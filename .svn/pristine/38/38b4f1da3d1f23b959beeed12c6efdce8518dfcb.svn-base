package com.smyhvae.view;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyhvae.model.FuColorGroupModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SelectGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<FuColorGroupModel> colorGroups = new ArrayList<>();
    private List<FuColorModel> colors = new ArrayList<>();
    private List<List<FuColorModel>> colorList = new ArrayList<>();
    private Context context;
    private SelectAdapter selectAdapter;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

    public SelectGroupAdapter(Context context, List<FuColorGroupModel> colorGroups, List<FuColorModel> colors) {
        if (colorGroups == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        this.colorGroups = colorGroups;
        this.context = context;
        this.colors = colors;
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<FuColorGroupModel> colorGroups) {
        this.colorGroups = colorGroups;
        mSelectedPositions = new SparseBooleanArray();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_group_info, viewGroup, false);
        return new SelectGroupAdapter.ListItemViewHolder(itemView);
    }


    //绑定界面，设置监听
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {
        ((ListItemViewHolder) holder).text_group_info.setText(colorGroups.get(i).getName());
        ((ListItemViewHolder) holder).color_or_size_info.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        List<FuColorModel> colorModelList = new ArrayList<>();
        for (FuColorModel colorModel : colors){
            if (colorModel.getGroupid().equals(colorGroups.get(i).getId())){
                colorModelList.add(colorModel);
            }
        }
        selectAdapter = new SelectAdapter(colorModelList);
        ((ListItemViewHolder) holder).color_or_size_info.setAdapter(selectAdapter);

        ((ListItemViewHolder) holder).fuColorList = selectAdapter.getColorList();
        colorList.add(((ListItemViewHolder) holder).fuColorList);
    }

    @Override
    public int getItemCount() {
        return colorGroups == null ? 0 : colorGroups.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder{
        List<FuColorModel> fuColorList;
        TextView text_group_info;
        RecyclerView color_or_size_info;
        ListItemViewHolder(View view) {
            super(view);
            this.text_group_info = (TextView) view.findViewById(R.id.text_group_info);
            this.color_or_size_info = view.findViewById(R.id.color_or_size_info);
            this.fuColorList = new ArrayList<>();
        }
    }

    public List<List<FuColorModel>> getColorList() {
        return colorList;
    }

}
