package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bm.library.PhotoView;
import com.smyhvae.service.PhotoService;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhotoActivity extends Activity{

    PhotoView PV_picasso;
    private int id;

    private MyApplication application;
    private String accessKey;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String fileServerip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photoview);

        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        fileServerip = application.getFileserverip();

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        PV_picasso = findViewById(R.id.PV_dialog);
        PV_picasso.enable();

        PhotoService photoService = new PhotoService();

        Bitmap bitmap =photoService.getUrlPhoto(fileServerip,accessKey,loginstaffid,logininvid,accountid,id,0);

        if(bitmap!=null)
        {
            PV_picasso.setImageBitmap(bitmap);
        }
        PV_picasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });

    }

    public void close()
    {
        finish();
    }
}
