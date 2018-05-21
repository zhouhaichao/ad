package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.model.FuStockModel;
import com.smyhvae.model.FuStyleColorModel;
import com.smyhvae.model.FuStyleImageModel;
import com.smyhvae.model.FuStyleModel;
import com.smyhvae.model.FuStylePriceTypeModel;
import com.smyhvae.model.FuStyleSizeModel;
import com.smyhvae.service.PhotoService;
import com.smyhvae.util.BigDecimalUtil;
import com.smyhvae.view.ImageViewSubClass;
import com.smyhvae.view.ScrollablePanel;
import com.smyhvae.view.ScrollablePanelAdapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ColorSizeActivity extends Activity  implements View.OnClickListener {
    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String fileServerip;
    private String webServerUrl;

    private ImageViewSubClass styleImage;
    private ImageView deleteView;
    private TextView selectinfo;
    private ImageView addView;
    private TextView stylecode;
    private TextView pricetypeString;
    private EditText priceString;
    private TextView styleString;
    private TextView amount;
   /* private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;*/

    int w_screen=0;
    int h_screen=0;
    private FuStyleModel styleModel;
    private ScrollablePanelAdapter scrollablePanelAdapter;

    private List<FuStyleColorModel> styleColorModelList = new ArrayList<>();
    private List<FuStyleSizeModel> styleSizeModelList = new ArrayList<>();
    private List<FuStockModel> stockModelList = new ArrayList<>();
    private List<FuSalesBillDetailModel> salesBillDetailModelList = new ArrayList<>();
    private List<FuSalesBillDetailModel> salesBillDetailList = new ArrayList<>();
    private String priceTypeString;
    private BigDecimal price = new BigDecimal(0);
    private String discount;
    private Bitmap bitmap;

    private BigDecimalUtil bigDecimalUtil = new BigDecimalUtil();

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1001:
                    styleImage.setImageDrawable(new BitmapDrawable(bitmap));
                    styleImage.setTag(msg.obj);
                    styleImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ColorSizeActivity.this,PhotoActivity.class);
                            intent.putExtra("id",(Integer)view.getTag());
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        fileServerip = application.getFileserverip();
        Intent intent = getIntent();
        styleModel = (FuStyleModel) intent.getSerializableExtra("styleModel");
        salesBillDetailList = (List<FuSalesBillDetailModel>) intent.getSerializableExtra("salesBillDetailList");
        priceTypeString = intent.getStringExtra("priceTypeString");
        discount = intent.getStringExtra("discount");
        if (discount != null){
            discount = "1";
        }

        if(styleModel.getLastprice()!=null)//上次价格
        {
            price = styleModel.getLastprice();
        }
        else
        {
            for (FuStylePriceTypeModel fuStylePriceTypeModel : styleModel.getFuStylePriceTypeModelList()){
                if (fuStylePriceTypeModel.getPricetypeString().equals(priceTypeString)){
                    price = fuStylePriceTypeModel.getPrice();
                }
            }
        }

        styleColorModelList = styleModel.getResultStyleColorModelList();
        styleSizeModelList = styleModel.getResultStyleSizeModelList();
        stockModelList = styleModel.getFuStockList();
        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;

        setContentView(R.layout.activity_color_size);
        initView();
        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        scrollablePanelAdapter  = new ScrollablePanelAdapter(getApplicationContext());
        generateTestData(scrollablePanelAdapter);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);



        new Thread(new Runnable() {
            @Override
            public void run() {
                if(styleModel.getFuStyleImageModelList()!=null&&styleModel.getFuStyleImageModelList().size()>0)
                {
                    PhotoService photoService =new PhotoService();
                    FuStyleImageModel fuStyleImageModel =styleModel.getFuStyleImageModelList().get(0);
                    Bitmap bitmap1 =photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,fuStyleImageModel.getId(),1);
                    if(bitmap1!=null)
                    {
                        bitmap = bitmap1;
                        Message msg = handler.obtainMessage();
                        msg.obj = fuStyleImageModel.getId();
                        msg.what = 1001;
                        handler.sendMessage(msg);
                    }
                }
            }
        }).start();

    }

    public void initView(){
        deleteView = findViewById(R.id.deleteView);
        deleteView.setOnClickListener(this);
        selectinfo = findViewById(R.id.selectinfo);
        styleImage = findViewById(R.id.styleImage);
        addView = findViewById(R.id.addView);
        addView.setOnClickListener(this);
        /*selectinfo.setWidth(w_screen/2);
        LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        infoParams.gravity= Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        infoParams.setMargins(w_screen/4, 0, 10, 0);
        selectinfo.setLayoutParams(infoParams);*/

        stylecode = findViewById(R.id.stylecode);
        //stylecode.setWidth(w_screen/3-100);
        stylecode.setText(styleModel.getCode());
        //stylecode.setPadding(0, 0, 10, 10);
        stylecode.setSingleLine(true);
        stylecode.setEllipsize(TextUtils.TruncateAt.END);

        pricetypeString = findViewById(R.id.pricetypeString);
        pricetypeString.setText(priceTypeString+"：");
        priceString = findViewById(R.id.price);
        //priceString.setWidth(w_screen/4-100);
        priceString.setText(bigDecimalUtil.getStringUtil(price.toString()));
        priceString.setSelection(priceString.getText().length());
        //priceString.setPadding(0, 0, 10, 10);
        priceString.setSingleLine(true);
        priceString.setEllipsize(TextUtils.TruncateAt.END);

        styleString = findViewById(R.id.styleString);
        styleString.setText(styleModel.getName());
        //styleString.setWidth(w_screen/3-100);
        //styleString.setPadding(0, 0, 10, 10);
        styleString.setSingleLine(true);
        styleString.setEllipsize(TextUtils.TruncateAt.END);

        amount = findViewById(R.id.amount);
        if (styleModel.getSumAmount() != null){
            amount.setText(styleModel.getSumAmount()+"");
        }
        //amount.setPadding(0, 0, 10, 10);

        /*line1 = findViewById(R.id.line1);
        line1.setHeight(2);
        line1.setWidth(w_screen/3-100);

        line2 = findViewById(R.id.line2);
        line2.setHeight(2);
        line2.setWidth(w_screen/4-120);

        line3 = findViewById(R.id.line3);
        line3.setHeight(2);
        line3.setWidth(w_screen/3-100);

        line4 = findViewById(R.id.line4);
        line4.setHeight(2);
        line4.setWidth(w_screen/4-120);*/

    }

    private void generateTestData(ScrollablePanelAdapter scrollablePanelAdapter) {
        scrollablePanelAdapter.setStyleColorModelList(styleColorModelList);
        scrollablePanelAdapter.setStyleSizeModelList(styleSizeModelList);

        if (stockModelList.size()>0){
            List<List<FuStockModel>> list = new ArrayList<>();
            for (FuStyleColorModel styleColorModel: styleColorModelList){
                List<FuStockModel> stockModelInfoList = new ArrayList<>();
                for (FuStyleSizeModel styleSizeModel: styleSizeModelList){
                    FuStockModel fuStockModel = new FuStockModel();
                    List<FuStockModel> fuStockList = new ArrayList<>();
                    for (FuStockModel stockModel : stockModelList){
                        if (stockModel.getColorid().equals(styleColorModel.getColorid())  && stockModel.getSizeid().equals(styleSizeModel.getSizeid())){
                            fuStockList.add(stockModel);
                            continue;
                        }
                    }
                    fuStockModel.setFuStockList(fuStockList);
                    stockModelInfoList.add(fuStockModel);
                }
                list.add(stockModelInfoList);
            }
            scrollablePanelAdapter.setStockModelList(list);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteView:
                finish();
                break;
            case R.id.addView:
                try
                {
                    price = BigDecimal.valueOf(Integer.parseInt(priceString.getText().toString() + ""));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                salesBillDetailModelList = scrollablePanelAdapter.getSalesBillDetailModelList();
                salesBillDetailList = new ArrayList<>();
                if (salesBillDetailModelList.size()>0){
                    for (FuSalesBillDetailModel salesBillDetailModel : salesBillDetailModelList){
                        if (salesBillDetailModel.getAmount() != null){
                            salesBillDetailModel.setStyleid(styleModel.getId());
                            salesBillDetailModel.setStyleCode(styleModel.getCode());
                            salesBillDetailModel.setStyleString(styleModel.getName());
                            salesBillDetailModel.setSalesAmount(styleModel.getSalesAmount());
                            salesBillDetailModel.setSalesCount(styleModel.getSalesCount());
                            salesBillDetailModel.setFuStyleImageModelList(styleModel.getFuStyleImageModelList());
                            salesBillDetailModel.setFuStylePriceTypeModelList(styleModel.getFuStylePriceTypeModelList());
                            salesBillDetailModel.setPrice(price);
                            salesBillDetailModel.setTotal(new BigDecimal(salesBillDetailModel.getAmount()).multiply(salesBillDetailModel.getPrice()).multiply(new BigDecimal(discount)));
                            salesBillDetailList.add(salesBillDetailModel);
                        }
                    }
                }
                Intent myIntent = new Intent();
                Bundle bundle = new Bundle();
                myIntent.putExtra("i", "0");
                myIntent.putExtra("salesBillDetailList", (Serializable) salesBillDetailList);
                myIntent.putExtras(bundle);
                setResult(RESULT_OK, myIntent);
                this.finish();
                this.onDestroy();
                break;
            default:
                break;
        }
    }
}
