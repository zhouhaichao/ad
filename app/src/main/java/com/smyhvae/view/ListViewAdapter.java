package com.smyhvae.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.fragment.SalesBillFragment;
import com.smyhvae.myapplication.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListViewAdapter extends BaseAdapter {
    private Set<SwipeListLayout> sets = new HashSet();

    public boolean haveSwipe = false;

    public List<ListItem> list;
    public LayoutInflater inflater;
    private Context context;
    private SalesBillFragment salesBillFragment;
    private int w_screen;
    private int h_screen;

    public ListViewAdapter(SalesBillFragment salesBillFragment, Context context, List<ListItem> list, int w_screen, int h_screen) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.salesBillFragment = salesBillFragment;
        this.w_screen = w_screen;
        this.h_screen = h_screen;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateView(List<ListItem> nowList) {
        this.list = nowList;
        this.notifyDataSetChanged();//强制动态刷新数据进而调用getView方法
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        int width = w_screen/7;
        if(convertView == null) {
            view = inflater.inflate(R.layout.salesbill_list_item, null);
            holder = new ViewHolder();
            holder.list_lay = view.findViewById(R.id.list_lay);
            holder.salesbill_code = (TextView)view.findViewById(R.id.salesbill_code);
            holder.salesbill_code.setWidth(w_screen/12);
            holder.salesbill_occurrencetime = (TextView)view.findViewById(R.id.salesbill_occurrencetime);
            holder.salesbill_occurrencetime.setWidth(width);
            holder.salesbill_clientstring = (TextView)view.findViewById(R.id.salesbill_clientstring);
            holder.salesbill_clientstring.setWidth(width);
            holder.salesbill_amount = (TextView)view.findViewById(R.id.salesbill_amount);
            holder.salesbill_amount.setWidth(width);
            holder.salesbill_total = (TextView)view.findViewById(R.id.salesbill_total);
            holder.salesbill_total.setWidth(width);
            holder.salesbill_realmoney = (TextView)view.findViewById(R.id.salesbill_realmoney);
            holder.salesbill_realmoney.setWidth(width);
            holder.lay_paymentString = view.findViewById(R.id.lay_paymentString);
            LinearLayout.LayoutParams lay_paymentStringparams = new LinearLayout.LayoutParams(w_screen/5, ViewGroup.LayoutParams.WRAP_CONTENT);
            lay_paymentStringparams.gravity= Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
            holder.lay_paymentString.setLayoutParams(lay_paymentStringparams);
            holder.salesbill_cash = (TextView)view.findViewById(R.id.salesbill_cash);
            holder.salesbill_swingCard = (TextView)view.findViewById(R.id.salesbill_swingCard);
            holder.salesbill_remit = (TextView)view.findViewById(R.id.salesbill_remit);
            holder.tv_update = view.findViewById(R.id.tv_update);
            holder.tv_disable = view.findViewById(R.id.tv_disable);
            holder.salesbill_line = view.findViewById(R.id.salesbill_line);
            view.setTag(holder);//为了复用holder
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        final SwipeListLayout sll_main = (SwipeListLayout) view.findViewById(R.id.sll_main);
        sll_main.setOnSwipeStatusListener(new MyOnSlipStatusListener(sll_main));
        holder.salesbill_code.setText(list.get(position).getCode().toString());
        holder.salesbill_occurrencetime.setText(list.get(position).getOccurrencetime());
        holder.salesbill_clientstring.setText(list.get(position).getClientString());
        holder.salesbill_amount.setText(list.get(position).getAmount().toString());
        holder.salesbill_total.setText(list.get(position).getPreSalesTotal().toString());
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
            view.setBackgroundColor(Color.parseColor("#32CD32"));
            holder.salesbill_line.setVisibility(View.GONE);
        }else if (list.get(position).getStatus().intValue()==0){
            view.setBackgroundColor(Color.parseColor("#F8F8FF"));
            holder.salesbill_line.setVisibility(View.GONE);
        }else if(list.get(position).getStatus().intValue()==1){
            view.setBackgroundColor(Color.parseColor("#F8F8FF"));
            holder.salesbill_line.setVisibility(View.VISIBLE);
        }

        holder.list_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesBillFragment.openActivity(list.get(position).getId());
            }
        });

        holder.tv_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sll_main.setStatus(SwipeListLayout.Status.Close, true);
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
                new CommomDialog(context, R.style.dialog, "您确定删除此信息？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            salesBillFragment.disableSalesBill(list.get(position).getId());
                            dialog.dismiss();
                        }
                    }
                }).setTitle("提示").show();
//                notifyDataSetChanged();
            }
        });
        return view;
    }
    static class ViewHolder {
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
            } else {
                if (sets.contains(slipListLayout)){
                    sets.remove(slipListLayout);
                }
            }
            haveSwipe = true;
        }

        @Override
        public void onStartCloseAnimation() {
        }

        @Override
        public void onStartOpenAnimation() {
        }

    }
}
