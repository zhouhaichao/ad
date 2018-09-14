package com.smyhvae.view;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.myapplication.R;


public class TableAdapter extends BaseAdapter {
	
	private List<FuSalesBillDetailModel> list;
	private LayoutInflater inflater;
    private int w_screen;
	
	public TableAdapter(Context context, List<FuSalesBillDetailModel> list, int w_screen){
		this.list = list;
		inflater = LayoutInflater.from(context);
        this.w_screen = w_screen;
	}

	@Override
	public int getCount() {
		int ret = 0;
		if(list!=null){
			ret = list.size();
		}
		return ret;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		FuSalesBillDetailModel salesBillDetailModel = (FuSalesBillDetailModel) this.getItem(position);
		
		ViewHolder viewHolder;
		
		if(convertView == null){
			
			viewHolder = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.list_item_salesbilllist, null);
			viewHolder.styleCode = (TextView) convertView.findViewById(R.id.stylecode);
			viewHolder.styleString = (TextView) convertView.findViewById(R.id.styleString);
            viewHolder.styleString.setWidth(w_screen/3);
			viewHolder.remarkDetail = (EditText) convertView.findViewById(R.id.remarkDetail);
            viewHolder.remarkDetail.setWidth(w_screen/4-50);
            viewHolder.remarkDetail.setPadding(20, 10, 10, 10);
			viewHolder.colorString = (TextView) convertView.findViewById(R.id.colorString);
            viewHolder.colorString.setWidth(w_screen/8);
            viewHolder.colorString.setSingleLine(true);
            viewHolder.colorString.setEllipsize(TextUtils.TruncateAt.END);
			viewHolder.sizeString = (TextView) convertView.findViewById(R.id.sizeString);
            viewHolder.sizeString.setWidth(w_screen/8);
            viewHolder.sizeString.setSingleLine(true);
            viewHolder.sizeString.setEllipsize(TextUtils.TruncateAt.END);
			viewHolder.amount = (EditText) convertView.findViewById(R.id.amount);
            viewHolder.amount.setWidth(w_screen/7);
            viewHolder.amount.setPadding(20, 10, 10, 10);
			viewHolder.price = (EditText) convertView.findViewById(R.id.price);
            viewHolder.price.setWidth(w_screen/7);
            viewHolder.price.setPadding(20, 10, 10, 10);
			viewHolder.discountdetail = (EditText) convertView.findViewById(R.id.discountdetail);
            viewHolder.discountdetail.setWidth(w_screen/7);
            viewHolder.discountdetail.setPadding(20, 10, 10, 10);
			viewHolder.total = (TextView) convertView.findViewById(R.id.total);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.styleCode.setText(salesBillDetailModel.getStyleCode());
		viewHolder.styleCode.setTextSize(13);
		viewHolder.styleString.setText(salesBillDetailModel.getStyleString());
		viewHolder.styleString.setTextSize(13);
		viewHolder.remarkDetail.setText(salesBillDetailModel.getRemark());
		viewHolder.remarkDetail.setTextSize(13);
		viewHolder.colorString.setText(salesBillDetailModel.getColorString());
		viewHolder.colorString.setTextSize(13);
		viewHolder.sizeString.setText(salesBillDetailModel.getSizeString());
		viewHolder.sizeString.setTextSize(13);
		viewHolder.amount.setText(salesBillDetailModel.getAmount()+"");
		viewHolder.amount.setTextSize(13);
		Double str = Double.valueOf(String.valueOf(salesBillDetailModel.getPrice()));
		String s1 = str.toString();
		String regex = "^(\\d+)(\\.0)$";
		String s = s1.replaceAll(regex,"$1");
		viewHolder.price.setText(s);
		viewHolder.price.setTextSize(13);
		viewHolder.discountdetail.setText(1+"");
		viewHolder.discountdetail.setTextSize(13);
		viewHolder.total.setText(100+"");
		viewHolder.total.setTextSize(13);
		
		return convertView;
	}
	
	public static class ViewHolder{
		public TextView styleCode;
		public TextView styleString;
		public EditText remarkDetail;
		public TextView colorString;
		public TextView sizeString;
		public EditText amount;
		public EditText price;
		public EditText discountdetail;
		public TextView total;
	}

    /**
     * 刷新数据
     *
     * @param list
     */
    public void refreshData(List<FuSalesBillDetailModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
	
}
