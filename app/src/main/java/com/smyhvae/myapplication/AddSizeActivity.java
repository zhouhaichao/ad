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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonPrimitive;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuColorGroupModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.model.FuSizeGroupModel;
import com.smyhvae.model.FuSizeModel;
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
 * Created by Administrator on 2018/5/17.
 */

public class AddSizeActivity extends Activity implements View.OnClickListener{

    public static final int RESULT_ADD_SIZE = 1011;

    private TextView TV_add;
    private ImageView IV_back;
    private Spinner SP_sizeGroup;
    private EditText TV_sizeName;

    public static final int RESULT_ADD_COLOR = 1010;
    private List<FuSizeModel> sizes = new ArrayList<>();
    private List<FuSizeGroupModel> sizeGroups = new ArrayList<>();

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_size);

        Intent intent = getIntent();
        sizes = (List<FuSizeModel>) intent.getSerializableExtra("sizes");
        sizeGroups = (List<FuSizeGroupModel>) intent.getSerializableExtra("sizeGroups");

        TV_add = findViewById(R.id.add);
        TV_add.setOnClickListener(this);
        IV_back = findViewById(R.id.back);
        IV_back.setOnClickListener(this);
        SP_sizeGroup = findViewById(R.id.SP_sizeGroup);
        TV_sizeName =findViewById(R.id.TV_sizeName);
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
        ArrayList<FuBaseModel>  sizeGroupList = new ArrayList<>();
        for(int i = 0;i<sizeGroups.size();i++)
        {
            FuBaseModel fuBaseModel = new FuBaseModel();
            fuBaseModel.setName(sizeGroups.get(i).getName());
            sizeGroupList.add(fuBaseModel);
        }
        SP_sizeGroup.setAdapter( new MyAdapter(this,sizeGroupList));



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

                new CommomDialog(AddSizeActivity.this, R.style.dialog, "确认要保存吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm)
                        {
                            if(TV_sizeName.getText().toString().trim().length()>0)
                            {
                                AddService addService = new AddService();
                                Integer groupId = sizeGroups.get(SP_sizeGroup.getSelectedItemPosition()).getId();
                                //Integer rank = sizeGroups.get(SP_sizeGroup.getSelectedItemPosition()).getRank();
                                String result = addService.addSize(webServerUrl,loginstaffid,logininvid,accountid,accessKey,TV_sizeName.getText().toString().trim(),groupId,1);
                                DataUtil dataUtil = new DataUtil();
                                FuBaseModel baseModel = dataUtil.message(result);
                                if(baseModel.getResultCode().intValue() ==1){
                                    Map<String, Object> queryMap = JsonToMap.toMap(result);
                                    Map<String, Object> colorMap = (Map<String, Object>) queryMap.get("result");
                                    FuSizeModel size = new FuSizeModel();
                                    size.setSizeid(((JsonPrimitive)(colorMap.get("id"))).getAsInt());
                                    size.setName(((JsonPrimitive)(colorMap.get("name"))).getAsString());
                                    size.setGroupid(((JsonPrimitive)(colorMap.get("groupid"))).getAsInt());
                                    size.setCode(((JsonPrimitive)(colorMap.get("code"))).getAsInt());
                                    //color.setSelectStatus(1);
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.putExtra("size", (Serializable) size);
                                    intent.putExtras(bundle);
                                    setResult(RESULT_ADD_SIZE,intent);
                                    finish();
                                    //{"resultCode":1,"message":"操作成功","result":{"code":56,"name":"洋红","groupid":1077,"status":0,"id":9591,"accountid":134,"flag":0,"opid":826,"modelClass":"ResultColorModel"}}
                                    Log.i("TestLog",result+"---");
                                }
                                else{
                                    new AlertDialog.Builder(AddSizeActivity.this).setTitle("提示").setMessage(baseModel.getMessage())
                                            .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            }).show();
                                    //Toast.makeText(AddSizeActivity.this,baseModel.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(AddSizeActivity.this,"颜色名称不能为空",Toast.LENGTH_SHORT).show();
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
