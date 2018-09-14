package com.smyhvae.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.model.FuSalesBillModel;
import com.smyhvae.model.FuStaffModel;
import com.smyhvae.service.BaseService;
import com.smyhvae.service.PhotoService;
import com.smyhvae.service.PrintService;
import com.smyhvae.service.SalesBillService;
import com.smyhvae.util.BigDecimalUtil;
import com.smyhvae.util.ClientThread;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DatabaseHelper;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.util.Print;
import com.smyhvae.view.CommomDialog;
import com.smyhvae.util.PrintBillUtil;
import com.smyhvae.view.DialogPhotoView;
import com.smyhvae.view.SwipeListLayout;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.List;

public class SelectSalesBillActivity extends Activity implements View.OnClickListener{
    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    private String fileServerip;
    private FuAccountModel accountModel;
    private FuStaffModel staffModel;
    int w_screen=0;
    int h_screen=0;


    private LinearLayout backview;
    private TextView print;
    private TextView text_title;
    private TextView tv_addSalesBill;
    private EditText et_clientid;
    private EditText arrears;
    private EditText occurencetime;
    private EditText et_staffid;
    private EditText discount;
    private EditText remark;

    private LinearLayout acLay;
    private TableLayout tableLayout;

    private TextView sum_id;
    private TextView sumamountdetail;
    private TextView sum_num;
    private TextView sum_money;
    private TextView tv_arrears;
    private ScrollView sv;

    private DataUtil dataUtil;
    private BigDecimalUtil bigDecimalUtil;
    int id;
    private int numberber = 0;
    private BigDecimal totlele = new BigDecimal(0);
    private int salesAmount = 0;
    private int cancelAmount = 0;

    ClientThread clientThread;
    String result;
    private String isWIFIPrinter;
    private String isPrinterType80mm;

