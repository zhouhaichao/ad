package com.smyhvae.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.smyhvae.model.FuSizeModel;
import com.smyhvae.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SelectSizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<FuSizeModel> sizes = new ArrayList<>();
    private List<FuSizeModel> sizeList = new ArrayList<>();

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;
    private Context context;

    public SelectSizeAdapter(Context context) {
        super();
        this.context = context;
    }
    public SelectSizeAdapter(List<FuSizeModel> sizes) {
        if (sizes == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        this.sizes = sizes;
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<FuSizeModel> sizes) {
        this.sizes = sizes;
        mSelectedPositions = new SparseBooleanArray();
    }


    //获得选中条目的结果
    public List<FuSizeModel> getSelectedItem() {
        List<FuSizeModel> selectList = new ArrayList<>();
        for (int i = 0; i < sizes.size(); i++) {
            if (isItemChecked(i)) {
                selectList.add(sizes.get(i));
            }
        }
        return selectList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_size_info, viewGroup, false);
        return new ListItemViewHolder(itemView);
    }

    //设置给定位置条目的选择状态
    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //根据位置判断条目是否可选
    private boolean isSelectable() {
        return mIsSelectable;
    }
    //设置给定位置条目的可选与否的状态
    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    //绑定界面，设置监听
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {
        //设置条目状态
        ((ListItemViewHolder) holder).mainTitle.setText(sizes.get(i).getName());
        ((ListItemViewHolder) holder).checkBox.setChecked(isItemChecked(i));

        ((ListItemViewHolder) holder).fuSizeModel.setSizeid(sizes.get(i).getId());
        ((ListItemViewHolder) holder).fuSizeModel.setName(sizes.get(i).getName());
        //checkBox的监听
        ((ListItemViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(i)) {
                    ((ListItemViewHolder) holder).fuSizeModel.setSelectStatus(null);
                    setItemChecked(i, false);
                } else {
                    ((ListItemViewHolder) holder).fuSizeModel.setSelectStatus(1);
                    setItemChecked(i, true);
                }
                Log.d("sfn选择了", sizes.get(i).getName());

            }
        });
        sizeList.add(((ListItemViewHolder) holder).fuSizeModel);

    }

    @Override
    public int getItemCount() {
        return sizes == null ? 0 : sizes.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder{
        FuSizeModel fuSizeModel;
        //ViewHolder
        CheckBox checkBox;
        TextView mainTitle;

        ListItemViewHolder(View view) {
            super(view);
            this.mainTitle = (TextView) view.findViewById(R.id.text);
            this.checkBox = (CheckBox) view.findViewById(R.id.select_checkbox);
            this.fuSizeModel = new FuSizeModel();
        }
    }

    public List<FuSizeModel> getSizeList() {
        return sizeList;
    }
}
