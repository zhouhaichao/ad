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

import com.smyhvae.model.FuColorModel;
import com.smyhvae.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FuColorModel> colors = new ArrayList<>();
    private List<FuColorModel> colorList = new ArrayList<>();

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;
    private Context context;

    public SelectAdapter(Context context) {
        super();
        this.context = context;
    }
    public SelectAdapter(List<FuColorModel> colors) {
        if (colors == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        this.colors = colors;
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<FuColorModel> colors) {
        this.colors = colors;
        mSelectedPositions = new SparseBooleanArray();
    }


    //获得选中条目的结果
    public List<FuColorModel> getSelectedItem() {
        List<FuColorModel> selectList = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            if (isItemChecked(i)) {
                selectList.add(colors.get(i));
            }
        }
        return selectList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_color_or_size_info, viewGroup, false);
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
        ((ListItemViewHolder) holder).mainTitle.setText(colors.get(i).getName());
        ((ListItemViewHolder) holder).checkBox.setChecked(isItemChecked(i));

        ((ListItemViewHolder) holder).fuColorModel.setColorid(colors.get(i).getId());
        ((ListItemViewHolder) holder).fuColorModel.setName(colors.get(i).getName());
        //checkBox的监听
        ((ListItemViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(i)) {
                    ((ListItemViewHolder) holder).fuColorModel.setSelectStatus(null);
                    setItemChecked(i, false);
                } else {
                    ((ListItemViewHolder) holder).fuColorModel.setSelectStatus(1);
                    setItemChecked(i, true);
                }
                Log.d("sfn选择了", colors.get(i).getName());

            }
        });
        colorList.add(((ListItemViewHolder) holder).fuColorModel);

//        //条目view的监听
//        ((ListItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isItemChecked(i)) {
//                    setItemChecked(i, false);
//                } else {
//                    setItemChecked(i, true);
//                }
//                notifyItemChanged(i);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return colors == null ? 0 : colors.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder{
        FuColorModel fuColorModel;
        //ViewHolder
        CheckBox checkBox;
        TextView mainTitle;

        ListItemViewHolder(View view) {
            super(view);
            this.mainTitle = (TextView) view.findViewById(R.id.text);
            this.checkBox = (CheckBox) view.findViewById(R.id.select_checkbox);
            this.fuColorModel = new FuColorModel();
        }
    }

    public List<FuColorModel> getColorList() {
        return colorList;
    }
}
