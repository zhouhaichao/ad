package com.smyhvae.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.service.AccountService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DatabaseHelper;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.view.PromptBoxDialog;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class PrintActivity extends Activity implements OnClickListener {

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    private FuAccountModel fuAccountModel = new FuAccountModel();

    private LinearLayout mybackimage;
    private RelativeLayout rl_print_address;
    private TextView print_address;
    private RelativeLayout rl_print_page;
    private TextView print_page;
    private RelativeLayout rl_printInterval;
    private TextView printInterval;
    private RelativeLayout rl_offsetX;
    private TextView offsetX;
    private RelativeLayout rl_offsetY;
    private TextView offsetY;
    private RelativeLayout rl_codeOffset;
    private TextView codeOffset;
    private RelativeLayout rl_nameOffset;
    private TextView nameOffset;
    private RelativeLayout rl_amountOffset;
    private TextView amountOffset;
    private RelativeLayout rl_priceOffset;
    private TextView priceOffset;
    private RelativeLayout rl_totalOffset;
    private TextView totalOffset;
    private RelativeLayout rl_remarkOffset;
    private TextView remarkOffset;
    private RelativeLayout rl_barcodePaperSize;
    private TextView barcodePaperSize;

    private Switch appendclientinfo;
    private Switch needSpecialSizeTitle;
    private Switch printerType80mm;
    private Switch needLandscape;
    private Switch needprintarrears;
    private Switch needPrintDetailSeperator;
    private Switch is_or_not_wifi;
    private Switch not_print_color_size;
    private Switch custom_bar_code_print;
    private Switch bar_code_remove_year;
    private Switch styleinfo_warp_print;
    private Switch pos_print;
    private Switch merge_color_print;
    private Switch temp_print_page;
    private Switch buletooth_print;
    private Switch double_mode;
    private Switch salesbill_print_standbarcode;
    private Switch PT380;
    private TextView host_address;
    private Switch buletooth_print_host;

    private AccountService accountService = new AccountService();


    private String[] items={"1","2","3","4","5"};
    private String[] barcodepapersize={"80mm*60mm(纵向)", "40mm*25mm(每行两个)(带颜色尺码)", "40mm*25mm(每行两个)(带加盟商电话)",
            "40mm*60mm(每行两个)", "45mm*115mm", "40mm*75mm", "40mm*30mm", "25mm*30mm(银饰品)", "50mm*70mm"};

    private Socket socket;


    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            DataUtil dataUtil = new DataUtil();
            FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
            if(baseModel.getResultCode().intValue() ==1){
                fuAccountModel = dataUtil.getAccountData(msg.obj.toString());
                if (fuAccountModel.getAppendclientinfo().intValue()==0){
                    appendclientinfo.setChecked(false);
                }else {
                    appendclientinfo.setChecked(true);
                }
                if (fuAccountModel.getNeedprintarrears().intValue()==0){
                    needprintarrears.setChecked(false);
                }else {
                    needprintarrears.setChecked(true);
                }
            }else if(baseModel.getResultCode().intValue() ==0){
                DialogUtil.showDialog(PrintActivity.this, baseModel.getMessage(), false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_print);
        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();

        initView();
        initEvent();
        new Thread() {
            public void run() {
                try {
                    accountService = new AccountService();

                    String result = accountService.doSelectAccount(webServerUrl, loginstaffid, logininvid, accountid, accessKey);
                    Logcat.show(result);
                    Message msg = Message.obtain();
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }.start();
        initData();
    }

    private void initView() {
        mybackimage = findViewById(R.id.mybackimage);
        rl_print_address = findViewById(R.id.rl_print_address);
        print_address = findViewById(R.id.print_address);
        rl_print_page = findViewById(R.id.rl_print_page);
        print_page = findViewById(R.id.print_page);
        rl_printInterval = findViewById(R.id.rl_printInterval);
        printInterval = findViewById(R.id.printInterval);
        rl_offsetX = findViewById(R.id.rl_offsetX);
        offsetX = findViewById(R.id.offsetX);
        rl_offsetY = findViewById(R.id.rl_offsetY);
        offsetY = findViewById(R.id.offsetY);
        rl_codeOffset = findViewById(R.id.rl_codeOffset);
        codeOffset = findViewById(R.id.codeOffset);
        rl_nameOffset = findViewById(R.id.rl_nameOffset);
        nameOffset = findViewById(R.id.nameOffset);
        rl_amountOffset = findViewById(R.id.rl_amountOffset);
        amountOffset = findViewById(R.id.amountOffset);
        rl_priceOffset = findViewById(R.id.rl_priceOffset);
        priceOffset = findViewById(R.id.priceOffset);
        rl_totalOffset = findViewById(R.id.rl_totalOffset);
        totalOffset = findViewById(R.id.totalOffset);
        rl_remarkOffset = findViewById(R.id.rl_remarkOffset);
        remarkOffset = findViewById(R.id.remarkOffset);
        rl_barcodePaperSize = findViewById(R.id.rl_barcodePaperSize);
        barcodePaperSize = findViewById(R.id.barcodePaperSize);
        appendclientinfo = (Switch) findViewById(R.id.appendclientinfo);
        needSpecialSizeTitle = (Switch) findViewById(R.id.needSpecialSizeTitle);
        printerType80mm = (Switch) findViewById(R.id.printerType80mm);
        needLandscape = (Switch) findViewById(R.id.needLandscape);
        needprintarrears = (Switch) findViewById(R.id.needprintarrears);
        needPrintDetailSeperator = (Switch) findViewById(R.id.needPrintDetailSeperator);
        is_or_not_wifi = findViewById(R.id.is_or_not_wifi);
        not_print_color_size = findViewById(R.id.not_print_color_size);
        custom_bar_code_print = findViewById(R.id.custom_bar_code_print);
        bar_code_remove_year = findViewById(R.id.bar_code_remove_year);
        styleinfo_warp_print = findViewById(R.id.styleinfo_warp_print);
        pos_print = findViewById(R.id.pos_print);
        merge_color_print = findViewById(R.id.merge_color_print);
        temp_print_page = findViewById(R.id.temp_print_page);
        buletooth_print = findViewById(R.id.buletooth_print);
        double_mode = findViewById(R.id.double_mode);
        salesbill_print_standbarcode = findViewById(R.id.salesbill_print_standbarcode);
        PT380 = findViewById(R.id.PT380);
        host_address = findViewById(R.id.host_address);
        buletooth_print_host = findViewById(R.id.buletooth_print_host);
    }

    private void initEvent() {
        mybackimage.setOnClickListener(this);
        rl_print_address.setOnClickListener(this);
        rl_print_page.setOnClickListener(this);
        rl_printInterval.setOnClickListener(this);
        rl_offsetX.setOnClickListener(this);
        rl_offsetY.setOnClickListener(this);
        rl_codeOffset.setOnClickListener(this);
        rl_nameOffset.setOnClickListener(this);
        rl_amountOffset.setOnClickListener(this);
        rl_priceOffset.setOnClickListener(this);
        rl_totalOffset.setOnClickListener(this);
        rl_remarkOffset.setOnClickListener(this);
        rl_barcodePaperSize.setOnClickListener(this);

        appendclientinfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    accountService.doUpdateAppendclientinfo(webServerUrl, loginstaffid, logininvid, accountid, accessKey, 1);
                }else {
                    accountService.doUpdateAppendclientinfo(webServerUrl, loginstaffid, logininvid, accountid, accessKey, 0);
                }
            }
        });
        needSpecialSizeTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "needSpecialSizeTitle");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "needSpecialSizeTitle");
                }
                dbHelper.close();
            }
        });
        printerType80mm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "printerType80mm");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "printerType80mm");
                }
                dbHelper.close();
            }
        });
        needLandscape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "needLandscape");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "needLandscape");
                }
                dbHelper.close();
            }
        });
        needprintarrears.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    accountService.doUpdateNeedprintarrears(webServerUrl, loginstaffid, logininvid, accountid, accessKey, 1);
                }else {
                    accountService.doUpdateNeedprintarrears(webServerUrl, loginstaffid, logininvid, accountid, accessKey, 0);
                }
            }
        });
        needPrintDetailSeperator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "needPrintDetailSeperator");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "needPrintDetailSeperator");
                }
                dbHelper.close();
            }
        });
        is_or_not_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "isWIFIPrinter");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "isWIFIPrinter");
                }
                dbHelper.close();
            }
        });
        not_print_color_size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "convertToNotColorSizeStyleToPrint");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "convertToNotColorSizeStyleToPrint");
                }
                dbHelper.close();
            }
        });
        custom_bar_code_print.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "customizeBarcodePrint");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "customizeBarcodePrint");
                }
                dbHelper.close();
            }
        });
        bar_code_remove_year.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "barcodeWithoutYear");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "barcodeWithoutYear");
                }
                dbHelper.close();
            }
        });
        styleinfo_warp_print.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "styleInfoChange");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "styleInfoChange");
                }
                dbHelper.close();
            }
        });
        pos_print.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "isPos");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "isPos");
                }
                dbHelper.close();
            }
        });
        merge_color_print.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "convertToNotSizeStyleToPrint");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "convertToNotSizeStyleToPrint");
                }
                dbHelper.close();
            }
        });
        temp_print_page.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "tempPrintCount");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "tempPrintCount");
                }
                dbHelper.close();
            }
        });
        buletooth_print.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "btPrinter");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "btPrinter");
                }
                dbHelper.close();
            }
        });
        double_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "zq_dualmode");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "zq_dualmode");
                }
                dbHelper.close();
            }
        });
        salesbill_print_standbarcode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "printStandardBarcode");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "printStandardBarcode");
                }
                dbHelper.close();
            }
        });
        PT380.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "PT380");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "PT380");
                }
                dbHelper.close();
            }
        });
        buletooth_print_host.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                if (isChecked){
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "1", "printerHost");
                }else {
                    dbHelper.updateData(dbHelper.getWritableDatabase(), "0", "printerHost");
                }
                dbHelper.close();
            }
        });
    }

    public void initData(){
        DatabaseHelper db =  new DatabaseHelper(PrintActivity.this);
        String sql = "select * from fu_parameter ";
        Cursor cursor = db.queryParameter(sql, null);
        String val = null;
        String name = null;
        while(cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            if (name.equals("printerAddress")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                print_address.setText(val);
            }else if(name.equals("printCount")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                print_page.setText(val);
            }else if(name.equals("printInterval")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                printInterval.setText(val);
            }else if(name.equals("offsetX")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                offsetX.setText(val);
            }else if(name.equals("offsetY")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                offsetY.setText(val);
            }else if(name.equals("codeOffset")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                codeOffset.setText(val);
            }else if(name.equals("nameOffset")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                nameOffset.setText(val);
            }else if(name.equals("amountOffset")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                amountOffset.setText(val);
            }else if(name.equals("priceOffset")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                priceOffset.setText(val);
            }else if(name.equals("totalOffset")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                totalOffset.setText(val);
            }else if(name.equals("remarkOffset")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                remarkOffset.setText(val);
            }else if(name.equals("barcodePaperSize")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                barcodePaperSize.setText(val);
            }else if(name.equals("needSpecialSizeTitle")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    needSpecialSizeTitle.setChecked(true);
                }else {
                    needSpecialSizeTitle.setChecked(false);
                }
            }else if(name.equals("printerType80mm")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    printerType80mm.setChecked(true);
                }else {
                    printerType80mm.setChecked(false);
                }
            }else if(name.equals("needLandscape")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    needLandscape.setChecked(true);
                }else {
                    needLandscape.setChecked(false);
                }
            }else if(name.equals("needPrintDetailSeperator")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    needPrintDetailSeperator.setChecked(true);
                }else {
                    needPrintDetailSeperator.setChecked(false);
                }
            }else if(name.equals("isWIFIPrinter")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    is_or_not_wifi.setChecked(true);
                }else {
                    is_or_not_wifi.setChecked(false);
                }
            }else if(name.equals("convertToNotColorSizeStyleToPrint")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    not_print_color_size.setChecked(true);
                }else {
                    not_print_color_size.setChecked(false);
                }
            }else if(name.equals("customizeBarcodePrint")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    custom_bar_code_print.setChecked(true);
                }else {
                    custom_bar_code_print.setChecked(false);
                }
            }else if(name.equals("barcodeWithoutYear")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    bar_code_remove_year.setChecked(true);
                }else {
                    bar_code_remove_year.setChecked(false);
                }
            }else if(name.equals("styleInfoChange")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    styleinfo_warp_print.setChecked(true);
                }else {
                    styleinfo_warp_print.setChecked(false);
                }
            }else if(name.equals("isPos")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    pos_print.setChecked(true);
                }else {
                    pos_print.setChecked(false);
                }
            }else if(name.equals("convertToNotSizeStyleToPrint")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    merge_color_print.setChecked(true);
                }else {
                    merge_color_print.setChecked(false);
                }
            }else if(name.equals("tempPrintCount")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    temp_print_page.setChecked(true);
                }else {
                    temp_print_page.setChecked(false);
                }
            }else if(name.equals("btPrinter")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    buletooth_print.setChecked(true);
                }else {
                    buletooth_print.setChecked(false);
                }
            }else if(name.equals("zq_dualmode")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    double_mode.setChecked(true);
                }else {
                    double_mode.setChecked(false);
                }
            }else if(name.equals("printStandardBarcode")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    salesbill_print_standbarcode.setChecked(true);
                }else {
                    salesbill_print_standbarcode.setChecked(false);
                }
            }else if(name.equals("PT380")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    PT380.setChecked(true);
                }else {
                    PT380.setChecked(false);
                }
            }else if(name.equals("printerHost")){
                val = cursor.getString(cursor.getColumnIndex("val"));
                if (val.equals("1")){
                    buletooth_print_host.setChecked(true);
                }else {
                    buletooth_print_host.setChecked(false);
                }
            }
        }
        db.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mybackimage:
                finish();
                break;
            case R.id.rl_print_address:
                final PromptBoxDialog promptBoxDialog = new PromptBoxDialog(PrintActivity.this, R.style.dialog);
                promptBoxDialog.setTitle("提示").setContext(print_address.getText().toString()).setMessage("请输入打印地址")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if(confirm){
                            dialog.dismiss();
                        }
                    }
                }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            print_address.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), print_address.getText().toString(), "printerAddress");
                            dbHelper.close();
                        }
                    }
                }).show();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        promptBoxDialog.showKeyboard();
                    }
                },100);

                break;
            case R.id.rl_print_page:
                ShowSingleDialog();
                break;
            case R.id.rl_printInterval:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入连续打印间隔(秒)")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            printInterval.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), printInterval.getText().toString(), "printInterval");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_offsetX:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入水平偏移量(建议以10的倍数)")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            offsetX.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), offsetX.getText().toString(), "offsetX");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_offsetY:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入垂直偏移量(建议以10的倍数)")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            offsetY.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), offsetY.getText().toString(), "offsetY");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_codeOffset:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入款号偏移量")
                    .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm, String content) {
                            if(confirm){
                                dialog.dismiss();
                            }
                        }
                    }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm, String content) {
                            if (confirm){
                                dialog.dismiss();
                                codeOffset.setText(content);
                                DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                                dbHelper.updateData(dbHelper.getWritableDatabase(), codeOffset.getText().toString(), "codeOffset");
                                dbHelper.close();
                            }
                        }
                    }).show();
            break;
            case R.id.rl_nameOffset:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入名称偏移量")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            nameOffset.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), nameOffset.getText().toString(), "nameOffset");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_amountOffset:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入数量偏移量")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            amountOffset.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), amountOffset.getText().toString(), "amountOffset");
                            dbHelper.close();
                        }
                    }
                }).show();
            case R.id.rl_priceOffset:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入价格偏移量")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            priceOffset.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), priceOffset.getText().toString(), "priceOffset");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_totalOffset:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入小计偏移量")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            totalOffset.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), totalOffset.getText().toString(), "totalOffset");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_remarkOffset:
                new PromptBoxDialog(PrintActivity.this, R.style.dialog).setTitle("提示").setMessage("请输入备注偏移量")
                        .setNegativeButton("取消", new PromptBoxDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm, String content) {
                                if(confirm){
                                    dialog.dismiss();
                                }
                            }
                        }).setPositiveButton("确定", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String content) {
                        if (confirm){
                            dialog.dismiss();
                            remarkOffset.setText(content);
                            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
                            dbHelper.updateData(dbHelper.getWritableDatabase(), remarkOffset.getText().toString(), "remarkOffset");
                            dbHelper.close();
                        }
                    }
                }).show();
                break;
            case R.id.rl_barcodePaperSize:
                ShowBarcodePaperSizeDialog();
            default:
                break;
        }
    }

    private void ShowSingleDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示  请选择需要打印份数");

        try
        {
           int count =Integer.parseInt(print_page.getText().toString().trim());
            builder.setSingleChoiceItems(items, count-1, new DialogsingleClickListener());
            AlertDialog dialog=builder.create();
            dialog.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            builder.setSingleChoiceItems(items, 0, new DialogsingleClickListener());
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }

    class DialogsingleClickListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 判断 单选按钮
            print_page.setText(items[which]);
            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
            dbHelper.updateData(dbHelper.getWritableDatabase(), print_page.getText().toString(), "printCount");
            dbHelper.close();
            dialog.dismiss();
        }
    }

    private void ShowBarcodePaperSizeDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示  请选择条码模板");
        builder.setSingleChoiceItems(barcodepapersize, 0, new DialogBarcodePaperSizeClickListener());
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    class DialogBarcodePaperSizeClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // 判断 单选按钮
            barcodePaperSize.setText(barcodepapersize[which]);
            DatabaseHelper dbHelper =  new DatabaseHelper(PrintActivity.this);
            dbHelper.updateData(dbHelper.getWritableDatabase(), barcodePaperSize.getText().toString(), "barcodePaperSize");
            dbHelper.close();
            dialog.dismiss();
        }
    }

}
