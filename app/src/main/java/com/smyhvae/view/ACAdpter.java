package com.smyhvae.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public class ACAdpter extends BaseAdapter {
    private List<FuBaseModel> mTitleArray;// 标题列表
    private LayoutInflater inflater = null;
    private Context mContext;

    /**
     * Adapter构造方法
     *
     * @param titleArray
     */
    public ACAdpter(Context context, List<FuBaseModel> titleArray) {
        // TODO Auto-generated constructor stub
        this.mTitleArray = titleArray;
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 获取总数
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mTitleArray.size();
    }

    /**
     * 获取Item内容
     */
    @Override
    public FuBaseModel getItem(int position) {
        // TODO Auto-generated method stub
        return mTitleArray.get(position);
    }

    /**
     * 获取Item的ID
     */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        MyAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new MyAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_ac, null);
            holder.titleTv = (TextView) convertView.findViewById(R.id.title_tv);
            convertView.setTag(holder);
        } else {
            holder = (MyAdapter.ViewHolder) convertView.getTag();
        }
        // 设置
        holder.titleTv.setText(mTitleArray.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView titleTv;
    }

    /**
     * 刷新数据
     *
     * @param array
     */
    public void refreshData(List<FuBaseModel> array) {
        this.mTitleArray = array;
        notifyDataSetChanged();
    }
}