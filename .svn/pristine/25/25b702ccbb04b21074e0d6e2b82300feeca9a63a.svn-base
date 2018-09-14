package com.smyhvae.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuColorGroupModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.model.FuSizeGroupModel;
import com.smyhvae.model.FuSizeModel;
import com.smyhvae.model.FuStyleClassModel;
import com.smyhvae.model.FuStyleModel;
import com.smyhvae.model.FuStylePriceTypeModel;
import com.smyhvae.service.AcService;
import com.smyhvae.service.BaseService;
import com.smyhvae.service.StyleService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.ImageUtil;
import com.smyhvae.util.JSONUtility;
import com.smyhvae.util.JsonToMap;
import com.smyhvae.util.Logcat;
import com.smyhvae.view.CommomDialog;
import com.smyhvae.view.MyAdapter;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddStyleActivity extends Activity implements View.OnClickListener {

    /***
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /***
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    public static final int REQUEST_TAKE_PHOTO_PERMISSION = 5;
    public static final int REQUEST_CAMERA_PERMISSION = 6;
    public static final int REQUEST_WRITE_PERMISSION = 7;

    public static final int RESULT_ADD_COLOR = 1010;
    public static final int RESULT_ADD_SIZE = 1011;
    public static final int RESULT_ADD_CLASS = 1012;
    public static final int RESULT_ADD_BRAND = 1013;
    public static final int RESULT_ADD_SUPPLIER = 1014;

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    private String webServerUploadUrl;
    int w_screen=0;
    int h_screen=0;

    AcService acService = new AcService();

    private ImageView back;
    private TextView addstyleinfo;
    private TextView add;

    private RelativeLayout rl_styleinfo;
    private LinearLayout rl_left;
    private LinearLayout rl_right;
    private ImageView imageView;
    private ScrollView scv;
    private LinearLayout lay_price;
    private EditText stylecode;
    private EditText stylename;
    private EditText colorString;
    private ImageView addColor;
    private EditText sizeString;
    private ImageView addSize;
    private EditText supplier;
    private ImageView addSupplier;
    private EditText price;
    private Spinner classString;
    private ImageView addClass;
    private EditText brand;
    private ImageView addbrand;
    private Spinner season;
    private EditText remark;
    private EditText suppliercode;
    private EditText standbarcode;
    private ImageView addStandbarcode;
    private EditText marketdate;

    private LinearLayout codeinfo;
    private LinearLayout nameinfo;
    private LinearLayout colorinfo;
    private LinearLayout sizeinfo;
    private LinearLayout supplierinfo;
    private LinearLayout priceinfo;
    private LinearLayout classinfo;
    private LinearLayout brandinfo;
    private LinearLayout seasoninfo;
    private LinearLayout remarkinfo;
    private LinearLayout suppliercodeinfo;
    private LinearLayout standbarcodeinfo;
    private LinearLayout marketdateinfo;

    private MyAdapter supplierAdapter;
    private ListView supplierListView;
    private MyAdapter brandAdapter;
    private ListView brandListView;


    private Bitmap bitmapIamge;
    private File fileImage;


    private Integer clientid;
    private Integer brandid;
    private Integer classid;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<FuBaseModel> priceTypeList = new ArrayList<>();
    private MyAdapter adapter;
    private List<FuBaseModel> testArray = new ArrayList<>();
    private List<FuBaseModel> classList;
    private List<FuBaseModel> brandList;
    private List<FuColorModel> colorModelList;
    private List<FuSizeModel> sizeModelList;
    private List<FuColorModel> colors = new ArrayList<>();
    private List<FuSizeModel> sizes = new ArrayList<>();
    private List<FuStylePriceTypeModel> pricetypes = new ArrayList<>();
    private List<FuStyleClassModel> styleClasses = new ArrayList<>();
    private List<FuColorGroupModel> fuColorGroupList = new ArrayList<>();
    private List<FuSizeGroupModel> fuSizeGroupList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_style);
        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        webServerUploadUrl = application.getFileUploadIp();
        Log.d("sfn", application.getFuAccountModel().getColorsizemode()+"");
        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;


        new Thread() {
            public void run() {
                try {
                    String result = null;
                    for (int i=1; i<7; i++){
                        BaseService baseService = new BaseService();
                        if(i == 1){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "priceType", "fuPriceTypeList");
                        }else if(i==2){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "class", "fuClassList");
                        }else if(i==3){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "color", "fuColorList");
                        }else if(i==4){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "size", "fuSizeList");
                        }else if(i==5){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "colorGroup", "fuColorGroupList");
                        }else if(i==6){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "sizeGroup", "fuSizeGroupList");
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

        initView();

        setData();
    }

    public void initView(){
        back = this.findViewById(R.id.back);
        addstyleinfo = this.findViewById(R.id.addstyleinfo);
        add= this.findViewById(R.id.add);
        rl_styleinfo = this.findViewById(R.id.rl_styleinfo);
        rl_left = this.findViewById(R.id.rl_left);
        rl_right = this.findViewById(R.id.rl_right);
        imageView = this.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        scv = this.findViewById(R.id.scv);
        lay_price = this.findViewById(R.id.lay_price);
        stylecode = this.findViewById(R.id.stylecode);
        stylecode.requestFocus();
        stylecode.setRawInputType(Configuration.KEYBOARD_QWERTY);
        stylename = this.findViewById(R.id.stylename);
        colorString = this.findViewById(R.id.colorString);
        colorString.setFocusable(false);

        sizeString = this.findViewById(R.id.sizeString);
        sizeString.setFocusable(false);

        supplier = this.findViewById(R.id.supplier);

        price = this.findViewById(R.id.price);
        classString = this.findViewById(R.id.classString);

        brand = this.findViewById(R.id.brand);

        season = this.findViewById(R.id.season);
        remark = this.findViewById(R.id.remark);
        suppliercode = this.findViewById(R.id.suppliercode);
        standbarcode = this.findViewById(R.id.standbarcode);

        marketdate = this.findViewById(R.id.marketdate);

        codeinfo = this.findViewById(R.id.codeinfo);
        nameinfo = this.findViewById(R.id.nameinfo);
        colorinfo = this.findViewById(R.id.colorinfo);
        sizeinfo = this.findViewById(R.id.sizeinfo);

        supplierinfo = this.findViewById(R.id.supplierinfo);
        priceinfo = this.findViewById(R.id.priceinfo);
        classinfo = this.findViewById(R.id.classinfo);
        brandinfo = this.findViewById(R.id.brandinfo);
        seasoninfo = this.findViewById(R.id.seasoninfo);
        remarkinfo = this.findViewById(R.id.remarkinfo);
        suppliercodeinfo = this.findViewById(R.id.suppliercodeinfo);
        standbarcodeinfo = this.findViewById(R.id.standbarcodeinfo);
        marketdateinfo = this.findViewById(R.id.marketdateinfo);

        addColor = this.findViewById(R.id.addColor);
        addSize = this.findViewById(R.id.addSize);
        addSupplier = this.findViewById(R.id.addSupplier);
        addClass = this.findViewById(R.id.addClass);
        addbrand = this.findViewById(R.id.addbrand);
        addStandbarcode = this.findViewById(R.id.addStandbarcode);

        if(application.getFuAccountModel().getColorsizemode().intValue()==0){
            addColor.setVisibility(View.VISIBLE);
            addSize.setVisibility(View.VISIBLE);
            colorString.setHint("必填");
            colorString.setText("");
            colorString.setOnClickListener(this);
            sizeString.setHint("必填");
            sizeString.setText("");
            sizeString.setOnClickListener(this);
        }


        int widths = w_screen/2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w_screen/3+50, 400);
        params.setMargins(20, 0 , 0 , 0);
        scv.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(widths+100, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(60, 10, 20, 0);
        /*codeinfo.setLayoutParams(params);
        nameinfo.setLayoutParams(params);
        colorinfo.setLayoutParams(params);
        sizeinfo.setLayoutParams(params);
        supplierinfo.setLayoutParams(params);
        priceinfo.setLayoutParams(params);
        classinfo.setLayoutParams(params);
        brandinfo.setLayoutParams(params);
        seasoninfo.setLayoutParams(params);
        remarkinfo.setLayoutParams(params);*/

        widths = widths-250;
       /* stylecode.setWidth(widths);
        stylename.setWidth(widths);
        colorString.setWidth(widths);
        sizeString.setWidth(widths);
        supplier.setWidth(widths);
        price.setWidth(widths);

        params = new LinearLayout.LayoutParams(widths-10, 100);
        params.setMargins(10, 0, 5, 0);
        classString.setLayoutParams(params);
        classString.setPadding(5, 5, 5, 5);
        season.setLayoutParams(params);
        season.setSelection(6);

        brand.setWidth(widths);
        remark.setWidth(widths);
        suppliercode.setWidth(widths);
        standbarcode.setWidth(widths);
        marketdate.setWidth(widths);*/
        marketdate.setFocusable(false);

        supplierListView = new ListView(this);
        supplierListView.setVisibility(View.GONE);
        supplierListView.setBackgroundColor(Color.parseColor("#FFC0CB"));
//        supplierListView.setBackground(getResources().getDrawable(R.drawable.list_bg));
        params = new LinearLayout.LayoutParams(widths, 500);
        params.setMargins(widths-100, h_screen/4, 0, 0);
        supplierListView.setLayoutParams(params);
        rl_styleinfo.addView(supplierListView);

        brandListView = new ListView(this);
        brandListView.setVisibility(View.GONE);
        brandListView.setBackgroundColor(Color.parseColor("#FFC0CB"));
        params = new LinearLayout.LayoutParams(widths, 500);
        params.setMargins(widths-100, h_screen/3, 0, 0);
        brandListView.setLayoutParams(params);
        rl_styleinfo.addView(brandListView);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
        addColor.setOnClickListener(this);
        addSize.setOnClickListener(this);
        addSupplier.setOnClickListener(this);
        addClass.setOnClickListener(this);
        addbrand.setOnClickListener(this);
        addStandbarcode.setOnClickListener(this);

        /*if(getIntent().getStringExtra("requestActivity").equals("AddSalesBillFragment"))
        {
            addColor.setVisibility(View.INVISIBLE);
            addSize.setVisibility(View.INVISIBLE);
            colorString.setText("均色");
            colorString.setEnabled(false);
            sizeString.setText("均码");
            sizeString.setEnabled(false);

            FuColorModel fuColorModel = new FuColorModel();
            fuColorModel.setColorid(9371);
            fuColorModel.setStatus(1);
            colors.add(fuColorModel);

            FuSizeModel fuSizeModel = new FuSizeModel();
            fuSizeModel.setSizeid(6299);
            fuSizeModel.setStatus(1);
            sizes.add(fuSizeModel);

        }*/

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            DataUtil dataUtil = new DataUtil();
            if (msg.what == 1) {
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                if(baseModel.getResultCode().intValue() ==1){
                    priceTypeList = dataUtil.getList(msg.obj.toString());
                    for (int i=0; i<priceTypeList.size(); i++){
                        final FuStylePriceTypeModel fuStylePriceTypeModel = new FuStylePriceTypeModel();
                        final FuBaseModel fuBaseModel = priceTypeList.get(i);
                        fuStylePriceTypeModel.setPricetypeString(fuBaseModel.getName());
                        fuStylePriceTypeModel.setPricetypeid(fuBaseModel.getId());
                        fuStylePriceTypeModel.setRatio(new BigDecimal(1));
                        LinearLayout layout = new LinearLayout(getApplicationContext());
                        layout.setOrientation(LinearLayout.HORIZONTAL);
                        TextView textView = new TextView(getApplicationContext());
                        textView.setText(fuBaseModel.getName());
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setTextSize(16);
                        TextView tv = new TextView(getApplicationContext());
                        tv.setText("¥");
                        tv.setTextColor(Color.RED);
                        tv.setTextSize(20);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(15, 0, 0, 0);
                        tv.setLayoutParams(params);
                        final EditText editText = new EditText(getApplicationContext());
                        editText.setSingleLine(true);
                        editText.setEllipsize(TextUtils.TruncateAt.END);
                        editText.setTextColor(Color.RED);
                        editText.setPadding(20,50,0,0);
                        editText.setBackground(null);
                        editText.setWidth(w_screen/4);
                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
                        fuStylePriceTypeModel.setPrice(new BigDecimal(0));
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                if (editText.getText().toString().trim() != null && !("").equals(editText.getText().toString().trim())){
                                    fuStylePriceTypeModel.setPrice(new BigDecimal(editText.getText().toString()));
                                }else {
                                    fuStylePriceTypeModel.setPrice(new BigDecimal(0));
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                            }
                        });
                        layout.addView(textView);
                        layout.addView(tv);
                        layout.addView(editText);
                        TextView line = new TextView(getApplicationContext());
                        line.setHeight(1);
                        line.setWidth(w_screen/3-50);
                        line.setBackgroundColor(Color.DKGRAY);
                        line.setPadding(10, 10, 10, 5);
                        lay_price.addView(layout);
                        lay_price.addView(line);
                        pricetypes.add(fuStylePriceTypeModel);
                    }
                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                }
            }else if (msg.what == 2) {
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());

                if(baseModel.getResultCode().intValue() ==1){
                    classList = dataUtil.getList(msg.obj.toString());
                    FuBaseModel fuBaseModel = new FuBaseModel();
                    classList.add(fuBaseModel);
                    adapter = new MyAdapter(getApplicationContext(), classList);
                    classString.setAdapter(adapter);
                    classString.setSelection(classList.size()-1);

                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                }
            }else if(msg.what==3){
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                if(baseModel.getResultCode().intValue() ==1){
                    colorModelList = dataUtil.getColorList(msg.obj.toString());
                    if (application.getFuAccountModel().getColorsizemode().intValue()==1){
                        for (FuColorModel fuColorModel : colorModelList){
                            if (fuColorModel.getName().equals("均色")){
                                FuColorModel colorModel = new FuColorModel();
                                colorModel.setColorid(fuColorModel.getId());
                                colors.add(colorModel);
                            }
                        }
                    }
                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                }
            }else if(msg.what==4){
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                if(baseModel.getResultCode().intValue() ==1){
                    sizeModelList = dataUtil.getSizeList(msg.obj.toString());
                    if (application.getFuAccountModel().getColorsizemode().intValue()==1){
                        for (FuSizeModel fuSizeModel : sizeModelList){
                            if (fuSizeModel.getName().equals("均码")){
                                FuSizeModel sizeModel = new FuSizeModel();
                                sizeModel.setSizeid(fuSizeModel.getId());
                                sizes.add(sizeModel);
                            }
                        }
                    }
                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                }
            }else if(msg.what==5){
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                if(baseModel.getResultCode().intValue() ==1){
                    fuColorGroupList = dataUtil.getColorGroupList(msg.obj.toString());
                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                }
            }else if(msg.what==6){
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                if(baseModel.getResultCode().intValue() ==1){
                    fuSizeGroupList = dataUtil.getSizeGroupList(msg.obj.toString());
                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getApplicationContext(), baseModel.getMessage(), false);
                }
            }
            else if(msg.what==7){
                Toast toast = Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            else if(msg.what==8)
            {
                if(msg.arg2==0)
                    Toast.makeText(AddStyleActivity.this,"图片上传失败..",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent();
                intent.putExtra("styleId",msg.arg1);
                setResult(ReturnCODE,intent);
                finish();
            }
        }
    };

    public void setData(){
        marketdate.setText(sdf.format(new Date()));
        final Calendar c = Calendar.getInstance();
        marketdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddStyleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        marketdate.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //厂商AC
        supplierAdapter = new MyAdapter(this, testArray);
        supplierListView.setAdapter(supplierAdapter);// 设置Adapter，初始值为空

        supplierListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clientid = supplierAdapter.getItem(position).getId();
                supplier.setText(supplierAdapter.getItem(position).getName());
                supplierListView.setVisibility(View.GONE);
            }
        });

        supplier.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testArray = new ArrayList<>();// 每次输入的时候，重新初始化数据列表
                if (!TextUtils.isEmpty(supplier.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    String input = supplier.getText().toString().trim();
                    testArray =acService.getAC(getApplicationContext(), webServerUrl, loginstaffid,
                            logininvid, accessKey, accountid, "supplierListForAC", input, null);
                }
                supplierAdapter.refreshData(testArray);// Adapter刷新数据
                supplierListView.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(supplier.getText().toString())){
                    supplierListView.setVisibility(View.GONE);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //品牌AC
        brandAdapter = new MyAdapter(this, testArray);
        brandListView.setAdapter(brandAdapter);// 设置Adapter，初始值为空

        brandListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                brandid = brandAdapter.getItem(position).getId();
                brand.setText(brandAdapter.getItem(position).getName());
                brandListView.setVisibility(View.GONE);
            }
        });

        brand.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testArray = new ArrayList<>();// 每次输入的时候，重新初始化数据列表
                if (!TextUtils.isEmpty(brand.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    String input = brand.getText().toString().trim();
                    testArray =acService.getAC(getApplicationContext(), webServerUrl, loginstaffid,
                            logininvid, accessKey, accountid, "brandListForAC", input, null);
                }
                brandAdapter.refreshData(testArray);// Adapter刷新数据
                brandListView.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(brand.getText().toString())){
                    brandListView.setVisibility(View.GONE);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        classString.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classid = classList.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.addColor:
                Intent intent1 = new Intent(this,AddColorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("colors", (Serializable) colorModelList);
                bundle.putSerializable("colorGroups", (Serializable) fuColorGroupList);
                intent1.putExtras(bundle);
                startActivityForResult(intent1, REQUEST_CODE);
                break;
            case R.id.addSize:
                Intent intent2 = new Intent(this,AddSizeActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("sizes", (Serializable) sizeModelList);
                bundle2.putSerializable("sizeGroups", (Serializable) fuSizeGroupList);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, REQUEST_CODE);
                break;
            case R.id.addSupplier:
                Intent intent5 = new Intent(this,AddSupplierActivity.class);
                startActivityForResult(intent5, REQUEST_CODE);
                break;
            case R.id.addClass:
                Intent intent4 = new Intent(this,AddClassActivity.class);
                startActivityForResult(intent4, REQUEST_CODE);
                break;
            case R.id.addbrand:
                Intent intent3 = new Intent(this,AddBrandActivity.class);
                startActivityForResult(intent3, REQUEST_CODE);
                break;
            case R.id.addStandbarcode:
                if (ContextCompat.checkSelfPermission(AddStyleActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(AddStyleActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_TAKE_PHOTO_PERMISSION);
                }
                else
                {
                    IntentIntegrator integrator = new IntentIntegrator(AddStyleActivity.this);
                    integrator.initiateScan();
                }
                break;
            case R.id.add:
                if (doAdd()){
                    new CommomDialog(this, R.style.dialog, "您确定保存？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                String code = stylecode.getText().toString();
                                String name = stylename.getText().toString();
                                String seasons = (String) season.getSelectedItem();
                                String prices = price.getText().toString();
                                String time = marketdate.getText().toString();
                                String suppliercodes = suppliercode.getText().toString();
                                String standardbarcode = standbarcode.getText().toString();
                                String remarks = remark.getText().toString();

                                if (TextUtils.isEmpty(supplier.getText().toString())){
                                    clientid = null;
                                }
                                if (TextUtils.isEmpty(brand.getText().toString())) {
                                    brand = null;
                                }
                                if (classid != null){
                                    FuStyleClassModel styleClassModel = new FuStyleClassModel();
                                    styleClassModel.setClassid(classid);
                                    styleClasses.add(styleClassModel);
                                }

                                final StyleService styleService = new StyleService();
                                String result = styleService.doAddStyle(webServerUrl, loginstaffid, logininvid, accessKey, accountid, code, name, brandid,
                                        seasons, clientid, time, prices, suppliercodes, standardbarcode, remarks, styleClasses, colors, sizes, pricetypes);
                                Logcat.show("AddStyleActivity"+result);
                                final DataUtil dataUtil = new DataUtil();
                                FuBaseModel baseModel = dataUtil.message(result);
                                if(baseModel.getResultCode().intValue() ==1){

                                    //result参数实例 如{"resultCode":1,"message":"操作成功","result":{"id":19640,"modelClass":"FuStyleModel"}}
                                    JSONUtility jsonUtility = new JSONUtility();
                                    Map<String, Object> queryMap = JsonToMap.toMap(result);
                                    Map<String, Object> result1 = jsonUtility.getObjectValueFromJSON(queryMap, "result");

                                    final int styleId = jsonUtility.getIntegerValueFromJSON(result1,"id");
                                    if(mCurrentPhotoPath!=null)
                                    {
                                        fileImage = compressImage(mCurrentPhotoPath);
                                        if(fileImage!=null)
                                        {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    String resStr =styleService.doAddStyleImage(webServerUploadUrl,loginstaffid,logininvid,accessKey,accountid,styleId,fileImage);
                                                    Message msg = handler.obtainMessage();
                                                    msg.what = 8;
                                                    msg.arg1 = styleId;
                                                    if(resStr!= null)
                                                    {
                                                        FuBaseModel baseModel1 = dataUtil.message(resStr);
                                                        if(baseModel1.getResultCode().intValue() ==1)
                                                        {
                                                            Log.i("TestLog","图片上传成功");
                                                            msg.arg2 = 1;
                                                        }
                                                        else
                                                        {
                                                            Log.i("TestLog","图片上传失败");
                                                            msg.arg2 = 0;
                                                        }
                                                    }
                                                    else
                                                    {
                                                        msg.arg2 = 0;
                                                        Log.i("TestLog","resStr为null");
                                                    }
                                                    handler.sendMessage(msg);
                                                }
                                            }).start();
                                        }
                                        else
                                        {
                                            Toast.makeText(AddStyleActivity.this,"图片上传失败..",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                    {
                                        Intent intent =new Intent();
                                        intent.putExtra("styleId",styleId);
                                        setResult(ReturnCODE,intent);
                                        finish();
                                    }

                                }else if(baseModel.getResultCode().intValue() ==0){
                                    Message msg = handler.obtainMessage();
                                    msg.what=7;
                                    msg.obj=baseModel.getMessage();
                                    handler.sendMessage(msg);
                                }
                            }
                        }
                    }).setTitle("提示").show();
                }
                 break;
            case R.id.colorString:
                Intent intent=new Intent(AddStyleActivity.this, SelectColorActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("colors", (Serializable) colorModelList);
                bundle1.putSerializable("colorGroups", (Serializable) fuColorGroupList);
                intent.putExtras(bundle1);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.sizeString:
                Intent intentForSize=new Intent(AddStyleActivity.this, SelectSizeActivity.class);
                Bundle bundleForSize = new Bundle();
                bundleForSize.putSerializable("sizes", (Serializable) sizeModelList);
                bundleForSize.putSerializable("sizeGroups", (Serializable) fuSizeGroupList);
                intentForSize.putExtras(bundleForSize);
                startActivityForResult(intentForSize, REQUEST_CODE);
                break;

            case R.id.imageView:
                if (ContextCompat.checkSelfPermission(AddStyleActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(AddStyleActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION);

                }
                if (ContextCompat.checkSelfPermission(AddStyleActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(AddStyleActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_PERMISSION);
                }
                    showPopueWindow();

                break;
            default:
                break;
        }
    }

    private void showPopueWindow() {
        View popView = View.inflate(this, R.layout.popupwindow_camera_need, null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1/2;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PIC_BY_PICK_PHOTO);
                popupWindow.dismiss();
            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(AddStyleActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(AddStyleActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(AddStyleActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                            REQUEST_TAKE_PHOTO_PERMISSION);
            } else {
                //有权限，直接拍照
                 takeCamera(SELECT_PIC_BY_TACK_PHOTO);
            }
                popupWindow.dismiss();
            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_TAKE_PHOTO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
                takeCamera(SELECT_PIC_BY_TACK_PHOTO);
            } else {
                Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照

            } else {
                Toast.makeText(this, "相机权限获取失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照

            } else {
                Toast.makeText(this, "储存权限获取失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    String mCurrentPhotoPath;
    private void takeCamera(int num) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            Uri photoURI;
            // Continue only if the File was successfully created
            if (photoFile != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //如果是7.0及以上的系统使用FileProvider的方式创建一个Uri
                    Log.e("TestLog", "Build.VERSION.SDK_INT >= Build.VERSION_CODES.N");
                    photoURI = FileProvider.getUriForFile(this, "com.smyhvae.provider.fileprovider", photoFile);
                    takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    takePictureIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    //7.0以下使用这种方式创建一个Uri
                    photoURI = Uri.fromFile(photoFile);
                }
                if (photoFile != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    //跳转界面传回拍照所得数据
                    startActivityForResult(takePictureIntent, num);
                }
            }

        }
    }

    private File createImageFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;
        try {
            image = File.createTempFile(generateFileName(), /* prefix */ ".jpg", /* suffix */ storageDir /* directory */);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

    public boolean doAdd(){
        boolean flag = true;
        if (stylecode.getText().toString().trim().equals("")){
            new  AlertDialog.Builder(this) .setTitle("警告！" ) .setMessage("请输入款号！" )
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }) .show();
            flag = false;
            return flag;
        }

        if (colorString.getText().toString().trim().equals("")){
            new  AlertDialog.Builder(this) .setTitle("警告！" ) .setMessage("请输入颜色！" )
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }) .show();
            flag = false;
            return flag;
        }

        if (sizeString.getText().toString().trim().equals("")){
            new  AlertDialog.Builder(this) .setTitle("警告！" ) .setMessage("请输入尺码！" )
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    }) .show();
            flag = false;
        }

        return flag;
    }

    public final int ReturnCODE = 3;
    public static final int  REQUEST_CODE=1001;
    public static final int CODE=1002;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //        判断返回值是否为空
        if (scanResult != null) {
            //返回条形码数据
            String result = scanResult.getContents();
            standbarcode.setText(result);
        }

        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            colors = (List<FuColorModel>) data.getSerializableExtra("colors");
            String colorStrings="";
            for (FuColorModel colorModel : colors){
                Log.d("sfn", colorModel.getName());
                colorStrings = colorModel.getName()+","+colorStrings;
            }
            if (colorStrings.length()>0){
                colorStrings = colorStrings.substring(0, colorStrings.length()-1);
            }
            colorString.setText(colorStrings);
        }else if(requestCode==REQUEST_CODE && resultCode==CODE){
            sizes = (List<FuSizeModel>) data.getSerializableExtra("sizes");
            String sizeStrings="";
            for (FuSizeModel sizeModel : sizes){
                Log.d("sfn", sizeModel.getName());
                sizeStrings = sizeModel.getName()+","+sizeStrings;
            }
            if (sizeStrings.length()>0){
                sizeStrings = sizeStrings.substring(0, sizeStrings.length()-1);
            }
            sizeString.setText(sizeStrings);
        }
        else if(resultCode == RESULT_OK)
        {
            if (requestCode == SELECT_PIC_BY_PICK_PHOTO && null != data) {
                Uri selectedImage = data.getData();
                Log.i("Testlog","uri"+selectedImage.toString());
                //Bitmap bit = null;
               /* try {
                    bitmapIamge = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                    imageView.setImageBitmap(bitmapIamge);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
                }*/
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mCurrentPhotoPath = cursor.getString(columnIndex);
                bitmapIamge = ImageUtil.decodeSampledBitmapFromFilePath(mCurrentPhotoPath,200,200);
                imageView.setImageBitmap(bitmapIamge);
                //upload(picturePath);
                if(Build.VERSION.SDK_INT<14)
                    cursor.close();
            } else if (requestCode == SELECT_PIC_BY_TACK_PHOTO) {
                try {
                    /**
                     * 该uri是上一个Activity返回的
                     */
                    bitmapIamge = ImageUtil.decodeSampledBitmapFromFilePath(mCurrentPhotoPath,200,200);

                    imageView.setImageBitmap(bitmapIamge);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("tag",e.getMessage());
                    Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(requestCode==REQUEST_CODE && resultCode==RESULT_ADD_COLOR)
        {
            FuColorModel color = (FuColorModel) data.getSerializableExtra("color");
            colorModelList.add(color);
            colors.clear();
            colors.add(color);
            String colorStrings="";
            for (FuColorModel colorModel : colors){
                Log.d("sfn", colorModel.getName());
                colorStrings = colorModel.getName()+","+colorStrings;
            }
            if (colorStrings.length()>0){
                colorStrings = colorStrings.substring(0, colorStrings.length()-1);
            }
            colorString.setText(colorStrings);
        }
        else if(requestCode==REQUEST_CODE && resultCode==RESULT_ADD_SIZE)
        {
            FuSizeModel size = (FuSizeModel) data.getSerializableExtra("size");
            sizeModelList.add(size);
            sizes.clear();
            sizes.add(size);
            String sizeStrings="";
            for (FuSizeModel sizeModel : sizes){
                Log.d("sfn", sizeModel.getName());
                sizeStrings = sizeModel.getName()+","+sizeStrings;
            }
            if (sizeStrings.length()>0){
                sizeStrings = sizeStrings.substring(0, sizeStrings.length()-1);
            }
            sizeString.setText(sizeStrings);
        }else if(requestCode==REQUEST_CODE && resultCode==RESULT_ADD_BRAND)
        {
            try
            {
                int id =data.getIntExtra("id",0);
                String name = data.getStringExtra("name");
                if(id>0)
                {
                    brandid = id;
                    brand.setText(name);
                    brandListView.setVisibility(View.GONE);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(requestCode==REQUEST_CODE && resultCode==RESULT_ADD_CLASS)
        {
            try
            {
                int id =data.getIntExtra("id",0);
                String name = data.getStringExtra("name");
                if(id>0)
                {
                    FuBaseModel fuBaseModel = new FuBaseModel();
                    fuBaseModel.setName(name);
                    fuBaseModel.setId(id);
                    classList.add(fuBaseModel);
                    classString.setSelection(classString.getAdapter().getCount()-1);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }else if(requestCode==REQUEST_CODE && resultCode==RESULT_ADD_SUPPLIER)
        {
            try
            {
                int id =data.getIntExtra("id",0);
                String name = data.getStringExtra("name");
                if(id>0)
                {
                    clientid = id;
                    supplier.setText(name);
                    supplierListView.setVisibility(View.GONE);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public  File compressImage(String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap=ImageUtil.decodeSampledBitmapFromFilePath(mCurrentPhotoPath,800,800);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
            if (options <= 10)//如果图片的质量已降到最低则，不再进行压缩
                break;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(),filename+".jpg");
        //File file = new File(mCurrentPhotoPath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                //BAFLogger.e(TAG,e.getMessage());
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //BAFLogger.e(TAG,e.getMessage());
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }

    public  void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps==null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

}
