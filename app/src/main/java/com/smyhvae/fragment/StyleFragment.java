package com.smyhvae.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuStyleModel;
import com.smyhvae.myapplication.AddStyleActivity;
import com.smyhvae.myapplication.MyApplication;
import com.smyhvae.myapplication.R;
import com.smyhvae.myapplication.StyleActivity;
import com.smyhvae.service.BaseService;
import com.smyhvae.service.PhotoService;
import com.smyhvae.service.StyleService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.view.ListItem;
import com.smyhvae.view.MyAdapter;
import com.smyhvae.view.RecycleView.StyleRecycleViewAdapter;
import com.smyhvae.view.StyleListViewAdapter;
import com.smyhvae.view.SwipeView.CustomLoadMoreView;
import com.smyhvae.view.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class StyleFragment extends Fragment implements View.OnClickListener,OnRefreshListener,OnLoadMoreListener, AdapterView.OnItemClickListener{

    public static  final  int  RETURN_CODE_SUCCESS = 101;
    public static  final  int  RETURN_CODE_FAIL = 100;

    public static final int  REQUEST_CODE=1003;

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    int w_screen=0;
    int h_screen=0;

    private View view;
    public StyleListViewAdapter styleListViewAdapter;

    public SwipeToLoadLayout swipeToLoadLayout;
    public StyleRecycleViewAdapter styleRecycleViewAdapter;
    public RecyclerView recyclerView;
    public CustomLoadMoreView customLoadMoreView;
    public boolean mIsRefreshing=false;


    public XListView listView;
    private List<ListItem> list = new ArrayList<>();
    private TextView tv_searchStyle;
    private TextView tv_clearStyle;
    private TextView tv_addStyle;
    private EditText et_code;
    private Spinner et_class;
    private EditText et_name;
    private Spinner spinner_season;
    private Spinner spinner_status;
    private Spinner et_brandid;
    private String result;
    private int page = 1;
    private TextView count;
    private Integer countSum=0;
    private String code;
    private Integer classid;
    private String season;
    private String name;
    private Integer brandid;
    private Integer status;
    private MyAdapter adapter;
    private List<FuBaseModel> classList;
    private List<FuBaseModel> brandList;

    private  LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        application = (MyApplication) getActivity().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        view = inflater.inflate(R.layout.list_view, container, false);
        tv_searchStyle = view.findViewById(R.id.tv_searchStyle);
        tv_clearStyle = view.findViewById(R.id.tv_clearStyle);
        tv_addStyle = view.findViewById(R.id.tv_addStyle);
        count =view.findViewById(R.id.count_style);
        //listView = view.findViewById(R.id.list_item);

        recyclerView = view.findViewById(R.id.swipe_target);
        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        customLoadMoreView= view.findViewById(R.id.swipe_load_more_footer);

        et_code = view.findViewById(R.id.et_code);
        et_class = view.findViewById(R.id.et_class);
        et_name = view.findViewById(R.id.et_name);
        spinner_season = view.findViewById(R.id.spinner_season);
        spinner_season.setSelection(6);
        spinner_status = view.findViewById(R.id.spinner_status);
        spinner_status.setSelection(1);
        et_brandid = view.findViewById(R.id.et_brandid);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        tv_clearStyle.setOnClickListener(this);
        tv_searchStyle.setOnClickListener(this);
        tv_addStyle.setOnClickListener(this);
        status = spinner_status.getSelectedItemPosition();
        setFirstData();
        count.setText(countSum.toString());

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        styleRecycleViewAdapter = new StyleRecycleViewAdapter(this, list, w_screen, h_screen);
        recyclerView.setAdapter(styleRecycleViewAdapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mIsRefreshing;
            }
        });

        new Thread() {
            public void run() {
                try {
                    for (int i=1; i<3; i++){
                        BaseService baseService = new BaseService();
                        if(i == 1){
                            result = baseService.doList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, "class", "fuClassList");
                        }else if(i==2){
                            result = baseService.doList(webServerUrl,loginstaffid, logininvid, accessKey, accountid, "brand", "fuBrandList");
                        }
                        Logcat.show(result);
                        Message msg = handler1.obtainMessage();
                        msg.what=i;
                        msg.obj = result;
                        handler1.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //swipeToLoadLayout.setRefreshing(true);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        StyleListViewAdapter.ListItemView listItemView = (StyleListViewAdapter.ListItemView) view.getTag();
        Intent intent = new Intent(getActivity(), StyleActivity.class);
        intent.putExtra("id", listItemView.id);
        startActivityForResult(intent,REQUEST_CODE);
    }

    public void statrActivity(Integer id){
        Intent intent = new Intent(getActivity(), StyleActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent,REQUEST_CODE);
    }
    // 上拉加载更多
    public void getRecordUpPullForeRefresh(final int page, final int showCount) {
        // 网络判断
        new Thread() {
            public void run() {
                result = result(page, showCount, code, classid, season, name, brandid, status);
                list = getData(result);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = list;
                handler.sendMessage(msg);

            }
        }.start();
    }
    // 下拉刷新最新数据
    public void getRecordDownPullForeRefresh( final int page, final int showCount) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (page == 1) {
                    list.clear();
                    code = et_code.getText().toString().trim();
                    name = et_name.getText().toString().trim();
                    season = (String) spinner_season.getSelectedItem();
                    status = spinner_status.getSelectedItemPosition();

                    result = result(page, showCount, code, classid, season, name, brandid, status);
                    list = getData(result);
                }
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = list;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void getStylePhoto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //DataUtil dataUtil = new DataUtil();
                PhotoService photoService = new PhotoService();
                String fileServerip = application.getFileserverip();
                for(int i=0;i<list.size();i++)
                {
                    if(list.get(i).getImageId()!=null&&list.get(i).getImage()==null)
                    {
                        Bitmap bitmap =photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,list.get(i).getImageId(),1);
                        if(bitmap!=null)
                        {
                            list.get(i).setImage(new BitmapDrawable(bitmap));
                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                            //Log.i("TestLog","刷新item"+i);
                        }
                    }
                }

            }
        }).start();
    }
    // 下拉刷新数据
    @Override
    public void onRefresh() {
        Log.i("TestLog","货品下拉刷新");
        mIsRefreshing=true;
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                page = 1;
                getRecordDownPullForeRefresh(page, 20);
            }
        }, 1200);
        customLoadMoreView.isBottom=false;
    }

    // 上拉加载更多
    @Override
    public void onLoadMore() {
        if(list.size()<countSum)
        {
            Log.i("TestLog","货品上拉加载更多");
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    page++;
                    getRecordUpPullForeRefresh(page, 20);
                }
            }, 1000);
        }
        else
        {
            Log.i("TestLog","list.size()"+list.size()+"countSum"+countSum);
            //list.size()<countSum
            customLoadMoreView.isBottom=true;
            swipeToLoadLayout.setLoadingMore(false);
        }

    }

    // 第一次加载数据
    public void setFirstData() {

        result = result(1, 20, code, classid, season, name, brandid, status);
        list = getData(result);
        if (list.size() > 0){
            return;
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_clearStyle:
                et_code.setText("");
                et_name.setText("");
                et_class.setSelection(classList.size()-1);
                et_brandid.setSelection(brandList.size()-1);
                spinner_season.setSelection(6);
                spinner_status.setSelection(0);
                break;
            case R.id.tv_searchStyle:
                swipeToLoadLayout.setRefreshing(true);
                //最先的ListView查询搜索代码
                /*list.clear();
                styleRecycleViewAdapter.notifyDataSetChanged();
                code = et_code.getText().toString().trim();
                name = et_name.getText().toString().trim();
                season = (String) spinner_season.getSelectedItem();
                status = spinner_status.getSelectedItemPosition();
                result = result(1, 20, code, classid, season, name, brandid, status);
                list = getData(result);
                count.setText(countSum.toString());
                //styleListViewAdapter = new StyleListViewAdapter(getContext(), list, w_screen, h_screen);
                if(styleRecycleViewAdapter == null)
                {
                    styleRecycleViewAdapter = new StyleRecycleViewAdapter(this,list, w_screen, h_screen);
                    //listView.setAdapter(styleListViewAdapter);
                    recyclerView.setAdapter(styleRecycleViewAdapter);
                }
                else
                {
                    styleRecycleViewAdapter.notifyDataSetChanged();
                }

                if (countSum<20){
                    //listView.setPullLoadEnable(false);
                }else {
                    //listView.setPullLoadEnable(true);
                }*/
                break;
            case R.id.tv_addStyle:
                Intent intent = new Intent(getActivity(), AddStyleActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(REQUEST_CODE ==requestCode)//货品修改返回
        {
            if(resultCode ==RETURN_CODE_SUCCESS)
            {
                swipeToLoadLayout.setRefreshing(true);
            }
            if(resultCode == 3 )//新增货品返回
            {
                swipeToLoadLayout.setRefreshing(true);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String result(final int page, final int showCount, final String code, final Integer classid, final String season, final String name, final Integer brandid, final Integer status){
        StyleService styleService = new StyleService();
        result = styleService.doStyleList(webServerUrl, loginstaffid, logininvid, accessKey, accountid, page, showCount, code, classid, season, name, brandid, status);
        Logcat.show(result);
        return result;
    }

    public List getData(String result){
        if(result!=null)
        {
            DataUtil dataUtil = new DataUtil();
            FuBaseModel baseModel = dataUtil.message(result);
            if(baseModel.getResultCode().intValue() ==1){
                countSum = dataUtil.count(result);
                Resources res = getActivity().getResources();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String marketString;
                Integer price;
                List<FuStyleModel> styleModelList = dataUtil.getStyleListData(result);
                for (FuStyleModel fuStyleModel: styleModelList) {
                    ListItem item = new ListItem();
                    //item.setImage(res.getDrawable(R.drawable.logo));
                    if(fuStyleModel.getFuStyleImageModelList()!= null&&fuStyleModel.getFuStyleImageModelList().size()>0)
                        item.setImageId(fuStyleModel.getFuStyleImageModelList().get(0).getId());
                    item.setId(fuStyleModel.getId());
                    item.setCode(fuStyleModel.getCode());
                    item.setName(fuStyleModel.getName());
                    item.setAmount(fuStyleModel.getAmount().toString());
                    item.setPricetypeString(fuStyleModel.getFuStylePriceTypeModelList().get(0).getPricetypeString());
                    price = fuStyleModel.getFuStylePriceTypeModelList().get(0).getPrice().intValue();
                    item.setPrice(price.toString());
                    marketString = sdf.format(fuStyleModel.getMarketdate());
                    item.setMarketdate(marketString);
                    item.setStatus(fuStyleModel.getStatus());
                    //Log.i("TestLog",item.getCode()+"---"+item.getStatus());
                    list.add(item);
                }
            }else if(baseModel.getResultCode().intValue() ==0){
                DialogUtil.showDialog(getActivity(), baseModel.getMessage(), false);
            }
        }
        return list;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    getStylePhoto();
                    swipeToLoadLayout.setLoadingMore(false);
                    styleRecycleViewAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    getStylePhoto();
                    swipeToLoadLayout.setRefreshing(false);
                    styleRecycleViewAdapter.notifyDataSetChanged();
                    mIsRefreshing=false;
                    count.setText(countSum.toString());
                    break;
                case 3://图片更新
                    styleRecycleViewAdapter.notifyItemChanged(msg.arg1);
                    break;
                case -1:
                    page--;
                    Toast.makeText(getActivity(), "数据加载失败！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            DataUtil dataUtil = new DataUtil();
            if (msg.what == 1) {
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());

                if(baseModel.getResultCode().intValue() ==1){
                    classList = dataUtil.getList(msg.obj.toString());
                    FuBaseModel fuBaseModel = new FuBaseModel();
                    classList.add(fuBaseModel);
                    adapter = new MyAdapter(getActivity(), classList);
                    et_class.setAdapter(adapter);
                    et_class.setSelection(classList.size()-1);

                    et_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            classid = classList.get(position).getId();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
                }
            } else if (msg.what == 2) {
                FuBaseModel baseModel = dataUtil.message(msg.obj.toString());

                if(baseModel.getResultCode().intValue() ==1){
                    brandList = dataUtil.getList(msg.obj.toString());
                    FuBaseModel fuBaseModel = new FuBaseModel();
                    brandList.add(fuBaseModel);
                    adapter = new MyAdapter(getActivity(), brandList);
                    et_brandid.setAdapter(adapter);
                    et_brandid.setSelection(brandList.size()-1);
                    et_brandid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            brandid = brandList.get(position).getId();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }else if(baseModel.getResultCode().intValue() ==0){
                    DialogUtil.showDialog(getContext(), baseModel.getMessage(), false);
                }
                getStylePhoto();
            }

        }
    };
}