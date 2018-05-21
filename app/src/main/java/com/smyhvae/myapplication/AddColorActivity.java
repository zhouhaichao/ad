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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonPrimitive;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuColorGroupModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.service.AddService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.JsonToMap;
import com.smyhvae.view.CommomDialog;
import com.smyhvae.view.MyAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/16.
 */

public class AddColorActivity extends Activity implements View.OnClickListener {


    private TextView TV_add;
    private ImageView IV_back;
    private Spinner SP_colorGroup;
    private EditText TV_colorName;

    public static final int RESULT_ADD_COLOR = 1010;
    private List<FuColorModel> colors = new ArrayList<>();
    private List<FuColorGroupModel> colorGroups = new ArrayList<>();

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_color);

        Intent intent = getIntent();
        colors = (List<FuColorModel>) intent.getSerializableExtra("colors");
        colorGroups = (List<FuColorGroupModel>) intent.getSerializableExtra("colorGroups");

        TV_add = findViewById(R.id.add);
        TV_add.setOnClickListener(this);
        IV_back = findViewById(R.id.back);
        IV_back.setOnClickListener(this);
        SP_colorGroup = findViewById(R.id.SP_colorGroup);
        TV_colorName =findViewById(R.id.TV_colorName);
        initData();

        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();

    }

    public void initData()
    {
        ArrayList<FuBaseModel>  colorGroupList = new ArrayList<>();
        for(int i = 0;i<colorGroups.size();i++)
        {
            FuBaseModel fuBaseModel = new FuBaseModel();
            fuBaseModel.setName(colorGroups.get(i).getName());
            colorGroupList.add(fuBaseModel);
        }
        SP_colorGroup.setAdapter( new MyAdapter(this,colorGroupList));

        /*SP_colorGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add:

                new CommomDialog(AddColorActivity.this, R.style.dialog, "确认要保存吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm)
                        {
                            if(TV_colorName.getText().toString().trim().length()>0)
                            {
                                AddService addService = new AddService();
                                Integer groupId = colorGroups.get(SP_colorGroup.getSelectedItemPosition()).getId();
                                Integer rank  = colorGroups.get(SP_colorGroup.getSelectedItemPosition()).getRank();
                                String result = addService.addcolor(webServerUrl,loginstaffid,logininvid,accountid,accessKey,TV_colorName.getText().toString().trim(),null,groupId,rank);

                                DataUtil dataUtil = new DataUtil();
                                FuBaseModel baseModel = dataUtil.message(result);
                                if(baseModel.getResultCode().intValue() ==1){
                                    Map<String, Object> queryMap = JsonToMap.toMap(result);
                                    Map<String, Object> colorMap = (Map<String, Object>) queryMap.get("result");
                                    FuColorModel color = new FuColorModel();
                                    color.setColorid(((JsonPrimitive)(colorMap.get("id"))).getAsInt());
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
                                }else
                                {
                                    new AlertDialog.Builder(AddColorActivity.this).setTitle("提示").setMessage(baseModel.getMessage())
                                            .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            }).show();
                                    //Toast.makeText(AddColorActivity.this,baseModel.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(AddColorActivity.this,"颜色名称不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).show();

                //Toast.makeText(AddColorActivity.this,"选择第"+SP_colorGroup.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();

                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
