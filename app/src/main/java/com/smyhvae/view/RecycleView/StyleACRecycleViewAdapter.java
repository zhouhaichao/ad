package com.smyhvae.view.RecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuDynamicModel;
import com.smyhvae.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class StyleACRecycleViewAdapter extends RecyclerView.Adapter<StyleACRecycleViewAdapter.ViewHolder> {

    private List<FuDynamicModel> mTitleArray;// 标题列表
    private LayoutInflater inflater = null;
    private Context mContext;

    private OnItemClickListener itemClickListener;

    public  StyleACRecycleViewAdapter(Context context, List<FuDynamicModel> titleArray)
    {
        this.mTitleArray = titleArray;
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public StyleACRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_style_ac, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StyleACRecycleViewAdapter.ViewHolder holder, final int position) {


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(mTitleArray.get(position), position);
            }
        });

        holder.name.setText(mTitleArray.get(position).getName());
        //holder.name.setb
        holder.image.setImageDrawable(mTitleArray.get(position).getImage());
        Log.i("TestLog","访问onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return null==mTitleArray?0:mTitleArray.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private ImageView image;

        public ViewHolder(View viewItem)
        {
            super(viewItem);
            name = viewItem.findViewById(R.id.TV_styleACname);
            image = viewItem.findViewById(R.id.IV_styleACimage);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(FuDynamicModel fuDynamicModel, int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void refreshData(List<FuDynamicModel> array) {
        this.mTitleArray = array;
        notifyDataSetChanged();
    }
}
