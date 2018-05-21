package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuStaffModel;
import com.smyhvae.service.HandlerService;
import com.smyhvae.service.LoginService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DatabaseHelper;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity implements OnClickListener {
    private MyApplication application;
    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";
    private EditText accountname, password;
    private Button button;
    private TextView codeno;
    private TextView no;
    private MessageReceiver receiver ;
    private Spinner s;
    private String str;
    private String accountidString;
    private Integer accountid;
    private Handler handler;
    private NewThread thread;

    private String code;
    private Integer passportCode;

    Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    codeno.setText(code);
                    break;
                case 2:
                    no.setText(passportCode.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        application = (MyApplication) getApplicationContext();
        accountname = (EditText) findViewById(R.id.loginAccount_id);
        accountname.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_NUMBER;
            }
            @Override
            protected char[] getAcceptedChars() {
                char[] data = getStringData(R.string.wordAndNum).toCharArray();
                return data;
            }
        });
        password = (EditText) findViewById(R.id.password_id);
        button = (Button) findViewById(R.id.login);
        codeno = findViewById(R.id.codeno);
        no = findViewById(R.id.no);

        s= (Spinner) this.findViewById(R.id.spinner);

        Intent i = getIntent();
        Integer epid = i.getExtras().getInt("epid");
        final String interfaceName = i.getStringExtra("interfaceName");
        code = i.getStringExtra("code");
        passportCode = i.getExtras().getInt("passportCode");
        String webServerUrl = i.getStringExtra("webServerUrl");
        Log.d("sfn", webServerUrl);
        webServerUrl = "http://"+webServerUrl+"/future/app/entry.htm?";
        application.setWebServerUrl(webServerUrl);
        Message msg = Message.obtain();
        msg.obj = code;
        msg.what=1;
        mhandler.sendMessage(msg);
        msg = Message.obtain();
        msg.obj = passportCode;
        msg.what=2;
        mhandler.handleMessage(msg);

        Intent intent = new Intent(LoginActivity.this, HandlerService.class);
        intent.putExtra("epid", epid);
        intent.putExtra("interfaceName", interfaceName);
        intent.putExtra("webServerUrl", webServerUrl);
        startService(intent);
        findViewById(R.id.traceroute_rootview).setOnClickListener(this);

        button.setOnClickListener(this);
        //动态注册receiver
        IntentFilter filter = new IntentFilter(ACTION_RECV_MSG);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new MessageReceiver();
        registerReceiver(receiver, filter);

        DatabaseHelper db =  new DatabaseHelper(LoginActivity.this);
        String sql = "select * from fu_parameter where name=? ";
        String[] selectionArgs = new String[] { "lastLoginNo" };
        Cursor cursor = db.queryParameter(sql, selectionArgs);
        String accountCode = null;
        while(cursor.moveToNext()){
            accountCode = cursor.getString(cursor.getColumnIndex("val"));
        }
        Log.d("sfn", accountCode+"****");
        accountname.setText(accountCode);
        selectionArgs = new String[] { "lastaccountid" };
        cursor = db.queryParameter("select * from fu_parameter where name=? ", selectionArgs);
        while (cursor.moveToNext()){
            accountidString = cursor.getString(cursor.getColumnIndex("val"));
        }
        db.close();


    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.traceroute_rootview:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                break;
            case R.id.login:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        Boolean flag = true;
        if(accountname.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), R.string.accountName_empty, Toast.LENGTH_LONG).show();
            flag = false;
        }else if(password.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), R.string.password_empty, Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(flag){

            if(accountid!=null&&accountname!=null)
            {
                DatabaseHelper dbHelper =  new DatabaseHelper(LoginActivity.this);
                dbHelper.updateData(dbHelper.getWritableDatabase(), accountname.getText().toString(), "lastLoginNo");
                dbHelper.updateData(dbHelper.getWritableDatabase(), accountid.toString(), "lastaccountid");
                dbHelper.close();

                handler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        DataUtil dataUtil = new DataUtil();
                        Log.i("TestLog",msg.obj.toString());

                        FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                        if(baseModel.getResultCode().intValue() ==1){
                            FuStaffModel staffModel = dataUtil.getStaffData(msg.obj.toString());
                            application.setFuStaffModel(staffModel);
                            //图片文件地址
                            application.setFileserverip("http://"+dataUtil.getFileServerip(msg.obj.toString())+"/fuFile/app/entry.htm?");
                            application.setFileUploadIp("http://"+dataUtil.getFileServerip(msg.obj.toString())+"/fuFile/app/image.htm?");

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(baseModel.getResultCode().intValue() ==0){
                            DialogUtil.showDialog(LoginActivity.this, baseModel.getMessage(), false);
                        }
                    }

                };

                thread = new NewThread();
            }
            else
            {
                Toast.makeText(this,"获取不到账号ID或NAME..",Toast.LENGTH_LONG).show();
            }
        }
    }



    //接收广播类
    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("result");
            String key = intent.getStringExtra("key");
            Logcat.show(message);
            Logcat.show(key);
            DataUtil dataUtil = new DataUtil();
            final List<FuAccountModel> accountModelList = dataUtil.getData(message);

            final List<String> list = new ArrayList<>();
            final List<Integer> list1 = new ArrayList<>();
            for(int i = 0; i < accountModelList.size(); i++){
                String name = accountModelList.get(i).getName();
                Integer accountid = accountModelList.get(i).getId();
                list.add(name);
                list1.add(accountid);
            }


            String accessKey = dataUtil.getKey(key);
            application.setAccessKey(accessKey);

            if (!message.trim().equals(true) ){
                s.setAdapter(new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_list_item_1, list));
                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        str= list.get(position);
                        accountid = list1.get(position);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }else{
                DialogUtil.showDialog(LoginActivity.this, "网络连接错误!", false);
            }

            for(int i=0;i<s.getAdapter().getCount();i++){
                if(accountidString.equals(list1.get(i).toString())){
                    s.setSelection(i,true);// 默认选中项
                    break;
                }
            }

        }
    }


    private class NewThread extends Thread {
        private Handler mHandler;
        private Looper mLooper;
        private String result;
        private LoginService loginService;

        public NewThread() {
            start();
        }

        public void run() {
            Looper.prepare();
            mLooper = Looper.myLooper();
            mHandler = new Handler(mLooper) {

                @Override
                public void handleMessage(Message msg) {
                    Message newMsg = Message.obtain();
                    newMsg.obj = msg.obj;
                    handler.sendMessage(newMsg);
                }

            };
            try {
                String code = accountname.getText().toString().trim();
                String psw = password.getText().toString().trim();
                String accessKey = application.getAccessKey();
                loginService = new LoginService();
                result = loginService.doLogin(application.getWebServerUrl(), code, psw, accessKey, accountid);
                Logcat.show(result);
            } catch (Exception e) {
                result = e.toString();
            }
            Message msg = Message.obtain();
            msg.obj = result;
            mHandler.sendMessage(msg);
            Looper.loop();
        }

    }

    @Override
    protected void onDestroy() {
        //结束服务
        stopService(new Intent(LoginActivity.this, HandlerService.class));
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}