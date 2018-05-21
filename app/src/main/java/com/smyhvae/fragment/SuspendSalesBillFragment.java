package com.smyhvae.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuClientModel;
import com.smyhvae.model.FuDynamicModel;
import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.model.FuSalesBillModel;
import com.smyhvae.model.FuStyleModel;
import com.smyhvae.model.FuStylePriceTypeModel;
import com.smyhvae.myapplication.AddClientActivity;
import com.smyhvae.myapplication.AddStyleActivity;
import com.smyhvae.myapplication.ColorSizeActivity;
import com.smyhvae.myapplication.MainActivity;
import com.smyhvae.myapplication.MyApplication;
import com.smyhvae.myapplication.PaymentActivity;
import com.smyhvae.myapplication.PhotoActivity;
import com.smyhvae.myapplication.R;
import com.smyhvae.service.BaseService;
import com.smyhvae.service.PhotoService;
import com.smyhvae.service.PrintService;
import com.smyhvae.service.SalesBillService;
import com.smyhvae.service.StyleService;
import com.smyhvae.util.BigDecimalUtil;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.view.CommomDialog;
import com.smyhvae.view.MyAdapter;
import com.smyhvae.view.RecycleView.StyleACRecycleViewAdapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018/5/11.
 */

public class SuspendSalesBillFragment extends Fragment implements View.OnClickListener {



    private LinearLayout LL_ACclient, RL_ACstyle;

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String fileServerip;
    private String webServerUrl;

    private int suspendBillId =-1;

    public void setSuspendBillId(int suspendBillId)
    {
        this.suspendBillId =suspendBillId;
    }

    private TextView tv_addSalesBill;
    private View view;
    int w_screen = 0;
    int h_screen = 0;

    private boolean SrcollViewisBottom = false;


    private HorizontalScrollView HSV_price;
    private LinearLayout lay3;
    private EditText et_clientid;
    private ImageView addClient;
    private TextView tv_arrears;
    private EditText arrears;
    private EditText occurencetime;
    private EditText et_staffid;
    private EditText discount;
    private EditText remark;

    private TableLayout tableLayout;

    private ScrollView sv;

    private EditText editText;

    private ListView mListView;
    private ListView staffView;
    private RecyclerView styleView;
    //private LinearLayout listStyle;
    private List<FuBaseModel> testArray = new ArrayList<>();
    private List<FuDynamicModel> styleAcArray = new ArrayList<>();
    private MyAdapter clientAdapter;
    private MyAdapter staffAdapter;
    private StyleACRecycleViewAdapter styleAdapter;
    private String result;
    private Integer clientid;
    private Integer staffid;
    private Integer styleid;
    private String priceTypeString;
    private Integer priceTypeid;
    private Integer clientPriceTypeId;

    private List<FuSalesBillDetailModel> fuSalesBillDetailModelList = new ArrayList<>();//追加的fuSalesBillList
    private List<FuBaseModel> priceTypeList = new ArrayList<>();

    private BigDecimalUtil bigDecimalUtil = new BigDecimalUtil();
    private FuSalesBillModel salesBillModel = new FuSalesBillModel();

    private boolean flag = true;
    private boolean doClientForAc = true;
    private Handler mhandler = null;

    private TextView sum_id;
    private TextView sumamountdetail;
    private TextView sum_num;
    private TextView sum_money;

    private Button BTN_suspendSalesBill;

    private int numberber = 0;
    private BigDecimal totlele = new BigDecimal(0);
    private int salesAmount = 0;
    private int cancelAmount = 0;
    private List<FuSalesBillDetailModel> templist = new ArrayList<>();
    private RadioGroup radioGroup;

    private Handler viewHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1001://延时sv拉到最低操作
                    sv.fullScroll(ScrollView.FOCUS_DOWN);
                    tableLayout.getChildAt(tableLayout.getChildCount() - 1).findViewById(R.id.ET_amount).requestFocus();
                    break;
                case 1002:
                    sv.fullScroll(ScrollView.FOCUS_DOWN);
                    tableLayout.getChildAt(tableLayout.getChildCount() - 1).findViewById(R.id.ET_amount).requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    break;
                case 1003:
                    try
                    {
                        styleAcArray.get(msg.arg1).setImage(new BitmapDrawable((Bitmap) msg.obj));
                        styleAdapter.notifyItemChanged(msg.arg1);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };


