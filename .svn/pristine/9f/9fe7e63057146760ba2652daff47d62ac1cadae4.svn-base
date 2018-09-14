package com.smyhvae.view.XRecycleView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.fragment.SalesBillFragment;
import com.smyhvae.myapplication.R;
import com.smyhvae.view.CommomDialog;
import com.smyhvae.view.ListItem;
import com.smyhvae.view.SwipeListLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/23.
 */

public class XRecycleViewAdapter extends RecyclerView.Adapter<XRecycleViewAdapter.ViewHolder>{

    private Set<SwipeListLayout> sets = new HashSet();
    public List<ListItem> list;
    public LayoutInflater inflater;
    private Context context;
    private SalesBillFragment salesBillFragment;
    private static int w_screen;
    private int h_screen;

    public XRecycleViewAdapter(SalesBillFragment salesBillFragment, Context context, List<ListItem> list, int w_screen, int h_screen) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.salesBillFragment = salesBillFragment;
        this.w_screen = w_screen;
        this.h_screen = h_screen;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.salesbill_list_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(list.get(position).getStatus()==1)
        {
            holder.sll_main.enable = false;
        }
        else
        {
            holder.sll_main.enable = true;
            holder.sll_main.setOnSwipeStatusListener(new XRecycleViewAdapter.MyOnSlipStatusListener(holder.sll_main));
        }
        holder.salesbill_code.setText(list.get(position).getCode().toString());
        holder.salesbill_occurrencetime.setText(list.get(position).getOccurrencetime());
        holder.salesbill_clientstring.setText(list.get(position).getClientString());
        //当数量小于0的时候，数量背景色为红色
        try{
            if(Integer.parseInt(list.get(position).getAmount())<0)
            {
                holder.salesbill_amount.setTextColor(Color.RED);
            }
            else
            {
                holder.salesbill_amount.setTextColor(context.getResources().getColor(R.color.money_in));
            }
        }
        catch (Exception e)
        {
            holder.salesbill_amount.setTextColor(context.getResources().getColor(R.color.money_in));
        }

        holder.salesbill_amount.setText(list.get(position).getAmount().toString());

        holder.salesbill_total.setText(list.get(position).getPreSalesTotal().toString());

        holder.list_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesBillFragment.openActivity(list.get(position).getId());
            }
        });

        if (list.get(position).getRealMoney() != null){
            holder.salesbill_realmoney.setText(list.get(position).getRealMoney().intValue()+"");
        }else {
            holder.salesbill_realmoney.setText("");
        }
        if (list.get(position).getCash().intValue() != 0){
            holder.salesbill_cash.setText("现"+" "+list.get(position).getCash().intValue());
        }else {
            holder.salesbill_cash.setText("");
        }
        if (list.get(position).getSwingCard().intValue() != 0){
            holder.salesbill_swingCard.setText("刷"+" "+list.get(position).getSwingCard().intValue()+"");
        }else {
            holder.salesbill_swingCard.setText("");
        }
        if (list.get(position).getRemit().intValue() != 0){
            holder.salesbill_remit.setText("汇"+" "+list.get(position).getRemit().intValue()+"");
        }else {
            holder.salesbill_remit.setText("");
        }
        if (list.get(position).getStatus().intValue()==2){
            //挂单单据
            holder.itemView.setBackgroundColor(Color.parseColor("#99F999"));
            holder.tv_disable.setText("删除");
            holder.salesbill_line.setVisibility(View.GONE);
            holder.list_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    salesBillFragment.openFragment(list.get(position).getId());
                }
            });
        }else if (list.get(position).getStatus().intValue()==0){
            //正常单据
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tv_disable.setText("作废");
            holder.salesbill_line.setVisibility(View.GONE);

        }else if(list.get(position).getStatus().intValue()==1){
            //作废单据
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tv_disable.setText("作废");
            holder.salesbill_line.setVisibility(View.VISIBLE);
        }

        holder.tv_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.sll_main.setStatus(SwipeListLayout.Status.Close, true);
                //
                Toast.makeText(context, "编辑", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });
        holder.tv_disable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                sll_main.setStatus(SwipeListLayout.Status.Close, true);
