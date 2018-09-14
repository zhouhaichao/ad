package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuStyleModel;
import com.smyhvae.service.StyleService;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.view.ListItem;
import com.smyhvae.view.ListViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BottomActivity extends Activity implements OnClickListener {
    private SwipeRefreshLayout swipeLayout;
    private ViewPager mViewPager;// 用来放置界面切换
    private PagerAdapter mPagerAdapter;// 初始化View适配器
    private List<View> mViews = new ArrayList<View>();

    private LinearLayout mTabSalesbill;
    private LinearLayout mTabStyle;
    private LinearLayout mTabSetting;
    private TextView salesbill;
    private TextView style;
    private TextView mine;
    private MyApplication application;
    private SimpleAdapter adapter;
    private ArrayList<ListItem> mList;
    private Handler handler;
    private NewThread thread;
    private ListView lv;


    public View loadmoreView;
    public LayoutInflater inflater;
    public ListView listView;
    public int last_index;
    public int total_index;
    public List<String> firstList = new ArrayList<String>();//表示首次加载的list
    public List<String> nextList = new ArrayList<String>();//表示出现刷新之后需要显示的list
    public boolean isLoading = false;//表示是否正处于加载状态
    public ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bottom);
        application = (MyApplication) getApplicationContext();
        initView();
        initViewPage();
        initEvent();

    }
    private void initEvent() {
        mTabSalesbill.setOnClickListener(this);
        mTabStyle.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            /**
             *ViewPage左右滑动时
             */
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        resetText(R.id.id_tab_weixin);
                        changeTitle(0);
                        break;
                    case 1:
                        resetText(R.id.id_tab_frd);
                        changeTitle(1);
                        break;
                    case 2:
                        resetText(R.id.id_tab_settings);
                        changeTitle(2);
                        break;
                    default:
                        break;
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /**
     * 初始化设置
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpage);
        mTabSalesbill = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabStyle = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
        salesbill = findViewById(R.id.salesbill);
        style = findViewById(R.id.style);
        mine = findViewById(R.id.mine);

    }

    /**
     * 初始化ViewPage
     */
    private void initViewPage() {

        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View tab01 = mLayoutInflater.inflate(R.layout.salesbill_list, null);
        View tab02 = mLayoutInflater.inflate(R.layout.list_view, null);
        View tab03 = mLayoutInflater.inflate(R.layout.fragment, null);

        mViews.add(tab01);
        mViews.add(tab02);
        mViews.add(tab03);

        // 适配器初始化并设置
        mPagerAdapter = new PagerAdapter() {

            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }

            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            public int getCount() {
                return mViews.size();
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.id_tab_weixin:
                mViewPager.setCurrentItem(0);
                resetText(arg0.getId());
                changeTitle(0);

//                inflater = LayoutInflater.from(this);
//                loadmoreView = inflater.inflate(R.layout.footview, null);//获得刷新视图
//                loadmoreView.setVisibility(View.VISIBLE);//设置刷新视图默认情况下是不可见的
//                listView = (ListView) findViewById(R.id.listView);
//
//                initList(10, 10);
//
//                listViewAdapter = new ListViewAdapter(this, firstList);
//                listView.setOnScrollListener(this);
//                listView.addFooterView(loadmoreView,null,false);
//                listView.setAdapter(listViewAdapter);

                break;
            case R.id.id_tab_frd:
                mViewPager.setCurrentItem(1);
                resetText(arg0.getId());
                changeTitle(1);
                lv = (ListView) findViewById(R.id.list_item);
                handler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        DataUtil dataUtil = new DataUtil();
                        FuBaseModel baseModel = dataUtil.message(msg.obj.toString());

                        if(baseModel.getResultCode().intValue() ==1){
                            TextView count ;
                            count = findViewById(R.id.count);
                            Integer countSum = dataUtil.count(msg.obj.toString());
                            count.setText(countSum.toString());

                            List<FuStyleModel> styleModelList = dataUtil.getStyleListData(msg.obj.toString());
                            getData(styleModelList);
                            StyleListViewAdapter adapter = new StyleListViewAdapter();
                            lv.setAdapter(adapter);

                        }else if(baseModel.getResultCode().intValue() ==0){
                            DialogUtil.showDialog(BottomActivity.this, baseModel.getMessage(), false);
                        }
                    }

                };

                thread = new NewThread();

                break;
            case R.id.id_tab_settings:
                mViewPager.setCurrentItem(2);
                resetText(arg0.getId());
                changeTitle(2);
                break;
            default:
                break;
        }
    }

    private void resetText(int id){
        if(id == R.id.id_tab_weixin){
            salesbill.setTextColor(Color.RED);
            style.setTextColor(Color.BLUE);
            mine.setTextColor(Color.BLUE);
        }else if(id == R.id.id_tab_frd){
            salesbill.setTextColor(Color.BLUE);
            style.setTextColor(Color.RED);
            mine.setTextColor(Color.BLUE);
        }else if(id == R.id.id_tab_settings){
            salesbill.setTextColor(Color.BLUE);
            style.setTextColor(Color.BLUE);
            mine.setTextColor(Color.RED);
        }
    }

    private void changeTitle(int i){
        TextView tv_backward ;
        TextView tv_forward ;
        TextView text_title ;
        TextView settings_name ;
        TextView settings_code ;
        TextView settings_role ;
        TextView settings_invString ;

        switch (i){
            case 0 :
                text_title = findViewById(R.id.text_title);
                tv_backward = findViewById(R.id.tv_backward);
                tv_forward = findViewById(R.id.tv_forward);
                tv_backward.setText("清除");
                tv_forward.setText("搜索");
                text_title.setText("销售查询");
                break;
            case 1 :
                text_title = findViewById(R.id.text_title);
                tv_backward = findViewById(R.id.tv_backward);
                tv_forward = findViewById(R.id.tv_forward);
                tv_backward.setText("编辑");
                tv_forward.setText("搜索");
                text_title.setText("货品列表");
                break;
            case 2 :
                text_title = findViewById(R.id.text_title);
                tv_backward = findViewById(R.id.tv_backward);
                tv_forward = findViewById(R.id.tv_forward);
                tv_backward.setText("");
                tv_forward.setText("");
                text_title.setText("设置");

                settings_name = findViewById(R.id.settings_name);
                settings_code = findViewById(R.id.settings_code);
                settings_role = findViewById(R.id.settings_role);
                settings_invString = findViewById(R.id.settings_invString);
                settings_name.setText(application.getFuStaffModel().getName());
                settings_code.setText(application.getFuStaffModel().getCode());
                settings_role.setText(application.getFuStaffModel().getFuRoleList().get(0).getName());
                settings_invString.setText(application.getFuStaffModel().getFuInventoryList().get(0).getName());
                break;
            default:
                break;
        }

    }

    private ArrayList<ListItem> getData(List<FuStyleModel> list) {
        Resources res = this.getResources();
        mList = new ArrayList<ListItem>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String marketString;
        Integer price;
        for (FuStyleModel fuStyleModel : list){
            ListItem item = new ListItem();
            item.setImage(res.getDrawable(R.drawable.logo));
            item.setCode(fuStyleModel.getCode());
            item.setName(fuStyleModel.getName());
            item.setAmount(fuStyleModel.getAmount().toString());
            item.setPricetypeString(fuStyleModel.getFuStylePriceTypeModelList().get(0).getPricetypeString());
            price = fuStyleModel.getFuStylePriceTypeModelList().get(0).getPrice().intValue();
            item.setPrice(price.toString());
            marketString = sdf.format(fuStyleModel.getMarketdate());
            item.setMarketdate(marketString);
            mList.add(item);
        }
        return mList;
    }

    class StyleListViewAdapter extends BaseAdapter {

        /**
         * 返回item的个数
         */
        @Override
        public int getCount() {
            return mList.size();
        }

        /**
         * 返回item的内容
         */
        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        /**
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 返回item的视图
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView listItemView;

            // 初始化item view
            if (convertView == null) {
                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = LayoutInflater.from(BottomActivity.this).inflate(R.layout.stylelist, null);

                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new ListItemView();
                listItemView.imageView = (ImageView) convertView.findViewById(R.id.image);
                listItemView.code = (TextView) convertView.findViewById(R.id.code);
                listItemView.name = convertView.findViewById(R.id.name);
                listItemView.amount = convertView.findViewById(R.id.amount);
                listItemView.pricetypeString = convertView.findViewById(R.id.pricetypeString);
                listItemView.price = convertView.findViewById(R.id.price);
                listItemView.marketdate = convertView.findViewById(R.id.marketdate);

                convertView.setTag(listItemView);
            } else {
                listItemView = (ListItemView) convertView.getTag();
            }

            // 获取到mList中指定索引位置的资源
            Drawable img = mList.get(position).getImage();
            String code = mList.get(position).getCode();
            String name = mList.get(position).getName();
            String amount = mList.get(position).getAmount();
            String pricetypeString = mList.get(position).getPricetypeString();
            String price = mList.get(position).getPrice();
            String marketdate = mList.get(position).getMarketdate();

            listItemView.imageView.setImageDrawable(img);
            listItemView.code.setText(code);
            listItemView.name.setText(name);
            listItemView.amount.setText(amount);
            listItemView.pricetypeString.setText(pricetypeString);
            listItemView.price.setText("￥"+price);
            listItemView.marketdate.setText(marketdate);

            // 返回convertView对象
            return convertView;
        }

    }

    class ListItemView {
        ImageView imageView;
        TextView code;
        TextView name;
        TextView amount;
        TextView pricetypeString;
        TextView price;
        TextView marketdate;
    }

    private class NewThread extends Thread {
        private Handler mHandler;
        private Looper mLooper;
        private String result;
        private StyleService styleService;

        public NewThread() {
            start();
        }

        public void run() {
            Looper.prepare();
            mLooper = Looper.myLooper();
            mHandler = new Handler(mLooper) {

                @Override
                public void handleMessage(Message msg) {
                    Message newMsg = Message.obtain();
                    newMsg.obj = msg.obj;
                    handler.sendMessage(newMsg);
                }

            };
            try {
                String accessKey = application.getAccessKey();
                int accountid = application.getFuStaffModel().getAccountid();
                int loginstaffid = application.getFuStaffModel().getId();
                int logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
                styleService = new StyleService();
//                result = styleService.doStyleList(loginstaffid, logininvid, accessKey, accountid, 1, 20);
                Logcat.show(result);

            } catch (Exception e) {
                result = e.toString();
            }
            Message msg = Message.obtain();
            msg.obj = result;
            mHandler.sendMessage(msg);
            Looper.loop();
        }
    }

    public void initList(int firstCount, int nextCount){
        for(int i = 0;i < firstCount;i++){
            firstList.add("第"+(i+1)+"个开始加载");
        }
//        for(int i = 0;i < firstCount;i++){
//            nextList.add("第"+(i+1)+"个加载");
//        }
        for(int i = 0;i < nextCount;i++){
            nextList.add("刷新之后第"+(i+1)+"个开始加载");
        }
    }
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        last_index = firstVisibleItem + visibleItemCount;
//        total_index = totalItemCount;
//        Log.v("last", last_index+" ");
//        Log.v("total", total_index+" ");
//    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        if(last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
//            //表示此时需要显示刷新视图界面进行新数据的加载(要等滑动停止)
//            if(!isLoading) {
//                //不处于加载状态的话对其进行加载
//                isLoading = true;
//                //设置刷新界面可见
//                loadmoreView.setVisibility(View.VISIBLE);
//                onLoad();
//            }
//        }
//    }

    /**
     * 刷新加载
     */
//    public void onLoad() {
//        try {
//            //模拟耗时操作
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if(listViewAdapter == null){
//            listViewAdapter = new ListViewAdapter(this, firstList);
//            listView.setAdapter(listViewAdapter);
//        }else {
//            listViewAdapter.updateView(nextList);
//        }
//        loadComplete();//刷新结束
//    }

    /**
     * 加载完成
     */
    public void loadComplete() {
        loadmoreView.setVisibility(View.GONE);//设置刷新界面不可见
        isLoading = false;//设置正在刷新标志位false
        BottomActivity.this.invalidateOptionsMenu();
        listView.removeFooterView(loadmoreView);//如果是最后一页的话，则将其从ListView中移出
    }

}