    private Handler handler1 = new Handler() {

        public void handleMessage(Message msg) {
            DataUtil dataUtil = new DataUtil();
            FuBaseModel baseModel = dataUtil.message(msg.obj.toString());
            if (baseModel.getResultCode().intValue() == 1) {
                priceTypeList = dataUtil.getList(msg.obj.toString());
                radioGroup = new RadioGroup(getActivity());
                radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                for (int i = 0; i < priceTypeList.size(); i++) {
                    final FuBaseModel fuBaseModel = priceTypeList.get(i);
                    final RadioButton radioButton = new RadioButton(getActivity());
                    radioButton.setText(fuBaseModel.getName());
                    radioGroup.addView(radioButton);
                    radioButton.setId(fuBaseModel.getId());
                    if (i == 0) {
                        priceTypeString = radioButton.getText().toString();
                        priceTypeid = fuBaseModel.getId();
                    }

                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            RadioButton cb = (RadioButton) buttonView;
                            if (isChecked) {
                                priceTypeString = cb.getText().toString();
                                priceTypeid = fuBaseModel.getId();
                            }
                        }
                    });
                }
                radioGroup.check(priceTypeid);
                if (clientPriceTypeId != null) {
                    radioGroup.check(clientPriceTypeId);
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        //Log.i("TestLog", "点击i为" + i);
                        int sum = tableLayout == null ? 0 : tableLayout.getChildCount();
                        for (int j = 0; j < sum; j++) {
                            try {
                                //Log.i("TestLog", salesBillDetailList.get(j).toString());
                                //BigDecimal price = salesBillDetailList.get(j).getFuStylePriceTypeModelList().get(i).getPrice();
                                //((EditText)tableLayout.getChildAt(j).findViewById(R.id.ET_price)).setText(price.toString());
                                List<FuStylePriceTypeModel> fuStylePriceTypeModelList = salesBillDetailList.get(j).getFuStylePriceTypeModelList();
                                ((EditText) tableLayout.getChildAt(j).findViewById(R.id.ET_price)).setText("0");
                                for (int k = 0; k < fuStylePriceTypeModelList.size(); k++) {
                                    if (i == fuStylePriceTypeModelList.get(k).getPricetypeid()) {
                                        ((EditText) tableLayout.getChildAt(j).findViewById(R.id.ET_price)).setText(bigDecimalUtil.getStringUtil(fuStylePriceTypeModelList.get(k).getPrice().toString()));
                                    }
                                }
                            } catch (Exception e) {
                                Log.i("TestLog", "价格类型转换失败");
                                e.printStackTrace();
                            }
                        }
                    }
                });
                //lay3.addView(radioGroup);
                HSV_price.addView(radioGroup);
            } else if (baseModel.getResultCode().intValue() == 0) {
                DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
            }
        }
    };


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        application = (MyApplication) getActivity().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        fileServerip = application.getFileserverip();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        Log.i("屏幕宽度", "屏幕尺寸2：宽度 = " + w_screen + "高度 = " + h_screen + "密度 = " + dm.densityDpi);
        view = inflater.inflate(R.layout.salesbill_listtable, container, false);
        RL_ACstyle = view.findViewById(R.id.RL_ACstyle);
        LL_ACclient = view.findViewById(R.id.LL_ACclient);
        sv = view.findViewById(R.id.sv);
        tv_addSalesBill = view.findViewById(R.id.tv_addSalesBill);
        et_clientid = view.findViewById(R.id.et_clientid);
        et_clientid.setWidth(w_screen / 4);
        view.findViewById(R.id.back_SalesBill).setVisibility(View.VISIBLE);
        view.findViewById(R.id.back_SalesBill).setOnClickListener(this);
        /*((TextView)view.findViewById(R.id.TV_clearData)).setText("返回");
        view.findViewById(R.id.TV_clearData).setVisibility(View.VISIBLE);
        view.findViewById(R.id.TV_clearData).setOnClickListener(this);*/

        addClient = view.findViewById(R.id.addClient);
        tv_arrears = view.findViewById(R.id.tv_arrears);
        arrears = view.findViewById(R.id.arrears);
        arrears.setWidth(w_screen / 4);
        //arrears.setPadding(20, 20, 10, 20);
        arrears.setTextColor(Color.RED);
        occurencetime = view.findViewById(R.id.occurencetime);
        occurencetime.setWidth(w_screen / 4);
        occurencetime.setFocusable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        occurencetime.setText(sdf.format(new Date()));
        et_staffid = view.findViewById(R.id.et_staffid);
        et_staffid.setWidth(w_screen / 4);
        et_staffid.setPadding(20, 20, 10, 10);
        discount = view.findViewById(R.id.discount);

        BTN_suspendSalesBill = view.findViewById(R.id.BTN_suspendSalesBill);
        BTN_suspendSalesBill.setOnClickListener(this);

        discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try
                {
                    double dis = Double.valueOf(discount.getText().toString());
                   if(tableLayout!=null)
                   {
                       for(int j = 0 ; j < tableLayout.getChildCount(); j++)
                       {
                           ((EditText)tableLayout.getChildAt(j).findViewById(R.id.ET_discount)).setText(dis+"");
                       }
                   }
                    salesBillModel.setDiscount(new BigDecimal(dis));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        discount.setInputType(InputType.TYPE_CLASS_PHONE);

        HSV_price = view.findViewById(R.id.HSV_price);
        lay3 = view.findViewById(R.id.lay3);
        remark = view.findViewById(R.id.remark);
        remark.setWidth(w_screen / 2 - 100);
        remark.setPadding(20, 20, 10, 10);

        view.findViewById(R.id.TV_addStyle).setOnClickListener(this);

        new Thread() {
            public void run() {
                try {
                    BaseService baseService = new BaseService();
                    String result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "priceType", "fuPriceTypeList");
                    Logcat.show(result);
                    Message msg = Message.obtain();
                    msg.obj = result;
                    handler1.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        RelativeLayout layout = view.findViewById(R.id.salesbill_listtable);
        //客户AC布局
        LinearLayout listClient = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        lparams.setMargins(50, 100, 50, 0);
        listClient.setOrientation(LinearLayout.VERTICAL);
        listClient.setLayoutParams(lparams);
        listClient.setBackgroundColor(Color.parseColor("#FFFFFF"));
        mListView = new ListView(getActivity());
        mListView.setVisibility(View.GONE);
        mListView.setBackground(getResources().getDrawable(R.drawable.list_bg));
        LL_ACclient.addView(mListView);
        //listClient.addView(mListView);
        //layout.addView(listClient);

        //店员AC布局
        LinearLayout listStaff = new LinearLayout(getActivity());
        LinearLayout.LayoutParams staffparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        staffparams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        staffparams.setMargins(w_screen / 3 + 100, 150, 10, 0);
        listStaff.setOrientation(LinearLayout.VERTICAL);
        listStaff.setLayoutParams(staffparams);
        listStaff.setBackgroundColor(Color.parseColor("#FFFFFF"));
        staffView = new ListView(getActivity());
        staffView.setVisibility(View.GONE);
        staffView.setBackground(getResources().getDrawable(R.drawable.list_bg));
        LL_ACclient.addView(staffView);
        //listStaff.addView(staffView);
        //layout.addView(listStaff);

        //输入款号
        LinearLayout acLay = view.findViewById(R.id.acLay);
        editText = view.findViewById(R.id.stylecodeForAc);
        editText.setWidth(w_screen / 3);
        editText.setPadding(20, 20, 10, 20);

        editText.setRawInputType(Configuration.KEYBOARD_QWERTY);

        LinearLayout.LayoutParams styleparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        styleparams.gravity = Gravity.CENTER_HORIZONTAL;


        styleView = new RecyclerView(getActivity());
        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        styleView.setLayoutManager(layoutManager);
        //styleView.setItemAnimator(new DefaultItemAnimator());


        //styleView.setVisibility(View.GONE);
        styleView.setBackground(getResources().getDrawable(R.drawable.list_bg));
        //+listStyle.addView(styleView);
        RL_ACstyle.addView(styleView);

        sum_id = view.findViewById(R.id.sum_id);
        sumamountdetail = view.findViewById(R.id.sumamountdetail);
        sum_num = view.findViewById(R.id.sum_num);
        sum_money = view.findViewById(R.id.sum_money);

        getfoottable();
        if(suspendBillId>0)
            SetSuspendSalesBill(suspendBillId);
        return view;
    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    public static final int REQUEST_CODE = 1001;
    public static final int CODE = 0;
    public static final int CLIENT_CODE = 2;
    public final int ADD_CODE = 3;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            fuSalesBillDetailModelList = (List<FuSalesBillDetailModel>) data.getSerializableExtra("salesBillDetailList");
            appendDetailItemTable();

            if (salesBillDetailList != null) {
                sum_id.setText("总计 " + salesBillDetailList.size());
                numberber = 0;
                totlele = new BigDecimal(0);
                salesAmount = 0;
                cancelAmount = 0;
                for (FuSalesBillDetailModel fuSalesBillDetailModel : salesBillDetailList) {
                    numberber = numberber + fuSalesBillDetailModel.getAmount();
                    totlele = totlele.add(fuSalesBillDetailModel.getTotal());
                    if (fuSalesBillDetailModel.getAmount().intValue() > 0) {
                        salesAmount = salesAmount + fuSalesBillDetailModel.getAmount();
                    }
                    if (fuSalesBillDetailModel.getAmount().intValue() < 0) {
                        cancelAmount = cancelAmount + fuSalesBillDetailModel.getAmount();
                    }
                }
                sum_num.setText(numberber + "");
                sum_money.setText(bigDecimalUtil.getStringUtil(totlele + ""));
                sumamountdetail.setText("(" + salesAmount + "," + cancelAmount + ")");

                if (salesBillModel != null) {
                    salesBillModel.setTotal(totlele);
                }

            }
        } else if (requestCode == REQUEST_CODE && resultCode == CODE) {

            try {
                final String data1 = data.getStringExtra("data");
                final int numberber = data.getExtras().getInt("numberber");
                final BigDecimal totlele = (BigDecimal) data.getExtras().get("totlele");

                new CommomDialog(getContext(), R.style.dialog, "您确定要打印吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Intent service = new Intent(getContext(), PrintService.class);
                            service.putExtra("data", data1);
                            service.putExtra("numberber", numberber);
                            service.putExtra("totlele", totlele);
                            getContext().startService(service);
                        }
                    }
                }).setTitle("保存成功").show();
                ((MainActivity)getActivity()).initFragment(1);
                ((MainActivity)getActivity()).getSalesBillFragment().refreshRecycleView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_CODE && resultCode == CLIENT_CODE) {
            FuClientModel clientModel;
            clientModel = (FuClientModel) data.getSerializableExtra("fuClientModel");
            doClientForAc = false;
            et_clientid.setText(clientModel.getName());
            clientid = clientModel.getId();
            salesBillModel.setClientid(clientid);
            salesBillModel.setClientString(clientModel.getName());
            arrears.setText("0");
            salesBillModel.setArrears(new BigDecimal(0));
            if (clientModel.getStaffName() != null && !("").equals(clientModel.getStaffName())) {
                flag = false;
                et_staffid.setText(clientModel.getStaffName());
                salesBillModel.setStaffid(clientModel.getStaffid());
                salesBillModel.setStaffString(clientModel.getStaffName());
            }
            discount.setText(bigDecimalUtil.getStringUtil(clientModel.getDiscount().toString()));
            salesBillModel.setDiscount(clientModel.getDiscount());
            clientPriceTypeId = clientModel.getPricetypeid();
            if (clientPriceTypeId != null) {
                radioGroup.check(clientPriceTypeId);
            }
        } else if (requestCode == REQUEST_CODE && resultCode == ADD_CODE) {

            if (application.getFuAccountModel().getColorsizemode().intValue() == 0) {
                FuStyleModel styleModel = getData(data.getIntExtra("styleId", 0));
                Intent intent = new Intent(getActivity(), ColorSizeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("styleModel", styleModel);
                intent.putExtra("priceTypeString", priceTypeString);
                intent.putExtra("discount", discount.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CODE);
                editText.setText("");
                editText.setFocusable(true);
            } else {
                FuStyleModel styleModel = getData(data.getIntExtra("styleId", 0));
                setSalesBillDetailList(styleModel);
                appendDetailItemTable();
                editText.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message msg = viewHandler.obtainMessage();
                        msg.what = 1002;
                        viewHandler.sendMessage(msg);
                    }
                }).start();
            }
            //Log.i("TestLog",fuSalesBillDetailModel.toString());
        }

        ACShowHide(false, false);
    }

    public void appendDetailItemTable() {
        ViewTabLay viewTabLay = new ViewTabLay(view);
        tableLayout = viewTabLay.tablay;
        for (FuSalesBillDetailModel fuSalesBillDetailModel : fuSalesBillDetailModelList) {
            HorizontalScrollView hsv = new HorizontalScrollView(getActivity());
            hsv.setHorizontalScrollBarEnabled(false);
            LinearLayout layContent = new LinearLayout(getActivity());
            layContent.setOrientation(LinearLayout.VERTICAL);
            View convertView = addRow(fuSalesBillDetailModel);
            tableLayout.addView(convertView);
        }

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_addSalesBill.setOnClickListener(this);
        addClient.setOnClickListener(this);

        //时间选项卡
        final Calendar c = Calendar.getInstance();
        occurencetime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        occurencetime.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //客户AC
        clientAdapter = new MyAdapter(getActivity(), testArray);
        mListView.setAdapter(clientAdapter);// 设置Adapter，初始值为空

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clientid = clientAdapter.getItem(position).getId();
                BaseService baseService = new BaseService();
                result = baseService.doSelectClient(webServerUrl, loginstaffid, logininvid, accessKey, accountid, clientid);
                Logcat.show(result);
                DataUtil dataUtil = new DataUtil();
                FuBaseModel baseModel = dataUtil.message(result);
                if (baseModel.getResultCode().intValue() == 1) {
                    FuClientModel clientModel = dataUtil.getClientData(result);
                    et_clientid.setText(clientModel.getName());

                    if (clientModel.getArrears().intValue() < 0) {
                        tv_arrears.setText("余额");
                        arrears.setText(String.valueOf(Math.abs(clientModel.getArrears().intValue())));
                        arrears.setTextColor(Color.parseColor("#006400"));
                    } else {
                        tv_arrears.setText("欠款");
                        arrears.setText(String.valueOf(clientModel.getArrears().intValue()));
                        arrears.setTextColor(Color.RED);
                    }

//                    salesBillModel = new FuSalesBillModel();
                    salesBillModel.setClientid(clientModel.getId());
                    salesBillModel.setClientString(clientModel.getName());
                    salesBillModel.setArrears(clientModel.getArrears());
                    if (clientModel.getStaffName() != null && !("").equals(clientModel.getStaffName())) {
                        flag = false;
                        et_staffid.setText(clientModel.getStaffName());
                        salesBillModel.setStaffid(clientModel.getStaffid());
                        salesBillModel.setStaffString(clientModel.getStaffName());
                    }
                    discount.setText(bigDecimalUtil.getStringUtil(clientModel.getDiscount().toString()));
                    salesBillModel.setDiscount(clientModel.getDiscount());
                    ACShowHide(false, false);
                } else if (baseModel.getResultCode().intValue() == 0) {
                    DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
                }
            }
        });


        et_clientid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    et_clientid.setText(salesBillModel.getClientString() == null ? "" : salesBillModel.getClientString());
                    ACShowHide(false, false);

                }
            }
        });

        //客户编辑框 AC页面状态下 点击软键盘的确认按钮 默认选择第一个
        et_clientid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (mListView.getVisibility() == View.VISIBLE && testArray != null && testArray.size() > 0) {
                    if (actionId == EditorInfo.IME_ACTION_SEND
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {

                        Log.i("TestLog", "确定选择第一个列表");
                        clientid = clientAdapter.getItem(0).getId();
                        BaseService baseService = new BaseService();
                        result = baseService.doSelectClient(webServerUrl, loginstaffid, logininvid, accessKey, accountid, clientid);
                        Logcat.show(result);
                        DataUtil dataUtil = new DataUtil();
                        FuBaseModel baseModel = dataUtil.message(result);
                        if (baseModel.getResultCode().intValue() == 1) {
                            FuClientModel clientModel = dataUtil.getClientData(result);
                            et_clientid.setText(clientModel.getName());

                            if (clientModel.getArrears().intValue() < 0) {
                                tv_arrears.setText("余额");
                                arrears.setText(String.valueOf(Math.abs(clientModel.getArrears().intValue())));
                                arrears.setTextColor(Color.parseColor("#006400"));
                            } else {
                                tv_arrears.setText("欠款");
                                arrears.setText(String.valueOf(clientModel.getArrears().intValue()));
                                arrears.setTextColor(Color.RED);
                            }

                            //salesBillModel = new FuSalesBillModel();
                            salesBillModel.setClientid(clientModel.getId());
                            salesBillModel.setClientString(clientModel.getName());
                            salesBillModel.setArrears(clientModel.getArrears());
                            if (clientModel.getStaffName() != null && !("").equals(clientModel.getStaffName())) {
                                flag = false;
                                et_staffid.setText(clientModel.getStaffName());
                                salesBillModel.setStaffid(clientModel.getStaffid());
                                salesBillModel.setStaffString(clientModel.getStaffName());
                            }
                            discount.setText(bigDecimalUtil.getStringUtil(clientModel.getDiscount().toString()));
                            salesBillModel.setDiscount(clientModel.getDiscount());
                            ACShowHide(false, false);
                        } else if (baseModel.getResultCode().intValue() == 0) {
                            DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
                        }
                        //处理事件
                    }
                }
                return false;
            }
        });


        et_clientid.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testArray = new ArrayList<>();// 每次输入的时候，重新初始化数据列表
                if (!TextUtils.isEmpty(et_clientid.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    if (doClientForAc) {
                        String input = et_clientid.getText().toString().trim();
                        testArray = getAC("clientListForAC", input, null);
                        clientid = null;

                    } else {
                        doClientForAc = true;
                    }
                }
                else
                {
                    arrears.setText("");
                }
                clientAdapter.refreshData(testArray);// Adapter刷新数据
                ACShowHide(true, false);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.length() == 0) {
                    ACShowHide(false, false);
                    salesBillModel.setClientString(null);
                    salesBillModel.setClientid(null);
                }
            }
        });

        //店员AC
        staffAdapter = new MyAdapter(getActivity(), testArray);
        staffView.setAdapter(staffAdapter);// 设置Adapter，初始值为空

        staffView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                staffid = staffAdapter.getItem(position).getId();
                et_staffid.setText(staffAdapter.getItem(position).getName());
                salesBillModel.setStaffid(staffid);
                salesBillModel.setStaffString(et_staffid.getText().toString());
                ACShowHide(false, false);
            }
        });

        //店员编辑框 AC页面状态下 点击软键盘的确认按钮 默认选择第一个
        et_staffid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {

                if (staffView.getVisibility() == View.VISIBLE && testArray != null && testArray.size() > 0) {

                    if (actionId == EditorInfo.IME_ACTION_SEND
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        staffid = staffAdapter.getItem(0).getId();
                        et_staffid.setText(staffAdapter.getItem(0).getName());
                        salesBillModel.setStaffid(staffid);
                        salesBillModel.setStaffString(et_staffid.getText().toString());
                        ACShowHide(false, false);

                    }

                }

                return false;
            }
        });
        et_staffid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    et_staffid.setText(salesBillModel.getStaffString() == null ? "" : salesBillModel.getStaffString());
                    ACShowHide(false, false);
                }
            }
        });
        et_staffid.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testArray = new ArrayList<>();// 每次输入的时候，重新初始化数据列表
                if (!TextUtils.isEmpty(et_staffid.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    if (flag) {
                        String input = et_staffid.getText().toString().trim();
                        testArray = getAC("staffListForAC", input, null);
                    } else {
                        flag = true;
                    }
                }
                staffAdapter.refreshData(testArray);// Adapter刷新数据
                ACShowHide(false, true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.length() == 0) {
                    ACShowHide(false, false);
                    salesBillModel.setStaffid(null);
                    salesBillModel.setStaffString(null);
                }
            }
        });

        //款AC
        styleAdapter = new StyleACRecycleViewAdapter(getActivity(), styleAcArray);

        styleAdapter.setItemClickListener(new StyleACRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FuDynamicModel fuDynamicModel, int position) {
                styleid = fuDynamicModel.getId();
                //styleView.setVisibility(View.GONE);
                RL_ACstyle.setVisibility(View.GONE);
                FuStyleModel styleModel = getData(styleid);
                if (application.getFuAccountModel().getColorsizemode().intValue() == 0) {
                    Intent intent = new Intent(getActivity(), ColorSizeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("styleModel", styleModel);
                    intent.putExtra("priceTypeString", priceTypeString);
                    intent.putExtra("discount", discount.getText().toString());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE);
                    editText.setText("");
                    editText.setFocusable(true);
                } else if (application.getFuAccountModel().getColorsizemode().intValue() == 1) {
                    setSalesBillDetailList(styleModel);
                    appendDetailItemTable();
                    editText.setText("");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = viewHandler.obtainMessage();
                            message.what = 1001;
                            viewHandler.sendMessage(message);
                        }
                    }).start();
                }
            }
        });

        styleView.setAdapter(styleAdapter);// 设置Adapter，初始值为空

        /*styleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// listView点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });*/


        //为避免获取焦点后撤回软键盘，滑动布局不在最底部，然后在点击输入，获取不到焦点事件，但是会获取点击事件
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!SrcollViewisBottom) {
                    sv.fullScroll(ScrollView.FOCUS_DOWN);
                    SrcollViewisBottom = true;
                    editText.requestFocus();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                SrcollViewisBottom = false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (!SrcollViewisBottom) {
                        sv.fullScroll(ScrollView.FOCUS_DOWN);
                        SrcollViewisBottom = true;
                        editText.requestFocus();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                    SrcollViewisBottom = false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                           /* InputMethodManager inputmanger = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                            inputmanger.hideSoftInputFromWindow(, 0);*/

                    SrcollViewisBottom = false;
                }
            });
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*if(!detailCountisNull)
                {*/
                styleAcArray = new ArrayList<>();// 每次输入的时候，重新初始化数据列表
                if (!TextUtils.isEmpty(editText.getText().toString())) {// 判断输入内容是否为空，为空则跳过
                    String input = editText.getText().toString().trim();
                    styleAcArray = getACforStyle("styleListForAC", input, 0);
                }

                styleAdapter.refreshData(styleAcArray);// Adapter刷新数据
                //styleView.setVisibility(View.VISIBLE);
                //RL_ACstyle.setBackgroundColor(getResources().getColor(R.color.border_clo));
                RL_ACstyle.setVisibility(View.VISIBLE);
               /* }
                else
                {
                    detailCountisNull = false;
                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    RL_ACstyle.setVisibility(View.GONE);
                }
            }
        });

    }

    public void ACShowHide(boolean clientACShow, boolean staffACShow) {
        if (clientACShow == true) {
            Log.i("TestLog", "mListView显示");
            LL_ACclient.setVisibility(View.VISIBLE);
            staffView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }
        if (staffACShow == true) {
            Log.i("TestLog", "staffView显示");
            LL_ACclient.setVisibility(View.VISIBLE);
            staffView.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }
        if (clientACShow == false && staffACShow == false)//两个都不显示
        {
            LL_ACclient.setVisibility(View.GONE);
            staffView.setVisibility(View.GONE);
            mListView.setVisibility(View.GONE);
        }
    }

    public List<FuDynamicModel> getACforStyle(String methodid, final String input, Integer justForName){
        BaseService baseService = new BaseService();
        result = baseService.doListForAC(webServerUrl, methodid, loginstaffid, logininvid, accessKey, accountid, input, justForName);
        Logcat.show("TestLog---"+result);
        DataUtil dataUtil = new DataUtil();
        List<FuDynamicModel> dynamicModelList = new ArrayList<>();
        FuBaseModel baseModel = dataUtil.message(result);
        if(baseModel !=null)
        {
            if(baseModel.getResultCode().intValue() ==1){
                dynamicModelList = dataUtil.getDataListForStyleAC(result);

                final List<FuDynamicModel> temModel = dynamicModelList;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PhotoService photoService = new PhotoService();
                        for(int i= 0;i<temModel.size();i++)
                        {
                            if(temModel.get(i).getImageId()!=null)
                            {
                                if(i>20)
                                    break;
                                Bitmap bitmap = photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,temModel.get(i).getImageId(),1);

                                if(input.equals(editText.getText().toString()))
                                {
                                    if(bitmap!=null)
                                    {
                                        Message message = viewHandler.obtainMessage();
                                        message.what = 1003;
                                        message.arg1 = i;
                                        message.obj = bitmap;
                                        viewHandler.sendMessage(message);
                                        Log.i("TestLog","第"+i+"张图片" );
                                    }
                                }
                                else
                                {
                                    break;
                                }
                            }
                        }
                    }
                }).start();

            }else if(baseModel.getResultCode().intValue() ==0){
                DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
            }
        }

        return dynamicModelList;
    }

    public List<FuBaseModel> getAC(String methodid, String input, Integer justForName){

        BaseService baseService = new BaseService();
        result = baseService.doListForAC(webServerUrl, methodid, loginstaffid, logininvid, accessKey, accountid, input, justForName);
        Logcat.show("TestLog---"+result);
        DataUtil dataUtil = new DataUtil();
        FuBaseModel baseModel = dataUtil.message(result);
        List<FuBaseModel> baseModelList = new ArrayList<>();
        if(baseModel.getResultCode().intValue() ==1){
            baseModelList = dataUtil.getDataListForAC(result);
            /*PhotoService photoService = new PhotoService();
            for(int i= 0;i<baseModelList.size();i++)
            {
                if(baseModelList.get(i).getImageId()!=null)
                {
                    Bitmap bitmap = photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,styleid,1);
                    baseModelList.get(i).setImage(new BitmapDrawable(bitmap));
                }
            }*/
        }else if(baseModel.getResultCode().intValue() ==0){
            DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
        }
        return baseModelList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_SalesBill:

                ((MainActivity)getActivity()).initFragment(1);

                break;
            case R.id.tv_addSalesBill:
                boolean complete = true;

                for(FuSalesBillDetailModel fuSalesBillDetailModel :salesBillDetailList)
                {
                    if (fuSalesBillDetailModel.getAmount()==null)
                    {
                        complete = false;
                        new  AlertDialog.Builder(getContext()) .setTitle("提示" ) .setMessage("单据明细未填写完整!" )
                                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                }) .show();
                        break;
                    }
                }
                if(complete)
                {
                    if (!TextUtils.isEmpty(remark.getText().toString())){
                        salesBillModel.setRemark(remark.getText().toString());
                    }
                    if (!TextUtils.isEmpty(discount.getText().toString())){
                        salesBillModel.setDiscount(new BigDecimal(discount.getText().toString()));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date time = sdf.parse(occurencetime.getText().toString());
                        salesBillModel.setOccurrencetime(time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    salesBillModel.setPricetypeid(priceTypeid);
                    Intent intent = new Intent(getActivity(), PaymentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("salesBillModel", salesBillModel);
                    intent.putExtra("salesBillDetailList", (Serializable) salesBillDetailList);
                    intent.putExtra("suspendBillId",suspendBillId);
                    Log.i("TestLog","客户id--"+salesBillModel.getClientid()+"--  客户名称--"+salesBillModel.getClientString()+"--");
                    Log.i("TestLog","店员id--"+salesBillModel.getStaffid()+"--  店员名称--"+salesBillModel.getStaffString()+"--");
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            case R.id.addClient:
                Intent intentForClient=new Intent(getActivity(), AddClientActivity.class);
                Bundle bundleForClient = new Bundle();
                bundleForClient.putSerializable("priceTypeList", (Serializable) priceTypeList);
                intentForClient.putExtras(bundleForClient);
                startActivityForResult(intentForClient, REQUEST_CODE);
                break;

            case R.id.TV_addStyle:
                Intent intent1 = new Intent(getActivity(), AddStyleActivity.class);
                intent1.putExtra("requestActivity","SuspendSalesBillFragment");
                startActivityForResult(intent1,REQUEST_CODE);
                break;

            case R.id.BTN_suspendSalesBill:

                new CommomDialog(getContext(), R.style.dialog, "您确定要挂单吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            SalesBillService billService = new SalesBillService();
                            String result = billService.doSuspendSalesBill(webServerUrl, loginstaffid, logininvid, accessKey, accountid,salesBillModel,salesBillDetailList,suspendBillId);
                            DataUtil dataUtil = new DataUtil();
                            FuBaseModel baseModel = dataUtil.message(result);
                            if(baseModel.getResultCode().intValue() ==1){
                                Toast.makeText(SuspendSalesBillFragment.this.getContext(),"挂单成功",Toast.LENGTH_SHORT).show();
                                ((MainActivity)getActivity()).initFragment(1);
                                ((MainActivity)getActivity()).getSalesBillFragment().refreshRecycleView();
                            }
                        }
                    }
                }).setTitle("提示").show();

                break;
            default:
                break;
        }
    }

    public FuStyleModel getData(int styleid){
        StyleService styleService = new StyleService();
        String result = styleService.doSelectStyle(webServerUrl, loginstaffid, logininvid, accessKey, accountid, styleid);
        Logcat.show(result);
        DataUtil dataUtil = new DataUtil();
        FuBaseModel baseModel = dataUtil.message(result);
        FuStyleModel styleModel = new FuStyleModel();
        if(baseModel.getResultCode().intValue() ==1){
            styleModel = dataUtil.getStyleData(result);
        }else if(baseModel.getResultCode().intValue() ==0){
            DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
        }
        return styleModel;
    }

    List<FuSalesBillDetailModel> salesBillDetailList = new ArrayList<>();//所有的FuSalesBillList
    int itemId  =0;

    public View addRow(final FuSalesBillDetailModel fuSalesBillDetailModel)   {
        final FuSalesBillDetailModel salesBillDetailModel = new FuSalesBillDetailModel();
        View view = View.inflate(getContext(),R.layout.item_salesbill,null);
        ImageView IV_logo;
        final TextView TV_styleCode,TV_styleName,TV_styleColor,TV_styleSize,TV_total,TV_delete;
        final EditText ET_remark,ET_amount,ET_price,ET_discount;

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
                        Intent intent = new Intent(getContext(), PhotoActivity.class);
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
        //TV_styleName.setPadding(0,0,40,0);
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
        ET_amount.setTextColor(Color.BLACK);
        ET_amount.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_amount.setSingleLine(true);
        ET_amount.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
        if (application.getFuAccountModel().getColorsizemode().intValue()==1){
            ET_amount.setFocusable(true);
            ET_amount.setFocusableInTouchMode(true);
        }

        ET_price.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getPrice().toString()));
        ET_price.setWidth(w_screen/7);
        ET_price.setTextColor(Color.BLACK);
        ET_price.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_price.setSingleLine(true);
        ET_price.setInputType(InputType.TYPE_CLASS_PHONE);


        ET_discount.setWidth(w_screen/9);
        ET_discount.setTextColor(Color.BLACK);
        ET_discount.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
        ET_discount.setSingleLine(true);
        ET_discount.setInputType(InputType.TYPE_CLASS_PHONE);


        TV_total.setWidth(w_screen/7);
        TV_total.setText(bigDecimalUtil.getStringUtil(fuSalesBillDetailModel.getTotal().toString()));
        //TV_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        TV_total.setTextColor(Color.BLACK);
        TV_total.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TV_total.getLineCount()>1)
                {
                    TV_total.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
                }
            }
        });
        //TV_total.setSingleLine(true);
        //TV_total.setEllipsize(TextUtils.TruncateAt.END);

        TV_delete.setTag(itemId);

        salesBillDetailModel.setPrice(fuSalesBillDetailModel.getPrice());
        salesBillDetailModel.setAmount(fuSalesBillDetailModel.getAmount());
        salesBillDetailModel.setTotal(fuSalesBillDetailModel.getTotal());


        if (fuSalesBillDetailModel.getAmount() != null){
            ET_amount.setText(String.valueOf(fuSalesBillDetailModel.getAmount()));
            if(fuSalesBillDetailModel.getAmount()<0)
            {
                TV_styleName.setTextColor(Color.RED);
                TV_styleCode.setTextColor(Color.RED);
                TV_styleColor.setTextColor(Color.RED);
                TV_styleSize.setTextColor(Color.RED);
                ET_discount.setTextColor(Color.RED);
                ET_remark.setTextColor(Color.RED);
                ET_price.setTextColor(Color.RED);
                ET_amount.setTextColor(Color.RED);
                TV_total.setTextColor(Color.RED);
            }
        }

        ET_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if("".equals(ET_amount.getText().toString().trim())||ET_amount.getText().toString().trim().length()==0)
                    {
                        if(!SrcollViewisBottom)
                        {
                            new  AlertDialog.Builder(getContext()) .setTitle("提示" ) .setMessage("数量未填写！" )
                                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int i) {
                                            dialog.dismiss();
                                        }
                                    }) .show();
                        }
                    }
                    try
                    {
                        new BigDecimal(ET_amount.getText().toString());
                    }catch (Exception e)
                    {
                        if(ET_amount.getText().length()>0)
                            ET_amount.setText("0");
                    }
                }
            }
        });

        ET_price.addTextChangedListener(new TextWatcher() {
            String lastbig;
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                try
                {
                    if (!("").equals(ET_price.getText().toString())  && !("").equals(ET_amount.getText().toString()) && !("").equals(ET_discount.getText().toString())) {
                        TV_total.setText(String.valueOf(new BigDecimal(ET_amount.getText().toString()).multiply(new BigDecimal(ET_price.getText().toString())).multiply(new BigDecimal(ET_discount.getText().toString()))));
                        salesBillDetailModel.setTotal(new BigDecimal(ET_amount.getText().toString()).multiply(new BigDecimal(ET_price.getText().toString())).multiply(new BigDecimal(ET_discount.getText().toString())));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    } else {
                        TV_total.setText("");
                        salesBillDetailModel.setTotal(new BigDecimal(0));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    }

                    if (!("").equals(ET_price.getText().toString())) {
                        salesBillDetailModel.setPrice(BigDecimal.valueOf(Integer.parseInt(ET_price.getText().toString() + "")));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);

                    } else {
                        salesBillDetailModel.setPrice(new BigDecimal(0));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    ET_price.setText(lastbig.toString());
                    ET_price.setSelection(ET_price.getText().length());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if(arg0!=null)
                    lastbig= arg0.toString();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }

        });
        ET_discount.addTextChangedListener(new TextWatcher() {
            String lastbig;
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                try{
                    if (!("").equals(ET_price.getText().toString())  && !("").equals(ET_amount.getText().toString()) && !("").equals(ET_discount.getText().toString())) {
                        TV_total.setText(String.valueOf(new BigDecimal(ET_amount.getText().toString()).multiply(new BigDecimal(ET_price.getText().toString())).multiply(new BigDecimal(ET_discount.getText().toString()))));
                        salesBillDetailModel.setTotal(new BigDecimal(ET_amount.getText().toString()).multiply(new BigDecimal(ET_price.getText().toString())).multiply(new BigDecimal(ET_discount.getText().toString())));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    } else {
                        TV_total.setText("");
                        salesBillDetailModel.setTotal(new BigDecimal(0));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    }

                    if (!("").equals(ET_discount.getText().toString())) {
                        salesBillDetailModel.setDiscount(new BigDecimal(ET_discount.getText().toString()));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);

                    } else {
                        salesBillDetailModel.setDiscount(null);
                        salesBillDetailModel.setPrice(new BigDecimal(0));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    }
                }catch (Exception e)
                {

                    ET_discount.setText(lastbig.toString());
                    ET_discount.setSelection(ET_discount.getText().length());
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if(arg0!=null)
                    lastbig = arg0.toString();
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        ET_amount.addTextChangedListener(new TextWatcher() {
            //String lastbig;
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                try
                {
                    if (!("").equals(ET_price.getText().toString()) && !("").equals(ET_amount.getText().toString()) && !("").equals(ET_discount.getText().toString())) {
                        if (ET_amount.getText().toString().indexOf("-") != -1 && ET_amount.getText().length()==1){
                            Log.d("sfn", ET_amount.getText().toString());
                        }else {
                            TV_total.setText(String.valueOf(new BigDecimal(ET_price.getText().toString()).multiply(new BigDecimal(Integer.parseInt(ET_amount.getText().toString().trim()))).multiply(new BigDecimal(ET_discount.getText().toString()))));
                            salesBillDetailModel.setTotal(new BigDecimal(ET_price.getText().toString()).multiply(new BigDecimal(Integer.parseInt(ET_amount.getText().toString().trim()))).multiply(new BigDecimal(ET_discount.getText().toString())));
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = salesBillDetailList;
                            mhandler.sendMessage(msg);
                        }

                    } else {
                        TV_total.setText("");
                        salesBillDetailModel.setTotal(new BigDecimal(0));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    }

                    if (!("").equals(ET_amount.getText().toString())) {
                        if (ET_amount.getText().toString().indexOf("-") != -1 && ET_amount.getText().length()==1){
                            Log.d("sfn", ET_amount.getText().toString());
                        }else {
                            salesBillDetailModel.setAmount(Integer.valueOf(ET_amount.getText().toString()));
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = salesBillDetailList;
                            mhandler.sendMessage(msg);
                        }

                    } else {
                        salesBillDetailModel.setAmount(null);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = salesBillDetailList;
                        mhandler.sendMessage(msg);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    //ET_amount.setText(lastbig.toString());
                    //ET_amount.setSelection(ET_amount.getText().length());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //if(arg0!=null&&arg0.length()>0)
                //lastbig = arg0.toString();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                try{
                    int i = Integer.parseInt(arg0.toString().trim());
                    Log.i("TESTLog","afterTextChanged");
                    if(i<0){
                        TV_styleName.setTextColor(Color.RED);
                        TV_styleCode.setTextColor(Color.RED);
                        TV_styleColor.setTextColor(Color.RED);
                        TV_styleSize.setTextColor(Color.RED);
                        ET_discount.setTextColor(Color.RED);
                        ET_remark.setTextColor(Color.RED);
                        ET_price.setTextColor(Color.RED);
                        ET_amount.setTextColor(Color.RED);
                        TV_total.setTextColor(Color.RED);
                    }
                    else
                    {
                        TV_styleName.setTextColor(Color.BLACK);
                        TV_styleCode.setTextColor(Color.BLACK);
                        ET_remark.setTextColor(Color.BLACK);
                        TV_styleColor.setTextColor(Color.BLACK);
                        TV_styleSize.setTextColor(Color.BLACK);
                        ET_discount.setTextColor(Color.BLACK);
                        ET_price.setTextColor(Color.BLACK);
                        ET_amount.setTextColor(Color.BLACK);
                        TV_total.setTextColor(Color.BLACK);
                    }
                }catch (Exception e)
                {
                    TV_styleName.setTextColor(Color.BLACK);
                    TV_styleCode.setTextColor(Color.BLACK);
                    ET_remark.setTextColor(Color.BLACK);
                    TV_styleColor.setTextColor(Color.BLACK);
                    TV_styleSize.setTextColor(Color.BLACK);
                    ET_discount.setTextColor(Color.BLACK);
                    ET_price.setTextColor(Color.BLACK);
                    ET_amount.setTextColor(Color.BLACK);
                    TV_total.setTextColor(Color.BLACK);
                }
            }
        });

        if(fuSalesBillDetailModel.getDiscount()!=null)
            ET_discount.setText(fuSalesBillDetailModel.getDiscount()+"");
        else
            ET_discount.setText(discount.getText().toString());
        salesBillDetailModel.setRemark(fuSalesBillDetailModel.getRemark());
        ET_remark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!("").equals(ET_remark.getText().toString())){
                    salesBillDetailModel.setRemark(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TV_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int Id = (int)view.getTag();
                //Log.d("sfn", Id+"");
                tableLayout.removeViewAt(Id);
                salesBillDetailList.remove(Id);
                int childrenCount = tableLayout.getChildCount();
                for(int i = 0 ;i<childrenCount;i++)
                {
                    try{
                        tableLayout.getChildAt(i).findViewById(R.id.TV_delete).setTag(i);
                        //Log.d("sfn", i+"---");
                    }
                    catch (Exception e)
                    {
                    }
                }
                itemId=childrenCount;
                Message msg = new Message();
                msg.what = 1;
                msg.obj = salesBillDetailList;
                mhandler.sendMessage(msg);
            }
        });
        salesBillDetailModel.setStyleid(fuSalesBillDetailModel.getStyleid());
        salesBillDetailModel.setStyleCode(fuSalesBillDetailModel.getStyleCode());
        salesBillDetailModel.setStyleString(fuSalesBillDetailModel.getStyleString());
        salesBillDetailModel.setColorid(fuSalesBillDetailModel.getColorid());
        salesBillDetailModel.setColorString(fuSalesBillDetailModel.getColorString());
        salesBillDetailModel.setSizeid(fuSalesBillDetailModel.getSizeid());
        salesBillDetailModel.setSizeString(fuSalesBillDetailModel.getSizeString());
        salesBillDetailModel.setFuStylePriceTypeModelList(fuSalesBillDetailModel.getFuStylePriceTypeModelList());
        try {
            salesBillDetailModel.setDiscount(BigDecimal.valueOf(Double.parseDouble(discount.getText().toString())));
        }catch (Exception e)
        {
            e.printStackTrace();
            salesBillDetailModel.setDiscount(new BigDecimal(1));
        }

        salesBillDetailList.add(salesBillDetailModel);
        itemId++;
        view.getTag(itemId);
        return view;
    }

    private void getfoottable() {

        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    if (salesBillModel == null){
                        salesBillModel = new FuSalesBillModel();
                    }
                    numberber = 0;
                    totlele = new BigDecimal(0);
                    salesAmount = 0;
                    cancelAmount = 0;
                    templist = (List<FuSalesBillDetailModel>) msg.obj;
                    sum_id.setText("总计 " + templist.size());
                    for (int i = 0; i < templist.size(); i++) {
                        if (templist.get(i).getAmount() != null){
                            numberber = numberber + templist.get(i).getAmount();
                            totlele = totlele.add(templist.get(i).getTotal());
                            if (templist.get(i).getAmount().intValue()>0){
                                salesAmount = salesAmount + templist.get(i).getAmount();
                            }
                            if (templist.get(i).getAmount().intValue()<0){
                                cancelAmount = cancelAmount + templist.get(i).getAmount();
                            }
                        }
                    }

                    sum_num.setText(numberber + "");
                    sum_money.setText(bigDecimalUtil.getStringUtil(totlele+""));
                    sumamountdetail.setText("("+salesAmount+","+cancelAmount+")");
                    salesBillModel.setTotal(totlele);
                }
            }
        };
    }

    public void SetSuspendSalesBill(int id){
        SalesBillService salesBillService = new SalesBillService();
        result = salesBillService.doSelectSalesBill(webServerUrl, loginstaffid, logininvid, accessKey, accountid, id);
        DataUtil dataUtil = new DataUtil();
        FuBaseModel baseModel = dataUtil.message(result);
        if(baseModel.getResultCode().intValue() ==1){
            bigDecimalUtil = new BigDecimalUtil();
            salesBillModel = dataUtil.getSalesBillData(result);
            if (salesBillModel.getClientString() != null){
                et_clientid.setText(salesBillModel.getClientString());
                if(salesBillModel.getArrears()!=null)
                {
                    if(salesBillModel.getArrears().intValue()>0)
                    {
                        arrears.setTextColor(getResources().getColor(R.color.delete));
                    }
                    if(salesBillModel.getArrears().intValue()<0)
                    {
                        arrears.setTextColor(getResources().getColor(R.color.money_in));
                        tv_arrears.setText("余额");
                    }
                    arrears.setText(bigDecimalUtil.getStringUtil(Math.abs(salesBillModel.getArrears().intValue())+""));
                }
            }

            if (salesBillModel.getStaffString() != null){
                et_staffid.setText(salesBillModel.getStaffString());
            }
            occurencetime.setText(bigDecimalUtil.getDate(salesBillModel.getOccurrencetime()));
            if (salesBillModel.getDiscount() != null){
                discount.setText(bigDecimalUtil.getStringUtil(salesBillModel.getDiscount().toString()));
            }else {
                discount.setText("1");
            }
            if (salesBillModel.getRemark() != null){
                remark.setText(salesBillModel.getRemark());
            }

            salesBillDetailList.clear();
            if(tableLayout!=null)
                tableLayout.removeAllViews();
            fuSalesBillDetailModelList = salesBillModel.getFuSalesBillDetailList();

            appendDetailItemTable();
            ACShowHide(false,false);
            suspendBillId =id;
             /*for (FuSalesBillDetailModel salesBillDetailModel : salesBillDetailModelList){

                View convertView = addRow(salesBillDetailModel);

                tableLayout.addView(convertView);

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

            staffid = salesBillModel.getStaffid() == null ? loginstaffid : salesBillModel.getStaffid();*/

        }else if(baseModel.getResultCode().intValue() ==0){
            DialogUtil.showDialog(getActivity(), baseModel.getMessage(), false);
        }
    }


    public static class ViewTabLay{
        public View view;
        public TableLayout tablay;

        public ViewTabLay(View view) {
            this.view = view;
            this.tablay = view.findViewById(R.id.tablay);
        }
    }

    public void setSalesBillDetailList(FuStyleModel fuStyleModel){

        fuSalesBillDetailModelList = new ArrayList<>();
        FuSalesBillDetailModel fuSalesBillDetailModel = new FuSalesBillDetailModel();
        fuSalesBillDetailModel.setStyleid(fuStyleModel.getId());
        fuSalesBillDetailModel.setStyleCode(fuStyleModel.getCode());
        fuSalesBillDetailModel.setStyleString(fuStyleModel.getName());
        fuSalesBillDetailModel.setColorid(fuStyleModel.getColorid());
        fuSalesBillDetailModel.setColorString(fuStyleModel.getColorString());
        fuSalesBillDetailModel.setSizeid(fuStyleModel.getSizeid());
        fuSalesBillDetailModel.setSizeString(fuStyleModel.getSizeString());
        fuSalesBillDetailModel.setFuStylePriceTypeModelList(fuStyleModel.getFuStylePriceTypeModelList());
        fuSalesBillDetailModel.setFuStyleImageModelList(fuStyleModel.getFuStyleImageModelList());
        fuSalesBillDetailModel.setPrice(new BigDecimal(0));
        for (FuStylePriceTypeModel fuStylePriceTypeModel : fuStyleModel.getFuStylePriceTypeModelList()){
            if (fuStylePriceTypeModel.getPricetypeString().equals(priceTypeString)){
                fuSalesBillDetailModel.setPrice(fuStylePriceTypeModel.getPrice());
            }
        }
        fuSalesBillDetailModel.setTotal(new BigDecimal(0));
        fuSalesBillDetailModel.setRemark("");

        fuSalesBillDetailModelList.add(fuSalesBillDetailModel);

    }

    public void setNull(){
        et_clientid.setText("");
        arrears.setText("");
        et_staffid.setText("");
        discount.setText("1");
        remark.setText("");
        salesBillModel = new FuSalesBillModel();
        salesBillDetailList.clear();
        salesBillDetailList = new ArrayList<>();
        fuSalesBillDetailModelList.clear();
        fuSalesBillDetailModelList = new ArrayList<>();
        ViewTabLay viewTabLay = new ViewTabLay(view);
        tableLayout = viewTabLay.tablay;
        View view1;
        for(int index = tableLayout.getChildCount(); index >0 ; index--){
            view1 = tableLayout.getChildAt(index);
            if(view1 != null && view1 instanceof TableLayout){
            } else {
                tableLayout.removeAllViews();
            }
        }
        sum_id.setText("总计 0");
        sumamountdetail.setText("(0,0)");
        sum_num.setText("0");
        sum_money.setText("0");
        itemId = 0;
    }
}