//                list.remove(position);
                if (list.get(position).getStatus().intValue()==2)
                {
                    new CommomDialog(context, R.style.dialog, "您确定删除此挂单？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                salesBillFragment.disableSuspendSalesBill(list.get(position).getId(),position);
                                dialog.dismiss();
                            }
                        }
                    }).setTitle("提示").show();
                }
                else
                {
                    new CommomDialog(context, R.style.dialog, "您确定作废此单据？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                salesBillFragment.disableSalesBill(list.get(position).getId());
                                dialog.dismiss();
                            }
                        }
                    }).setTitle("提示").show();
                }

//                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View salesbill_line;
        LinearLayout list_lay;
        TextView salesbill_code;
        TextView salesbill_occurrencetime;
        TextView salesbill_clientstring;
        TextView salesbill_amount;
        TextView salesbill_total;
        TextView salesbill_realmoney;
        LinearLayout lay_paymentString;
        TextView salesbill_cash;
        TextView salesbill_swingCard;
        TextView salesbill_remit;


        TextView tv_update;
        TextView tv_disable;
        SwipeListLayout sll_main ;

        public ViewHolder(View itemView) {
            super(itemView);
            lay_paymentString = itemView.findViewById(R.id.lay_paymentString);
            salesbill_line = itemView.findViewById(R.id.salesbill_line);
            list_lay = itemView.findViewById(R.id.list_lay);
            salesbill_code = itemView.findViewById(R.id.salesbill_code);
            salesbill_occurrencetime = itemView.findViewById(R.id.salesbill_occurrencetime);
            salesbill_clientstring = itemView.findViewById(R.id.salesbill_clientstring);
            salesbill_amount = itemView.findViewById(R.id.salesbill_amount);
            salesbill_total = itemView.findViewById(R.id.salesbill_total);
            salesbill_realmoney = itemView.findViewById(R.id.salesbill_realmoney);
            salesbill_cash = itemView.findViewById(R.id.salesbill_cash);
            salesbill_swingCard = itemView.findViewById(R.id.salesbill_swingCard);
            salesbill_remit = itemView.findViewById(R.id.salesbill_remit);
            tv_update = itemView.findViewById(R.id.tv_update);
            tv_disable = itemView.findViewById(R.id.tv_disable);
            sll_main = itemView.findViewById(R.id.sll_main);


            salesbill_code.setWidth(w_screen*3/35);
            salesbill_occurrencetime.setWidth(w_screen/7);
            salesbill_clientstring.setWidth(w_screen/7);
            salesbill_amount.setWidth(w_screen/7);
            //salesbill_amount.setBackgroundColor(Color.BLUE);
            salesbill_total.setWidth(w_screen/7);
            //salesbill_total.setBackgroundColor(Color.BLACK);
            salesbill_realmoney.setWidth(w_screen/7);
            //salesbill_realmoney.setBackgroundColor(Color.BLUE);
            salesbill_cash.setWidth(w_screen/5);
            salesbill_swingCard.setWidth(w_screen/5);
            salesbill_remit.setWidth(w_screen/5);
        }
    }

    public void clearSwipeLayout(){
        if (sets.size() > 0) {
            for (SwipeListLayout s : sets) {
                s.setStatus(SwipeListLayout.Status.Close, true);
                sets.remove(s);
            }
        }
    }

    class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {
        private SwipeListLayout slipListLayout;
        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
                Log.i("TestLog","tianjiabuju");
            } else {
                if (sets.contains(slipListLayout)){
                    sets.remove(slipListLayout);
                }
            }
        }

        @Override
        public void onStartCloseAnimation() {
        }

        @Override
        public void onStartOpenAnimation() {
        }

    }
}
