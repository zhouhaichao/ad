package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonPrimitive;
import com.smyhvae.service.ValidateService;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.JSONUtility;
import com.smyhvae.util.JsonToMap;

import java.util.Map;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class ValidateActivity extends Activity {
    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";
    private MessageReceiver receiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);

        Intent msgIntent = new Intent(ValidateActivity.this, ValidateService.class);
        startService(msgIntent);
        //动态注册receiver
        IntentFilter filter = new IntentFilter(ACTION_RECV_MSG);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new MessageReceiver();
        registerReceiver(receiver, filter);

        SQLiteStudioService.instance().start(this);
    }

    //接收广播类
    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            JSONUtility jsonUtility = new JSONUtility();
            String message = intent.getStringExtra("result");
            Map<String, Object> queryMap = JsonToMap.toMap(message);
            String resultCode = ((JsonPrimitive) (queryMap.get("resultCode"))).getAsString();
            String msg = ((JsonPrimitive) (queryMap.get("message"))).getAsString();
            Map<String, Object> result = (Map<String, Object>)(queryMap.get("result"));
            Log.i("MessageReceiver", message);
            // 如果登录成功
            if (!message.trim().equals(true)) {
                if(resultCode.equals("0")){
                    Intent nextIntent = new Intent(ValidateActivity.this, RegeditActivity.class);
                    startActivity(nextIntent);
                    finish();
                    context.unregisterReceiver(this);
                }else if(resultCode.equals("1")){
                    String interfaceName = jsonUtility.getStringValueFromJSON(result, "interfaceName");
                    Integer epid = jsonUtility.getIntegerValueFromJSON(result, "id");
                    String code = jsonUtility.getStringValueFromJSON(result, "code");
                    Integer passportCode = jsonUtility.getIntegerValueFromJSON(result, "passportCode");
                    String webServerUrl = jsonUtility.getStringValueFromJSON(result, "webServerUrl");
                    Intent nextIntent = new Intent(ValidateActivity.this, LoginActivity.class);
                    nextIntent.putExtra("epid",epid);
                    nextIntent.putExtra("interfaceName",interfaceName);
                    nextIntent.putExtra("code", code);
                    nextIntent.putExtra("passportCode", passportCode);
                    nextIntent.putExtra("webServerUrl", webServerUrl);
                    startActivity(nextIntent);
                    finish();
                    context.unregisterReceiver(this);
                }
            } else {
                DialogUtil.showDialog(ValidateActivity.this, "出错了出错了!", false);
            }

        }
    }

    @Override
    protected void onDestroy() {
        //结束服务
//        unregisterReceiver(receiver);
//        SQLiteStudioService.instance().stop();
        stopService(new Intent(ValidateActivity.this, ValidateService.class));
        super.onDestroy();
    }
}
