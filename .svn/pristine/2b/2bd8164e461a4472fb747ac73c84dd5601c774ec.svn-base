package com.smyhvae.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuClientModel;
import com.smyhvae.service.AcService;
import com.smyhvae.service.BaseService;
import com.smyhvae.service.ClientService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.util.ResultMessageConstants;
import com.smyhvae.view.MyAdapter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddClientActivity extends Activity implements View.OnClickListener{
    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    int w_screen=0;
    int h_screen=0;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private ImageView back;
    private TextView tv_addClient;

    private RelativeLayout rl_clientinfo;
    private LinearLayout add_clientname;
    private LinearLayout add_inv;
    private LinearLayout add_phone;
    private LinearLayout add_staff;
    private LinearLayout add_discount;
    private LinearLayout add_address;
    private LinearLayout add_parentclient;
    private LinearLayout add_pricetype;
    private LinearLayout add_kind;
    private LinearLayout add_identity;
    private LinearLayout add_birthday;
    private LinearLayout add_area;
    private LinearLayout add_credit;
    private LinearLayout add_alarmcredit;
    private LinearLayout add_remark;

    private EditText clientname;
    private Spinner inv;
    private EditText clientphone;
    private EditText staffname;
    private EditText clientdiscount;
    private EditText clientaddress;
    private EditText parentclient;
    private Spinner pricetype;
    private Spinner kind;
    private EditText clientidentity;
    private EditText clientbirthday;
    private EditText clientarea;
    private EditText clientcredit;
    private EditText clientalarmcredit;
    private EditText clientremark;


    private List<FuBaseModel> priceTypeList = new ArrayList<>();
    private List<FuBaseModel> inventoryList = new ArrayList<>();
    private List<FuBaseModel> inventoryModelList = new ArrayList<>();
    private List<FuBaseModel> kindList = new ArrayList<>();
    private MyAdapter invAdapter;
    private MyAdapter adapter;
    private MyAdapter kindAdapter;
    private Integer invid;
    private Integer pricetypeid;
    private Integer kindid;
    private ListView staffView;
    private Integer staffid;
    private MyAdapter staffAdapter;
    private List<FuBaseModel> testArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_client);
        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        Intent intent = getIntent();
        priceTypeList = (List<FuBaseModel>) intent.getSerializableExtra("priceTypeList");

        new Thread() {
            public void run() {
                try {
                    String result = null;
                    for (int i=1; i<3; i++){
                        BaseService baseService = new BaseService();
                        if(i == 1){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "inventory", "fuInventoryList");
                        }else if(i==2){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "kind", "fuKindList");
                        }
                        Logcat.show(result);
                        Message msg = Message.obtain();
                        msg.what=i;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        initview();
        setData();
    }

    public void initview(){
        back = this.findViewById(R.id.back);
        tv_addClient = this.findViewById(R.id.tv_addClient);

        rl_clientinfo = this.findViewById(R.id.rl_clientinfo);
        add_clientname = this.findViewById(R.id.add_clientname);
        add_inv = this.findViewById(R.id.add_inv);
        add_phone = this.findViewById(R.id.add_phone);
        add_staff = this.findViewById(R.id.add_staff);
        add_discount = this.findViewById(R.id.add_discount);
        add_address = this.findViewById(R.id.add_address);
        add_parentclient = this.findViewById(R.id.add_parentclient);
        add_pricetype = this.findViewById(R.id.add_pricetype);
        add_kind = this.findViewById(R.id.add_kind);
        add_identity = this.findViewById(R.id.add_identity);
        add_birthday = this.findViewById(R.id.add_birthday);
        add_area = this.findViewById(R.id.add_area);
        add_credit = this.findViewById(R.id.add_credit);
        add_alarmcredit = this.findViewById(R.id.add_alarmcredit);
        add_remark = this.findViewById(R.id.add_remark);

        int widths = w_screen/2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widths, ViewGroup.LayoutParams.WRAP_CONTENT);
        add_clientname.setLayoutParams(params);
        add_inv.setLayoutParams(params);
        add_staff.setLayoutParams(params);
        add_discount.setLayoutParams(params);
        add_parentclient.setLayoutParams(params);
        add_pricetype.setLayoutParams(params);
        add_kind.setLayoutParams(params);
        add_identity.setLayoutParams(params);
        add_birthday.setLayoutParams(params);
        add_area.setLayoutParams(params);
        add_credit.setLayoutParams(params);
        add_alarmcredit.setLayoutParams(params);

        clientname = this.findViewById(R.id.clientname);
        clientname.setFocusable(true);
        inv = this.findViewById(R.id.inv);
        clientphone = this.findViewById(R.id.phone);
        staffname = this.findViewById(R.id.staffname);
        clientdiscount = this.findViewById(R.id.discount);
        clientaddress = this.findViewById(R.id.address);
        parentclient = this.findViewById(R.id.parentclient);
        pricetype = this.findViewById(R.id.pricetype);
        kind = this.findViewById(R.id.kind);
        clientidentity = this.findViewById(R.id.identity);
        clientbirthday = this.findViewById(R.id.birthday);
        clientarea = this.findViewById(R.id.area);
        clientcredit = this.findViewById(R.id.credit);
        clientalarmcredit = this.findViewById(R.id.alarmcredit);
        clientremark = this.findViewById(R.id.remark);
        clientremark.clearFocus();
        clientname.requestFocus();

        /*params = new LinearLayout.LayoutParams(widths-200, ViewGroup.LayoutParams.WRAP_CONTENT);
        clientname.setWidth(widths-50);
        inv.setLayoutParams(params);
        staffname.setWidth(widths-50);
        clientdiscount.setWidth(widths-50);
        parentclient.setWidth(widths-50);
        pricetype.setLayoutParams(params);
        kind.setLayoutParams(params);
        clientidentity.setWidth(widths-50);
        clientbirthday.setWidth(widths-50);
        clientarea.setWidth(widths-50);
        clientcredit.setWidth(widths-50);
        clientalarmcredit.setWidth(widths-50);
        clientremark.setWidth(w_screen-50);*/

        FuBaseModel fuBaseModel = new FuBaseModel();
        priceTypeList.add(fuBaseModel);
        adapter = new MyAdapter(getApplicationContext(), priceTypeList);
        pricetype.setAdapter(adapter);
        pricetype.setSelection(priceTypeList.size()-1);

        staffView = new ListView(this);
        staffView.setBackgroundColor(Color.parseColor("#FFC0CB"));
        staffView.setVisibility(View.GONE);
        params = new LinearLayout.LayoutParams(widths, 500);
        params.setMargins(80, h_screen/5-30, 0, 0);
        staffView.setLayoutParams(params);
        rl_clientinfo.addView(staffView);

        back.setOnClickListener(this);
        tv_addClient.setOnClickListener(this);
    }

    AcService acService = new AcService();
    public void setData(){

        inv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                invid = inventoryModelList.get(i).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        pricetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pricetypeid = priceTypeList.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        kind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kindid = kindList.get(i).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //店员AC
        staffAdapter = new MyAdapter(this, testArray);
        staffView.setAdapter(staffAdapter);// 设置Adapter，初始值为空

        staffView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                staffid = staffAdapter.getItem(position).getId();
                staffname.setText(staffAdapter.getItem(position).getName());
                staffView.setVisibility(View.GONE);
            }
        });

        staffname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testArray = new ArrayList<>();// 每次输入的时候，重新初始化数据列表

                if (!TextUtils.isEmpty(staffname.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    String input = staffname.getText().toString().trim();
                    testArray = acService.getAC(getApplicationContext(), webServerUrl, loginstaffid,
                            logininvid, accessKey, accountid, "staffListForAC", input, null);
                }
                staffAdapter.refreshData(testArray);// Adapter刷新数据
                staffView.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(staffname.getText().toString())){
                    staffView.setVisibility(View.GONE);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final Calendar c = Calendar.getInstance();
        clientbirthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddClientActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        clientbirthday.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            DataUtil dataUtil = new DataUtil();
            switch (msg.what){
                case 1:
                    FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                    if(baseModel.getResultCode().intValue() ==1){
                        inventoryList = dataUtil.getList(msg.obj.toString());
                        for (FuBaseModel fuBaseModel : inventoryList){
                            if (fuBaseModel.getName() != (ResultMessageConstants.DEFAULT_SELECTION_INVENTORY) && !(ResultMessageConstants.DEFAULT_SELECTION_INVENTORY).equals(fuBaseModel.getName())) {
                                inventoryModelList.add(fuBaseModel);
                            }
                        }
                        invAdapter = new MyAdapter(getApplicationContext(), inventoryModelList);
                        inv.setAdapter(invAdapter);
                        invid = inventoryModelList.get(0).getId();
                    }else if(baseModel.getResultCode().intValue() ==0){
                        DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                    }
                    break;
                case 2:
                    baseModel = dataUtil.message(msg.obj.toString());
                    if(baseModel.getResultCode().intValue() ==1){
                        kindList = dataUtil.getList(msg.obj.toString());
                        FuBaseModel fuBaseModel = new FuBaseModel();
                        kindList.add(fuBaseModel);
                        kindAdapter = new MyAdapter(getApplicationContext(), kindList);
                        kind.setAdapter(kindAdapter);
                        kind.setSelection(kindList.size()-1);
                    }else if(baseModel.getResultCode().intValue() ==0){
                        DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.tv_addClient:
                if (TextUtils.isEmpty(staffname.getText().toString())){
                    staffid = null;
                }
                if (!TextUtils.isEmpty(clientname.getText().toString())){
                    new  AlertDialog.Builder(this) .setTitle("确认" ) .setMessage("您确定要保存吗？" )
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                public static final int CLIENT_CODE = 2;

                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                    String name = clientname.getText().toString();
                                    String phone = clientphone.getText().toString();
                                    String address = clientaddress.getText().toString();
                                    BigDecimal discount = new BigDecimal(clientdiscount.getText().toString());
                                    Date birthday = null;
                                    try {
                                        if (!TextUtils.isEmpty(clientbirthday.getText().toString())){
                                            birthday = sdf.parse(clientbirthday.getText().toString());
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Integer type = 0;
                                    String credit = clientcredit.getText().toString();
                                    String alarmcredit = clientalarmcredit.getText().toString();
                                    String identity = clientidentity.getText().toString();
                                    String area = clientarea.getText().toString();
                                    String remark = clientremark.getText().toString();
                                    ClientService clientService = new ClientService();
                                    String result = clientService.doAddClient(webServerUrl, loginstaffid, logininvid, accessKey, accountid, name, phone, address, discount, birthday, pricetypeid, staffid, type, credit, alarmcredit, invid, identity, area, kindid, null, remark);
                                    Logcat.show(result);
                                    DataUtil dataUtil = new DataUtil();
                                    FuBaseModel baseModel = dataUtil.message(result);
                                    if(baseModel.getResultCode().intValue() ==1){
                                        FuClientModel fuClientModel = dataUtil.getClientData(result);
                                        Intent myIntent = new Intent(AddClientActivity.this, MainActivity.class);
                                        myIntent.putExtra("fuClientModel", fuClientModel);
                                        setResult(CLIENT_CODE, myIntent);
                                        Toast toast = Toast.makeText(getApplication(), baseModel.getMessage(), Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                        finish();
                                        AddClientActivity.this.onDestroy();
                                    }else if(baseModel.getResultCode().intValue() ==0){
                                        DialogUtil.showDialog(AddClientActivity.this, baseModel.getMessage(), false);
                                    }
                                }
                            }) .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }) .show();
                }else {
                    Toast toast = Toast.makeText(getApplication(), "注意！！！姓名为必填项！", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            default:
                break;
        }
    }
}
