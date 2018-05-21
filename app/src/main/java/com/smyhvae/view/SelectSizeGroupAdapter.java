package com.smyhvae.view;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyhvae.model.FuSizeGroupModel;
import com.smyhvae.model.FuSizeModel;
import com.smyhvae.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SelectSizeGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FuSizeGroupModel> sizeGroups = new ArrayList<>();
    private List<FuSizeModel> sizes = new ArrayList<>();
    private List<List<FuSizeModel>> sizeList = new ArrayList<>();
    private Context context;
    private SelectSizeAdapter selectSizeAdapter;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

    public SelectSizeGroupAdapter(Context context, List<FuSizeGroupModel> sizeGroups, List<FuSizeModel> sizes) {
        if (sizeGroups == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        this.sizeGroups = sizeGroups;
        this.context = context;
        this.sizes = sizes;
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<FuSizeGroupModel> sizeGroups) {
        this.sizeGroups = sizeGroups;
        mSelectedPositions = new SparseBooleanArray();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_size_group_info, viewGroup, false);
        return new ListItemViewHolder(itemView);
    }


    //绑定界面，设置监听
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {
        ((ListItemViewHolder) holder).text_size_group_info.setText(sizeGroups.get(i).getName());
        ((ListItemViewHolder) holder).size_info.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        List<FuSizeModel> sizeModelList = new ArrayList<>();
        for (FuSizeModel sizeModel : sizes){
            if (sizeModel.getGroupid().equals(sizeGroups.get(i).getId())){
                sizeModelList.add(sizeModel);
            }
        }
        selectSizeAdapter = new SelectSizeAdapter(sizeModelList);
        ((ListItemViewHolder) holder).size_info.setAdapter(selectSizeAdapter);

        ((ListItemViewHolder) holder).fuSizeList = selectSizeAdapter.getSizeList();
        sizeList.add(((ListItemViewHolder) holder).fuSizeList);
    }

    @Override
    public int getItemCount() {
        return sizeGroups == null ? 0 : sizeGroups.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder{
        List<FuSizeModel> fuSizeList;
        TextView text_size_group_info;
        RecyclerView size_info;
        ListItemViewHolder(View view) {
            super(view);
            this.text_size_group_info = (TextView) view.findViewById(R.id.text_size_group_info);
            this.size_info = view.findViewById(R.id.size_info);
            this.fuSizeList = new ArrayList<>();
        }
    }

    public List<List<FuSizeModel>> getSizeList() {
        return sizeList;
    }
}
