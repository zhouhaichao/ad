package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyhvae.model.FuSizeGroupModel;
import com.smyhvae.model.FuSizeModel;
import com.smyhvae.view.SelectSizeGroupAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectSizeActivity extends Activity implements View.OnClickListener{
    int w_screen=0;
    int h_screen=0;

    private ImageView deleteView;
    private TextView selectinfo;
    private ImageView addView;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SelectSizeGroupAdapter sizeGroupAdapter;

    private List<FuSizeModel> sizes = new ArrayList<>();
    private List<FuSizeGroupModel> sizeGroups = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_size);
        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        Intent intent = getIntent();
        sizes = (List<FuSizeModel>) intent.getSerializableExtra("sizes");
        sizeGroups = (List<FuSizeGroupModel>) intent.getSerializableExtra("sizeGroups");
        initview();
    }

    public void initview(){
        deleteView = this.findViewById(R.id.deleteView);
        //selectinfo = this.findViewById(R.id.selectinfo);
        addView = this.findViewById(R.id.addView);
        deleteView.setOnClickListener(this);
       /*LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        infoParams.gravity= Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        infoParams.setMargins(w_screen/4, 0, w_screen/4, 0);
        selectinfo.setLayoutParams(infoParams);*/
        addView.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sizeGroupAdapter = new SelectSizeGroupAdapter(getApplicationContext(), sizeGroups, sizes);
        recyclerView.setAdapter(sizeGroupAdapter);
    }

    public static final int CODE=1002;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deleteView:
                this.finish();
            case R.id.addView:
                List<FuSizeModel> fuSizeList = new ArrayList<>();
                List<List<FuSizeModel>> sizeList = sizeGroupAdapter.getSizeList();
                for (List<FuSizeModel> sizeModelList : sizeList){
                    for (FuSizeModel sizeModel : sizeModelList){
                        if (sizeModel.getSelectStatus() != null){
                            Log.d("sfn", sizeModel.toString());
                            fuSizeList.add(sizeModel);
                        }
                    }
                }
                Intent myIntent = new Intent();
                Bundle bundle = new Bundle();
                myIntent.putExtra("sizes", (Serializable) fuSizeList);
                myIntent.putExtras(bundle);
                setResult(CODE, myIntent);
                this.finish();
                break;
            default:
                break;
        }
    }
}