    private static boolean isStrictMode = false;
    DatabaseHelper db;
    Cursor cursor;
    String ip="";
    private Integer staffid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_sales_bill);
        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        accountModel = application.getFuAccountModel();
        staffModel = application.getFuStaffModel();
        fileServerip = application.getFileserverip();

        sv =findViewById(R.id.sv);
        //sv.setPadding(0,0,0,0);

        findViewById(R.id.TV_null).setVisibility(View.GONE);

        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        initview();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", id);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SalesBillService salesBillService = new SalesBillService();
                result = salesBillService.doSelectSalesBill(webServerUrl, loginstaffid, logininvid, accessKey, accountid, id);
                Logcat.show(result);
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }).start();


        db =  new DatabaseHelper(SelectSalesBillActivity.this);
        String sql = "select * from fu_parameter ";
        cursor = db.queryParameter(sql, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            if (name.equals("printerAddress")){
                ip = cursor.getString(cursor.getColumnIndex("val"));
            }
            if (name.equals("isWIFIPrinter")){
                isWIFIPrinter = cursor.getString(cursor.getColumnIndex("val"));
            }
            if(name.equals("printerType80mm"))
            {
                isPrinterType80mm = cursor.getString(cursor.getColumnIndex("val"));
            }
        }

        if(isWIFIPrinter.equals("0")){

        }else if (isWIFIPrinter.equals("1")){
//            if(! isStrictMode){
//                StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder()).detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//                StrictMode.setVmPolicy((new android.os.StrictMode.VmPolicy.Builder()).detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
//                isStrictMode = true;
//            }
        }

    }

    public void initview(){
        et_clientid =this.findViewById(R.id.et_clientid);
        tv_arrears =this.findViewById(R.id.tv_arrears);
        this.findViewById(R.id.addClient).setVisibility(View.INVISIBLE);
        et_clientid.setWidth(w_screen/4);
        //et_clientid.setPadding(20, 20, 10, 20);
        et_clientid.setFocusable(false);
        et_clientid.setEnabled(false);
        arrears = this.findViewById(R.id.arrears);
        arrears.setWidth(w_screen/4);
        //arrears.setPadding(20, 20, 10, 20);
        occurencetime = this.findViewById(R.id.occurencetime);
        occurencetime.setWidth(w_screen/4);
        //occurencetime.setPadding(20, 20, 10, 10);
        occurencetime.setFocusable(false);
        occurencetime.setEnabled(false);
        et_staffid = this.findViewById(R.id.et_staffid);
        et_staffid.setWidth(w_screen/4);
        et_staffid.setPadding(20, 20, 10, 10);
        et_staffid.setFocusable(false);
        et_staffid.setEnabled(false);
        discount = this.findViewById(R.id.discount);
        //discount.setWidth(w_screen/3-160);
        discount.setPadding(20, 20, 10, 10);
        discount.setFocusable(false);
        discount.setEnabled(false);
        remark = this.findViewById(R.id.remark);
        remark.setWidth(w_screen/2-100);
        remark.setPadding(20, 20, 10, 10);
        remark.setFocusable(false);
        remark.setEnabled(false);

        acLay = this.findViewById(R.id.acLay);
        acLay.setVisibility(View.GONE);

        tableLayout = this.findViewById(R.id.tablay);
        sum_id = this.findViewById(R.id.sum_id);
        sumamountdetail = this.findViewById(R.id.sumamountdetail);
        sum_num = this.findViewById(R.id.sum_num);
        sum_money = this.findViewById(R.id.sum_money);

        backview = this.findViewById(R.id.back_SalesBill);
        backview.setVisibility(View.VISIBLE);
        print = this.findViewById(R.id.print);
        print.setVisibility(View.VISIBLE);

        text_title = this.findViewById(R.id.text_title);
        text_title.setText("销售查询");
        tv_addSalesBill = this.findViewById(R.id.tv_addSalesBill);
        tv_addSalesBill.setVisibility(View.GONE);

        backview.setOnClickListener(this);
        print.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    dataUtil = new DataUtil();
                    FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
                    if(baseModel.getResultCode().intValue() ==1){
                        bigDecimalUtil = new BigDecimalUtil();
                        FuSalesBillModel fuSalesBillModel = dataUtil.getSalesBillData(msg.obj.toString());
                        if (fuSalesBillModel.getClientString() != null){
                            et_clientid.setText(fuSalesBillModel.getClientString());
                            if(fuSalesBillModel.getArrears()!=null)
                            {
                                if(fuSalesBillModel.getArrears().intValue()>0)
                                {
                                    arrears.setTextColor(getResources().getColor(R.color.delete));
                                }
                                if(fuSalesBillModel.getArrears().intValue()<0)
                                {
                                    arrears.setTextColor(getResources().getColor(R.color.money_in));
                                    tv_arrears.setText("余额");
                                }
                                arrears.setText(bigDecimalUtil.getStringUtil(Math.abs(fuSalesBillModel.getArrears().intValue())+""));
                            }
                        }

                        if (fuSalesBillModel.getStaffString() != null){
                            et_staffid.setText(fuSalesBillModel.getStaffString());
                        }
                        occurencetime.setText(bigDecimalUtil.getDate(fuSalesBillModel.getOccurrencetime()));
                        if (fuSalesBillModel.getDiscount() != null){
                            discount.setText(bigDecimalUtil.getStringUtil(fuSalesBillModel.getDiscount().toString()));
                        }else {
                            discount.setText("1");
                        }
                        if (fuSalesBillModel.getRemark() != null){
                            remark.setText(fuSalesBillModel.getRemark());
                        }

                        List<FuSalesBillDetailModel> salesBillDetailModelList = fuSalesBillModel.getFuSalesBillDetailList();
                        sum_id.setText("总计 "+salesBillDetailModelList.size());
                        numberber = 0;
                        totlele = new BigDecimal(0);
                        salesAmount = 0;
                        cancelAmount = 0;
                        for (FuSalesBillDetailModel salesBillDetailModel : salesBillDetailModelList){
                            HorizontalScrollView hsv = new HorizontalScrollView(SelectSalesBillActivity.this);
                            hsv.setHorizontalScrollBarEnabled(false);
                            hsv.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View arg0, MotionEvent arg1) {
                                    //不能滑动
                                    return true;
                                    //可以滑动
                                    //return false;
                                }
                            });
                            TableRow tabRow = new TableRow(SelectSalesBillActivity.this);
                            LinearLayout layContent = new LinearLayout(SelectSalesBillActivity.this);
                            layContent.setOrientation(LinearLayout.VERTICAL);

                            View convertView = addRow(salesBillDetailModel);

                            //View line = new View(SelectSalesBillActivity.this);
                            //line.setBackgroundColor(Color.parseColor("#A7A5A5"));
                           // LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1);
                            //line.setLayoutParams(lineParams);

                            layContent.addView(convertView);
                            //layContent.addView(line);
                            tabRow.addView(layContent);
                            hsv.addView(tabRow);
                            tableLayout.addView(hsv);
                            numberber = numberber + salesBillDetailModel.getAmount();
                            totlele = totlele .add(salesBillDetailModel.getTotal());
                            if (salesBillDetailModel.getAmount().intValue()>0){
                                salesAmount = salesAmount + salesBillDetailModel.getAmount();
                            }
                            if (salesBillDetailModel.getAmount().intValue()<0){
                                cancelAmount = cancelAmount + salesBillDetailModel.getAmount();
                            }
                        }
                        sum_num.setText(numberber + "");
                        sum_money.setText(bigDecimalUtil.getStringUtil(totlele+""));
                        sumamountdetail.setText("("+salesAmount+","+cancelAmount+")");

                        staffid = fuSalesBillModel.getStaffid() == null ? loginstaffid : fuSalesBillModel.getStaffid();

                    }else if(baseModel.getResultCode().intValue() ==0){
                        DialogUtil.showDialog(SelectSalesBillActivity.this, baseModel.getMessage(), false);
                    }
                    break;
                case 2:

                    break;
                case 3://ip地址连接超时
                    DialogUtil.showDialog(SelectSalesBillActivity.this, "连接打印机超时，请检查打印地址", false);
                    break;

                case 4:
                    DialogUtil.showDialog(SelectSalesBillActivity.this, "颜色尺码模式不支持该打印..", false);
                    break;
                default:
                    break;
            }
        }
    };
    int itemId  =0;

    public View addRow1(final FuSalesBillDetailModel fuSalesBillDetailModel){

        //表格内容
        final LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setPadding(0,10,0,10);
        LinearLayout layoutinfo = new LinearLayout(this);
        layoutinfo.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutinfoParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutinfoParams.gravity= Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        layoutinfo.setLayoutParams(layoutinfoParams);
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageParams.gravity=Gravity.LEFT;
        imageParams.setMargins(10,0,10,0);
        imageView.setLayoutParams(imageParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackground(getResources().getDrawable(R.drawable.logo));

        if( fuSalesBillDetailModel.getFuStyleImageModelList()!=null&&fuSalesBillDetailModel.getFuStyleImageModelList().size()>0)
        {
            PhotoService photoService =new PhotoService();
            Bitmap bitmap =photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,fuSalesBillDetailModel.getFuStyleImageModelList().get(0).getId(),1);
            if(bitmap!=null)
            {
                imageView.setImageDrawable(new BitmapDrawable(bitmap));
                imageView.setTag(fuSalesBillDetailModel.getFuStyleImageModelList().get(0).getId());
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(SelectSalesBillActivity.this,PhotoActivity.class);
                        intent.putExtra("id",(Integer) view.getTag());
                        startActivity(intent);
                        //PhotoService photoService = new PhotoService();
                        //new DialogPhotoView(SelectSalesBillActivity.this,photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid, (Integer) view.getTag(),0)).show();
                    }
                });
            }


        }

        LinearLayout layoutinfo1 = new LinearLayout(this);
        layoutinfo1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutinfo1Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutinfo1Params.gravity=Gravity.RIGHT;
        layoutinfo1Params.setMargins(20,0,10,0);
        layoutinfo1.setLayoutParams(layoutinfo1Params);

        LinearLayout layoutinfo2 = new LinearLayout(this);
        layoutinfo2.setOrientation(LinearLayout.HORIZONTAL);
        layoutinfo2.setPadding(0,5,0,5);

        TextView styleCode = new TextView(this);
        styleCode.setText(fuSalesBillDetailModel.getStyleCode());
        styleCode.setTextColor(Color.BLACK);
        styleCode.setPadding(0,0,10,0);
        TextView styleString = new TextView(this);
        styleString.setWidth(w_screen/3);
        styleString.setText(fuSalesBillDetailModel.getStyleString());
        styleString.setTextColor(Color.BLACK);
        styleString.setPadding(0,0,40,0);
        styleString.setSingleLine(true);
        styleString.setEllipsize(TextUtils.TruncateAt.END);
        final EditText remarkDetail = new EditText(this);
        remarkDetail.setWidth(w_screen/4-50);
        remarkDetail.setHint("备注");
        remarkDetail.setSingleLine(true);
        remarkDetail.setSelection(0);
        remarkDetail.setText(fuSalesBillDetailModel.getRemark());
        remarkDetail.setTextColor(Color.BLACK);
        remarkDetail.setPadding(10, 5, 10, 5);
        remarkDetail.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        remarkDetail.setEnabled(false);
        layoutinfo2.addView(styleCode);
        layoutinfo2.addView(styleString);
        layoutinfo2.addView(remarkDetail);

        LinearLayout layoutinfo3 = new LinearLayout(this);
        layoutinfo2.setOrientation(LinearLayout.HORIZONTAL);
        TextView colorString = new TextView(this);
        colorString.setWidth(w_screen/8);
        colorString.setText(fuSalesBillDetailModel.getColorString());
        colorString.setTextColor(Color.BLACK);
        colorString.setSingleLine(true);
        colorString.setEllipsize(TextUtils.TruncateAt.END);
        TextView sizeString = new TextView(this);
        sizeString.setWidth(w_screen/8);
        sizeString.setText(fuSalesBillDetailModel.getSizeString());
        sizeString.setTextColor(Color.BLACK);
        sizeString.setSingleLine(true);
        sizeString.setEllipsize(TextUtils.TruncateAt.END);
        final EditText amountDetail = new EditText(this);
        amountDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
        amountDetail.setWidth(w_screen/7);
        amountDetail.setPadding(20, 10, 10, 10);

        amountDetail.setTextColor(Color.BLACK);
        amountDetail.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        amountDetail.setSingleLine(true);
        amountDetail.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
        amountDetail.setFocusable(false);
        amountDetail.setEnabled(false);

        final EditText priceDetail = new EditText(this);
        priceDetail.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getPrice().toString()));
        priceDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
        priceDetail.setWidth(w_screen/7);
        priceDetail.setPadding(20, 10, 10, 10);
        priceDetail.setTextColor(Color.BLACK);
        priceDetail.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        priceDetail.setSingleLine(true);
        priceDetail.setInputType(InputType.TYPE_CLASS_PHONE);
        priceDetail.setEnabled(false);

        final EditText discountDetail = new EditText(this);
        discountDetail.setText(discount.getText().toString());
        discountDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
        discountDetail.setWidth(w_screen/7);
        discountDetail.setPadding(20, 10, 10, 10);
        discountDetail.setTextColor(Color.BLACK);
        discountDetail.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        discountDetail.setSingleLine(true);
        discountDetail.setInputType(InputType.TYPE_CLASS_PHONE);
        discountDetail.setEnabled(false);

        final TextView totalDetail = new TextView(this);
        LinearLayout.LayoutParams totalParams = new LinearLayout.LayoutParams(w_screen/8, ViewGroup.LayoutParams.WRAP_CONTENT);
        totalParams.gravity=Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        totalParams.setMargins(20,0,10,0);
        totalDetail.setLayoutParams(totalParams);
        totalDetail.setWidth(w_screen/6);
        totalDetail.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getTotal().toString()));
        totalDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        totalDetail.setTextColor(Color.BLACK);
        totalDetail.setSingleLine(true);
        totalDetail.setEllipsize(TextUtils.TruncateAt.END);
        totalDetail.setEnabled(false);

        TextView delete = new TextView(this);
        delete.setWidth(w_screen/8);
        delete.setHeight(130);
        delete.setBackgroundColor(Color.RED);
        delete.setText("删除");
        delete.setTextColor(Color.WHITE);
        delete.setGravity(Gravity.CENTER);
        delete.setTag(itemId);

        layoutinfo3.addView(colorString);
        layoutinfo3.addView(sizeString);
        layoutinfo3.addView(amountDetail);
        layoutinfo3.addView(priceDetail);
        layoutinfo3.addView(discountDetail);
        layoutinfo3.addView(totalDetail);

        layoutinfo1.addView(layoutinfo2);
        layoutinfo1.addView(layoutinfo3);
        layoutinfo.addView(imageView);
        layoutinfo.addView(layoutinfo1);
        layoutinfo.addView(delete);
        linearLayout1.addView(layoutinfo);

        itemId++;


        if (fuSalesBillDetailModel.getAmount() != null){

            if(fuSalesBillDetailModel.getAmount()<0)
            {
                styleCode.setTextColor(Color.RED);
                styleString.setTextColor(Color.RED);
                amountDetail.setTextColor(Color.RED);
                sizeString.setTextColor(Color.RED);
                colorString.setTextColor(Color.RED);
                remarkDetail.setTextColor(Color.RED);
                priceDetail.setTextColor(Color.RED);
                totalDetail.setTextColor(Color.RED);
                discountDetail.setTextColor(Color.RED);
            }

            amountDetail.setText(String.valueOf(fuSalesBillDetailModel.getAmount()));
        }


        return linearLayout1;
    }

    public View addRow(final FuSalesBillDetailModel fuSalesBillDetailModel) {
        View view = View.inflate(this,R.layout.item_salesbill,null);
        ImageView IV_logo;
        final TextView TV_styleCode,TV_styleName,TV_styleColor,TV_styleSize,TV_total,TV_delete;
        final EditText ET_remark,ET_amount,ET_price,ET_discount;

        ((SwipeListLayout)view.findViewById(R.id.SLL_item)).enable =false;
        view.findViewById(R.id.TV_delete).setVisibility(View.GONE);

        IV_logo =view.findViewById(R.id.IV_logo);
        if( fuSalesBillDetailModel.getFuStyleImageModelList()!=null&&fuSalesBillDetailModel.getFuStyleImageModelList().size()>0)
        {
            PhotoService photoService =new PhotoService();
            Bitmap bitmap =photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,fuSalesBillDetailModel.getFuStyleImageModelList().get(0).getId(),1);
            if(bitmap!=null)
            {
                IV_logo.setImageDrawable(new BitmapDrawable(bitmap));
                IV_logo.setTag(fuSalesBillDetailModel.getFuStyleImageModelList().get(0).getId());
                IV_logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SelectSalesBillActivity.this, PhotoActivity.class);
                        intent.putExtra("id",(Integer) view.getTag());
                        startActivity(intent);
                        //PhotoService photoService = new PhotoService();
                        //new DialogPhotoView(getContext(),photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid, (Integer) view.getTag(),0)).show();
                    }
                });
            }
        }
        TV_styleCode = view.findViewById(R.id.TV_styleCode);
        TV_styleName = view.findViewById(R.id.TV_styleName);
        TV_styleColor = view.findViewById(R.id.TV_styleColor);
        TV_styleSize = view.findViewById(R.id.TV_styleSize);
        TV_total = view.findViewById(R.id.TV_total);
        ET_remark = view.findViewById(R.id.ET_remark);
        ET_amount = view.findViewById(R.id.ET_amount);
        ET_price = view.findViewById(R.id.ET_price);
        ET_discount = view.findViewById(R.id.ET_discount);
        TV_delete = view.findViewById(R.id.TV_delete);

        TV_styleCode.setPadding(0,0,10,0);
        TV_styleCode.setText(fuSalesBillDetailModel.getStyleCode());
        TV_styleCode.setTextColor(Color.BLACK);

        TV_styleName.setWidth(w_screen/3);
        TV_styleName.setText(fuSalesBillDetailModel.getStyleString());
        TV_styleName.setTextColor(Color.BLACK);
        TV_styleName.setPadding(0,0,40,0);
        TV_styleName.setSingleLine(true);
        TV_styleName.setEllipsize(TextUtils.TruncateAt.END);

        ET_remark.setWidth(w_screen/4-50);
        ET_remark.setHint("备注");
        ET_remark.setSingleLine(true);
        ET_remark.setSelection(0);
        ET_remark.setText(fuSalesBillDetailModel.getRemark());
        ET_remark.setTextColor(Color.BLACK);
        ET_remark.setPadding(10, 5, 10, 5);
        ET_remark.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_remark.setEnabled(false);

        TV_styleColor.setWidth(w_screen/8);
        TV_styleColor.setText(fuSalesBillDetailModel.getColorString());
        TV_styleColor.setTextColor(Color.BLACK);
        TV_styleColor.setSingleLine(true);
        TV_styleColor.setEllipsize(TextUtils.TruncateAt.END);

        TV_styleSize.setWidth(w_screen/8);
        TV_styleSize.setText(fuSalesBillDetailModel.getSizeString());
        TV_styleSize.setTextColor(Color.BLACK);
        TV_styleSize.setSingleLine(true);
        TV_styleSize.setEllipsize(TextUtils.TruncateAt.END);


        ET_amount.setWidth(w_screen/7);
        if (fuSalesBillDetailModel.getAmount() != null){
            ET_amount.setText(String.valueOf(fuSalesBillDetailModel.getAmount()));
        }
        ET_amount.setTextColor(Color.BLACK);
        ET_amount.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_amount.setSingleLine(true);
        ET_amount.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
        ET_amount.setEnabled(false);


        ET_price.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getPrice().toString()));
        ET_price.setWidth(w_screen/7);
        ET_price.setTextColor(Color.BLACK);
        ET_price.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_price.setSingleLine(true);
        ET_price.setInputType(InputType.TYPE_CLASS_PHONE);
        ET_price.setEnabled(false);

        ET_discount.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getDiscount().toString()));
        ET_discount.setWidth(w_screen/9);
        ET_discount.setTextColor(Color.BLACK);
        ET_discount.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_discount.setSingleLine(true);
        ET_discount.setInputType(InputType.TYPE_CLASS_PHONE);
        ET_discount.setEnabled(false);

        TV_total.setWidth(w_screen/7);
        TV_total.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getTotal().toString()));
        //TV_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        TV_total.setTextColor(Color.BLACK);


        if (fuSalesBillDetailModel.getSalesAmount() != null){
            if(fuSalesBillDetailModel.getSalesAmount()>0)
            {
                TV_styleCode.setTextColor(Color.BLUE);
                TV_styleName.setTextColor(Color.BLUE);
                TV_styleColor.setTextColor(Color.BLUE);
                TV_styleSize.setTextColor(Color.BLUE);
                TV_total.setTextColor(Color.BLUE);
                ET_amount.setTextColor(Color.BLUE);
                ET_discount.setTextColor(Color.BLUE);
                ET_price.setTextColor(Color.BLUE);
                ET_remark.setTextColor(Color.BLUE);
                TV_styleCode.setText("(补)"+TV_styleCode.getText());
            }
        }

        if (fuSalesBillDetailModel.getAmount() != null){
            if(fuSalesBillDetailModel.getAmount()<0)
            {
                TV_styleCode.setTextColor(Color.RED);
                TV_styleName.setTextColor(Color.RED);
                TV_styleColor.setTextColor(Color.RED);
                TV_styleSize.setTextColor(Color.RED);
                TV_total.setTextColor(Color.RED);
                ET_amount.setTextColor(Color.RED);
                ET_discount.setTextColor(Color.RED);
                ET_price.setTextColor(Color.RED);
                ET_remark.setTextColor(Color.RED);
            }
            ET_amount.setText(String.valueOf(fuSalesBillDetailModel.getAmount()));
        }

        return view;

    }






    private InputStream InStream = null;
    private OutputStream OutStream = null;
    private Socket socket = null;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_SalesBill:
                if (cursor != null){
                    cursor.close();
                    db.close();
                }
                finish();
                break;
            case R.id.print:
                new CommomDialog(this, R.style.dialog, "您确定需要打印？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Toast.makeText(getApplicationContext(), "正在打印，请等待...", Toast.LENGTH_SHORT).show();
                            try {
                                String qrcodewx = "";
                                BaseService baseService = new BaseService();
                                String staffForQRCODE = baseService.doSelectData(webServerUrl, "staff", "selectStaff", loginstaffid, logininvid, accessKey, accountid, staffid);
                                Logcat.show(staffForQRCODE);
                                DataUtil dataUtil = new DataUtil();
                                FuBaseModel baseModel = dataUtil.message(staffForQRCODE);
                                if(baseModel.getResultCode().intValue() ==1){
                                    FuStaffModel staffModel = dataUtil.getStaffDataForQRCODE(staffForQRCODE);
                                    qrcodewx = staffModel.getQrcodewx();
                                }else if(baseModel.getResultCode().intValue() ==0){
                                    DialogUtil.showDialog(SelectSalesBillActivity.this, baseModel.getMessage(), false);
                                }
                                final String finalQrcodewx = qrcodewx;
                                // 然后发送给子线程Handler
                                if (isWIFIPrinter.equals("0")){
//                                    Thread.sleep(500);
                                   /* Message msg = new Message();
                                    msg.what = 0x345;*/
                                    String printStr = new Print().doPrint(SelectSalesBillActivity.this, result, accountModel, numberber, staffModel, totlele, finalQrcodewx);
                                    clientThread = new ClientThread(ip,printStr);
                                    new Thread(clientThread).start();
                                    //clientThread.revHandler.sendMessage(msg);
                                }else if (isWIFIPrinter.equals("1")) {
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                socket = new Socket();
                                                SocketAddress socketAddress = new InetSocketAddress(ip,9100);
                                                socket.connect(socketAddress,5000);
                                                InStream = socket.getInputStream();
                                                OutStream = socket.getOutputStream();

                                                    PrintBillUtil printBillUtil = new PrintBillUtil(socket, OutStream, InStream,SelectSalesBillActivity.this);
                                                    final String printResult = new Print().doPrint(SelectSalesBillActivity.this, result, accountModel, numberber, staffModel, totlele, finalQrcodewx);
                                                    if("1".equals(isPrinterType80mm))
                                                    {
                                                        printBillUtil.printeForPT380(printResult);
                                                    }
                                                    else
                                                    {
                                                        if (accountModel.getColorsizemode().intValue() == 0) {
                                                            /*Message msg = handler.obtainMessage();
                                                            msg.what =4;
                                                            handler.sendMessage(msg);*/

                                                            printBillUtil.prePrint(printResult);
                                                        } else if (accountModel.getColorsizemode().intValue() == 1) {
                                                            printBillUtil.prePrint(printResult);
                                                        }
                                                    }

                                            } catch (SocketTimeoutException var4)
                                            {
                                                var4.printStackTrace();
                                                Message msg = handler.obtainMessage();
                                                msg.what =3;
                                                handler.sendMessage(msg);
                                            }catch (Exception var4) {
                                                var4.printStackTrace();
                                            }
                                        }
                                    }.start();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                DialogUtil.showDialog(SelectSalesBillActivity.this, "打印连接错误！", false);
//                                throw e;
                            }
                        }
                    }
                }).setTitle("提示").show();
                break;
            default:
                break;
        }
    }
}
