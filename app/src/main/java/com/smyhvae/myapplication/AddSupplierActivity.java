package com.smyhvae.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonPrimitive;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.service.AddService;
import com.smyhvae.service.BaseService;
import com.smyhvae.service.ClientService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.JsonToMap;
import com.smyhvae.util.Logcat;
import com.smyhvae.view.CommomDialog;
import com.smyhvae.view.MyAdapter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.smyhvae.myapplication.AddStyleActivity.RESULT_ADD_CLASS;
import static com.smyhvae.myapplication.AddStyleActivity.RESULT_ADD_SUPPLIER;

/**
 * Created by Administrator on 2018/5/17.
 */

public class AddSupplierActivity extends Activity implements View.OnClickListener{

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    private MyAdapter adapter;

    private EditText ET_name,ET_phone,ET_discount,ET_address,ET_remark;
    private Spinner SP_priceType;
    private TextView TV_add;
    private ImageView IV_back;
    private List<FuBaseModel> priceTypeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);


        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();

        SP_priceType = findViewById(R.id.SP_priceType);
        TV_add = findViewById(R.id.add);
        TV_add.setOnClickListener(this);
        IV_back = findViewById(R.id.back);
        IV_back.setOnClickListener(this);
        ET_name = findViewById(R.id.ET_name);
        ET_address =findViewById(R.id.ET_address);
        ET_discount = findViewById(R.id.ET_discount);
        ET_discount.setText("1");
        ET_phone  = findViewById(R.id.ET_phone);
        ET_remark = findViewById(R.id.ET_remark);
        //ET_className =findViewById(R.id.ET_className);
        initData();
    }

    public void initData()
    {
        BaseService baseService = new BaseService();
        String result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "priceType", "fuPriceTypeList");
        DataUtil dataUtil = new DataUtil();
        FuBaseModel baseModel = dataUtil.message(result);
        if (baseModel.getResultCode().intValue() == 1) {
            priceTypeList = dataUtil.getList(result);
            FuBaseModel fuBaseModel = new FuBaseModel();
            priceTypeList.add(fuBaseModel);
            adapter = new MyAdapter(getApplicationContext(), priceTypeList);
            SP_priceType.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add:

                new CommomDialog(this, R.style.dialog, "确认要保存吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm)
                        {
                            if(ET_name.getText().toString().trim().length()>0)
                            {
                                String name = ET_name.getText().toString();
                                String phone = ET_phone.getText().toString();
                                String address = ET_address.getText().toString();
                                BigDecimal discount = new BigDecimal(1);
                                try
                                {
                                    discount = new BigDecimal(ET_discount.getText().toString());

                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                int pricetypeid = priceTypeList.get(SP_priceType.getSelectedItemPosition()).getId();
                                Integer type = 1;

                                String remark = ET_remark.getText().toString();
                                ClientService clientService = new ClientService();
                                String result = clientService.doAddClient(webServerUrl, loginstaffid, logininvid, accessKey, accountid, name, phone, address, discount, null, pricetypeid, null, type, null, null, null, null, null, null, null, remark);
                                Logcat.show(result);
                                DataUtil dataUtil = new DataUtil();
                                FuBaseModel baseModel = dataUtil.message(result);
                                if(baseModel.getResultCode().intValue() ==1){
                                    Map<String, Object> queryMap = JsonToMap.toMap(result);
                                    Map<String, Object> colorMap = (Map<String, Object>) queryMap.get("result");

                                    int id =((JsonPrimitive)(colorMap.get("id"))).getAsInt();
                                    String name1  = ((JsonPrimitive)(colorMap.get("name"))).getAsString();
                                    Intent intent = new Intent();
                                    intent.putExtra("id",id);
                                    intent.putExtra("name",name1);
                                    setResult(RESULT_ADD_SUPPLIER,intent);
                                    Log.i("TestLog",result+"---");
                                    finish();
                                }
                                else
                                {
                                    new AlertDialog.Builder(AddSupplierActivity.this).setTitle("提示").setMessage(baseModel.getMessage())
                                            .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            }).show();
                                    //Toast.makeText(AddSupplierActivity.this,baseModel.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(AddSupplierActivity.this,"名字不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).show();



                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
