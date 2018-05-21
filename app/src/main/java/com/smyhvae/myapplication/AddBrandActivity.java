package com.smyhvae.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonPrimitive;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.service.AddService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.JsonToMap;
import com.smyhvae.view.CommomDialog;

import java.io.Serializable;
import java.util.Map;

import static com.smyhvae.myapplication.AddStyleActivity.RESULT_ADD_BRAND;

/**
 * Created by Administrator on 2018/5/17.
 */

public class AddBrandActivity extends Activity implements View.OnClickListener{

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;

    private TextView TV_add;
    private ImageView IV_back;

    private EditText ET_brandName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand);

        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();

        TV_add = findViewById(R.id.add);
        TV_add.setOnClickListener(this);
        IV_back = findViewById(R.id.back);
        IV_back.setOnClickListener(this);
        ET_brandName =findViewById(R.id.ET_brandName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add:

                new CommomDialog(this, R.style.dialog, "你确定要保存吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm)
                        {
                            if(ET_brandName.getText().toString().trim().length()>0)
                            {
                                AddService addService = new AddService();
                                String result = addService.addBrand(webServerUrl,loginstaffid,logininvid,accountid,accessKey,ET_brandName.getText().toString().trim());
                                DataUtil dataUtil = new DataUtil();
                                FuBaseModel baseModel = dataUtil.message(result);
                                if(baseModel.getResultCode().intValue() ==1){
                                    Map<String, Object> queryMap = JsonToMap.toMap(result);
                                    Map<String, Object> colorMap = (Map<String, Object>) queryMap.get("result");

                                    int id =((JsonPrimitive)(colorMap.get("id"))).getAsInt();
                                    String name  = ((JsonPrimitive)(colorMap.get("name"))).getAsString();
                                    Intent intent = new Intent();
                                    intent.putExtra("id",id);
                                    intent.putExtra("name",name);
                                    setResult(RESULT_ADD_BRAND,intent);
                                    Log.i("TestLog",result+"---");
                                    finish();
                                }
                                else
                                {
                                    new  AlertDialog.Builder(AddBrandActivity.this) .setTitle("提示" ) .setMessage(baseModel.getMessage())
                                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int i) {
                                                    dialog.dismiss();
                                                }
                                            }) .show();
                                    //Toast.makeText(AddBrandActivity.this,baseModel.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(AddBrandActivity.this,"名称不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).show();




                /*if(ET_brandName.getText().toString().trim().length()>0)
                {
                    AddService addService = new AddService();
                    Integer groupId = colorGroups.get(SP_colorGroup.getSelectedItemPosition()).getId();
                    Integer rank  = colorGroups.get(SP_colorGroup.getSelectedItemPosition()).getRank();
                    String result = addService.addcolor(webServerUrl,loginstaffid,logininvid,accountid,accessKey,ET_brandName.getText().toString().trim(),null,groupId,rank);

                    DataUtil dataUtil = new DataUtil();
                    FuBaseModel baseModel = dataUtil.message(result);
                    if(baseModel.getResultCode().intValue() ==1){
                        Map<String, Object> queryMap = JsonToMap.toMap(result);
                        Map<String, Object> colorMap = (Map<String, Object>) queryMap.get("result");
                        FuColorModel color = new FuColorModel();
                        color.setId(((JsonPrimitive)(colorMap.get("id"))).getAsInt());
                        color.setName(((JsonPrimitive)(colorMap.get("name"))).getAsString());
                        color.setGroupid(((JsonPrimitive)(colorMap.get("groupid"))).getAsInt());
                        color.setCode(((JsonPrimitive)(colorMap.get("code"))).getAsInt());
                        //color.setSelectStatus(1);
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        intent.putExtra("color", (Serializable) color);
                        intent.putExtras(bundle);
                        setResult(RESULT_ADD_COLOR,intent);
                        finish();
                        //{"resultCode":1,"message":"操作成功","result":{"code":56,"name":"洋红","groupid":1077,"status":0,"id":9591,"accountid":134,"flag":0,"opid":826,"modelClass":"ResultColorModel"}}
                        Log.i("TestLog",result+"---");
                    }
                }
                else
                {
                    Toast.makeText(AddBrandActivity.this,"颜色名称不能为空",Toast.LENGTH_SHORT).show();
                }*/
                //Toast.makeText(AddColorActivity.this,"选择第"+SP_colorGroup.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();

                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
