package com.smyhvae.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.model.FuStockModel;
import com.smyhvae.model.FuStyleColorModel;
import com.smyhvae.model.FuStyleSizeModel;
import com.smyhvae.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class ScrollablePanelAdapter extends PanelAdapter {
    private static final int TITLE_TYPE = 4;
    private static final int COLORS_TYPE = 0;
    private static final int SIZES_TYPE = 1;
    private static final int STOCK_TYPE = 2;

    private List<FuStyleColorModel> styleColorModelList=new ArrayList<>();
    private List<List<FuStockModel>> stockModelList = new ArrayList<>();
    private List<FuStyleSizeModel> styleSizeModelList = new ArrayList<>();
    private List<FuSalesBillDetailModel> salesBillDetailModelList = new ArrayList<>();
    private Context context;

    public ScrollablePanelAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getRowCount() {
        return styleColorModelList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return styleSizeModelList.size() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case SIZES_TYPE:
                setSizesView(column, (SizesViewHolder) holder);
                break;
            case COLORS_TYPE:
                setColorView(row, (ColorViewHolder) holder);
                break;
            case STOCK_TYPE:
                setStockView(row, column, (StockViewHolder) holder);
                break;
            case TITLE_TYPE:
                break;
            default:
                setStockView(row, column, (StockViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return COLORS_TYPE;
        }
        if (row == 0) {
            return SIZES_TYPE;
        }
        return STOCK_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SIZES_TYPE:
                return new SizesViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_sizes_info, parent, false));
            case COLORS_TYPE:
                return new ColorViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_colors_info, parent, false));
            case STOCK_TYPE:
                View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_content_stock_info, parent, false);
                return new StockViewHolder(view);
            case TITLE_TYPE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_title, parent, false));
            default:
                break;
        }
        return new StockViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_content_stock_info, parent, false));
    }


    private void setSizesView(int pos, SizesViewHolder viewHolder) {
        FuStyleSizeModel styleSizeModel = styleSizeModelList.get(pos - 1);
        if (styleSizeModel != null && pos > 0) {
            viewHolder.sizeid.setText(styleSizeModel.getSizeid()+"");
            viewHolder.sizeString.setText(styleSizeModel.getSizeString());
        }
    }

    private void setColorView(int pos, ColorViewHolder viewHolder) {
        FuStyleColorModel styleColorModel = styleColorModelList.get(pos - 1);
        if (styleColorModel != null && pos > 0) {
            viewHolder.colorid.setText(styleColorModel.getColorid()+"");
            viewHolder.colorString.setText(styleColorModel.getColorString());
        }
    }

    private void setStockView(final int row, final int column, final StockViewHolder viewHolder) {
        LinearLayout linearLayout = viewHolder.list_stock;
        LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.removeAllViews();
        if (stockModelList.size()>0){
            final FuStockModel stockModel = stockModelList.get(row-1).get(column-1);
            if (stockModel != null) {
                for (FuStockModel fuStockModel : stockModel.getFuStockList()) {
                    if (fuStockModel.getAmount() != null){
                        LinearLayout linearLayout1 = new LinearLayout(context);
                        TextView invString = new TextView(context);
                        invString.setText(fuStockModel.getInvString());
                        invString.setTextColor(Color.argb(0xff, 0x00, 0x00, 0x00));
                        invString.setTextSize(14);
                        TextView sum = new TextView(context);
                        sum.setText(fuStockModel.getAmount()+"");
                        sum.setTextColor(Color.argb(0xff, 0x00, 0x00, 0x00));
                        sum.setTextSize(12);
                        sum.setPadding(5, 0, 0, 0);
                        linearLayout1.addView(invString, LP_FW);
                        linearLayout1.addView(sum, LP_FW);
                        linearLayout.addView(linearLayout1, LP_FW);//全部用父结点的布局参数
                        break;
                    }
                }
            }
            else
            {
                linearLayout.removeAllViews();
            }
        }

        viewHolder.reduce.setClickable(true);
        viewHolder.add.setClickable(true);
        viewHolder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reduceSum(viewHolder);


            }
        });
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSum(viewHolder);
            }
        });

        viewHolder.salesDetailModel = new FuSalesBillDetailModel();

        viewHolder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!("").equals(viewHolder.editText.getText().toString())){

                    if (viewHolder.editText.getText().toString().length()==1 && viewHolder.editText.getText().toString().indexOf("-") != -1){

                    }else{
                        final FuStyleColorModel fuStyleColorModel = styleColorModelList.get(row - 1);
                        final FuStyleSizeModel fuStyleSizeModel = styleSizeModelList.get(column - 1);
                        viewHolder.salesDetailModel.setColorid(fuStyleColorModel.getColorid());
                        viewHolder.salesDetailModel.setColorString(fuStyleColorModel.getColorString());
                        viewHolder.salesDetailModel.setSizeid(fuStyleSizeModel.getSizeid());
                        viewHolder.salesDetailModel.setSizeString(fuStyleSizeModel.getSizeString());
                        viewHolder.salesDetailModel.setAmount(Integer.parseInt(viewHolder.editText.getText().toString()));
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        salesBillDetailModelList.add(viewHolder.salesDetailModel);

    }


    private static class SizesViewHolder extends RecyclerView.ViewHolder {
        public TextView sizeid;
        public TextView sizeString;

        public SizesViewHolder(View itemView) {
            super(itemView);
            this.sizeid = (TextView) itemView.findViewById(R.id.sizeid);
            this.sizeString = (TextView) itemView.findViewById(R.id.sizeString);
        }

    }

    private static class ColorViewHolder extends RecyclerView.ViewHolder {
        public TextView colorid;
        public TextView colorString;

        public ColorViewHolder(View view) {
            super(view);
            this.colorid = (TextView) view.findViewById(R.id.colorid);
            this.colorString = (TextView) view.findViewById(R.id.colorString);
        }
    }

    private static class StockViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public LinearLayout list_stock;
        public TextView reduce;
        public TextView add;
        public EditText editText;
        public FuSalesBillDetailModel salesDetailModel;

        public StockViewHolder(View view) {
            super(view);
            this.view = view;
            this.list_stock = view.findViewById(R.id.list_stock);
            this.reduce = view.findViewById(R.id.reduce);
            this.add = view.findViewById(R.id.add);
            this.editText = view.findViewById(R.id.editText);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }

    private void addSum(StockViewHolder viewHolder){
        String sum = viewHolder.editText.getText().toString();
        if (sum.equals("")){
            sum = "0";
        }
        viewHolder.editText.setText(String.valueOf((Integer.parseInt(sum)+1)));
    }

    private void reduceSum(StockViewHolder viewHolder){
        String sum = viewHolder.editText.getText().toString();
        if (sum.equals("")){
            sum = "0";
        }
        viewHolder.editText.setText(String.valueOf((Integer.parseInt(sum)-1)));
    }

    public void setStyleColorModelList(List<FuStyleColorModel> styleColorModelList) {
        this.styleColorModelList = styleColorModelList;
    }

    public void setStyleSizeModelList(List<FuStyleSizeModel> styleSizeModelList) {
        this.styleSizeModelList = styleSizeModelList;
    }

    public void setStockModelList(List<List<FuStockModel>> stockModelList) {
        this.stockModelList = stockModelList;
    }

    public List<FuSalesBillDetailModel> getSalesBillDetailModelList() {
        return salesBillDetailModelList;
    }

    public void setSalesBillDetailModelList(List<FuSalesBillDetailModel> salesBillDetailModelList) {
        this.salesBillDetailModelList = salesBillDetailModelList;
    }
}
