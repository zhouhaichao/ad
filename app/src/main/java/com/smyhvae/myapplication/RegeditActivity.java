package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonPrimitive;
import com.smyhvae.service.RegeditService;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.JsonToMap;

import java.util.Map;

public class RegeditActivity extends Activity implements OnClickListener {
    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";
    private EditText regedit_id;
    private Button button;
    private MessageReceiver receiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regedit_view);
        regedit_id = (EditText) findViewById(R.id.regedit_id);
        button = (Button) findViewById(R.id.regedit);
        button.setOnClickListener(this);

        //动态注册receiver
        IntentFilter filter = new IntentFilter(ACTION_RECV_MSG);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new MessageReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regedit:
                regedit();
                break;
            default:
                break;
        }
    }

    private void regedit() {
        Boolean flag = true;
        if(regedit_id.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "企业编号不能为空", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(flag){
            Toast.makeText(getApplicationContext(), "验证中...", Toast.LENGTH_SHORT).show();
            Intent msgIntent = new Intent(RegeditActivity.this, RegeditService.class);
            msgIntent.putExtra("epCode", regedit_id.getText().toString().trim());
            startService(msgIntent);
        }
    }

    //接收广播类
    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("result");
            Map<String, Object> queryMap = JsonToMap.toMap(message);
            String resultCode = ((JsonPrimitive)(queryMap.get("resultCode"))).getAsString();
            String msg = ((JsonPrimitive)(queryMap.get("message"))).getAsString();
            Log.i("MessageReceiver", message);

            if (!message.trim().equals(true) ){
                if(resultCode.equals("1")){
                    Intent nextIntent = new Intent(RegeditActivity.this, ValidateActivity.class);
                    startActivity(nextIntent);
                    finish();
                    context.unregisterReceiver(this);
                }else if(resultCode.equals("0")){
                    DialogUtil.showDialog(RegeditActivity.this, msg, false);
                }
            }else{
                DialogUtil.showDialog(RegeditActivity.this, "注册失败!", false);
            }

        }
    }

}
