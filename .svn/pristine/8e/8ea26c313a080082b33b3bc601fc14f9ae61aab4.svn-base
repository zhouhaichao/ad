package com.smyhvae.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyhvae.fragment.AddSalesBillFragment;
import com.smyhvae.fragment.SalesBillFragment;
import com.smyhvae.fragment.SettingsFragment;
import com.smyhvae.fragment.StyleFragment;
import com.smyhvae.fragment.SuspendSalesBillFragment;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.service.AccountService;
import com.smyhvae.service.PrintService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;

public class MainActivity extends FragmentActivity implements OnClickListener{

    private MyApplication application;
    private AccountService accountService;

    private LinearLayout mAdd;
    private LinearLayout mTabSalesbill;
    private LinearLayout mTabStyle;
    private LinearLayout mTabSetting;

    private TextView addbill;
    private TextView salesbill;
    private TextView style;
    private TextView mine;

    private AddSalesBillFragment addSalesBillFragment;
    private SalesBillFragment salesBillFragment;
    private SuspendSalesBillFragment suspendSalesBillFragment;
    private Fragment styleFragment;
    private Fragment settingsFragment;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String accessKey;
    private String webServerUrl;

    private int i=0;

    private Handler handler1 = new Handler() {

        public void handleMessage(Message msg) {
            DataUtil dataUtil = new DataUtil();
            FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
            if(baseModel.getResultCode().intValue() ==1){
                FuAccountModel fuAccountModel = dataUtil.getAccountData(msg.obj.toString());
                application.setFuAccountModel(fuAccountModel);
            }else if(baseModel.getResultCode().intValue() ==0){
                DialogUtil.showDialog(MainActivity.this, baseModel.getMessage(), false);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        application = (MyApplication) getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        new Thread() {
            public void run() {
                try {
                    accountService = new AccountService();

                    String result = accountService.doSelectAccount(webServerUrl, loginstaffid, logininvid, accountid, accessKey);
                    Logcat.show(result);
                    Message msg = Message.obtain();
                    msg.obj = result;
                    handler1.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }.start();

        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment

        Intent intent = getIntent();
        String a = intent.getStringExtra("i");
        if (a != null){
            i = Integer.valueOf(a);
        }
        initFragment(i);
    }

    public AddSalesBillFragment getAddSalesBillFragment() {
        return addSalesBillFragment;
    }

    public SuspendSalesBillFragment getSuspendSalesBillFragment()
    {
        return suspendSalesBillFragment;
    }

    public  SalesBillFragment getSalesBillFragment()
    {
        return  salesBillFragment;
    }
    public void initFragment(int index) {

        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (addSalesBillFragment == null) {
                    addSalesBillFragment = new AddSalesBillFragment();
                    transaction.add(R.id.fl_content, addSalesBillFragment);
                } else {
                    transaction.show(addSalesBillFragment);
                }
                break;
            case 1:
                if (salesBillFragment == null) {
                    salesBillFragment = new SalesBillFragment();
                    transaction.add(R.id.fl_content, salesBillFragment);
                } else {
                    transaction.show(salesBillFragment);
                }
                break;
            case 2:
                 if (styleFragment == null) {
                     styleFragment = new StyleFragment();
                     transaction.add(R.id.fl_content, styleFragment);
                 } else {
                     transaction.show(styleFragment);
                 }
                 break;
            case 3:
                if (settingsFragment == null) {
                    settingsFragment = new SettingsFragment();
                    transaction.add(R.id.fl_content, settingsFragment);
                } else {
                    transaction.show(settingsFragment);
                }
                break;
            case 4:
                if (suspendSalesBillFragment == null) {
                    suspendSalesBillFragment = new SuspendSalesBillFragment();
                    transaction.add(R.id.fl_content, suspendSalesBillFragment);
                } else {
                    transaction.show(suspendSalesBillFragment);
                }
                break;
            default:
                break;
         }
        // 提交事务
        transaction.commit();
    }

     //隐藏Fragment
     private void hideFragment(FragmentTransaction transaction) {
         if (addSalesBillFragment != null){
             transaction.hide(addSalesBillFragment);
         }
         if (salesBillFragment != null) {
             transaction.hide(salesBillFragment);
         }
         if (styleFragment != null) {
            transaction.hide(styleFragment);
         }
         if (settingsFragment != null) {
             transaction.hide(settingsFragment);
         }
         if (suspendSalesBillFragment != null) {
             transaction.hide(suspendSalesBillFragment);
         }

     }

     private void initEvent() {
         // 设置按钮监听
         mAdd.setOnClickListener(this);
         mTabSalesbill.setOnClickListener(this);
         mTabStyle.setOnClickListener(this);
         mTabSetting.setOnClickListener(this);
     }

     private void initView() {

         // 底部菜单Linearlayout
         this.mAdd = (LinearLayout) findViewById(R.id.id_tab_add);
         this.mTabSalesbill = (LinearLayout) findViewById(R.id.id_tab_weixin);
         this.mTabStyle = (LinearLayout) findViewById(R.id.id_tab_frd);
         this.mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);

         // 底部菜单标题
         this.addbill = findViewById(R.id.addbill);
         this.salesbill = findViewById(R.id.salesbill);
         this.style = findViewById(R.id.style);
         this.mine = findViewById(R.id.mine);
     }

     @Override
     public void onClick(View v) {
         switch (v.getId()) {
             case R.id.id_tab_add:
                 restartBotton();
                 addbill.setTextColor(0xff1B940A);
                 addbill.setTextColor(getResources().getColor(R.color.saddleBrown));
                 initFragment(0);
                 break;
             case R.id.id_tab_weixin:
                 restartBotton();
                 salesbill.setTextColor(0xff1B940A);
                 salesbill.setTextColor(getResources().getColor(R.color.saddleBrown));
                 initFragment(1);
                 break;
             case R.id.id_tab_frd:
                 restartBotton();
                 style.setTextColor(0xff1B940A);
                 style.setTextColor(getResources().getColor(R.color.saddleBrown));
                 initFragment(2);
                 break;
             case R.id.id_tab_settings:
                 restartBotton();
                 mine.setTextColor(0xff1B940A);
                 mine.setTextColor(getResources().getColor(R.color.saddleBrown));
                 initFragment(3);
                 break;
             default:
                 break;
         }
     }
     private void restartBotton() {
         // TextView置为白色
         addbill.setTextColor(0xff000000);
         salesbill.setTextColor(0xff000000);
         style.setTextColor(0xff000000);
         mine.setTextColor(0xff000000);
     }

    @Override
    public void onBackPressed() {
        Log.i("TestLog","后台运行");
        moveTaskToBack(false);
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Intent service = new Intent(this, PrintService.class);
        stopService(service);
        super.onDestroy();
    }


    public void reLoadFragView()
    {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        transaction.commit();
        addSalesBillFragment=null;
        initFragment(0);
    }

    public void reLoadSuspendView()
    {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        transaction.commit();
        suspendSalesBillFragment= null;
        initFragment(4);
    }

    /*public void onBackPressed() {
        Log.i("TestLog","后台运行");
        //方式一：将此任务转向后台
        moveTaskToBack(false);
        //方式二：返回手机的主屏幕
    *//*Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addCategory(Intent.CATEGORY_HOME);
    startActivity(intent);*//*
    }*/
}
