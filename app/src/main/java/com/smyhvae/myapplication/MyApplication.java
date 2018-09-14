package com.smyhvae.myapplication;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuStaffModel;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {
    private String accessKey;
    private String webServerUrl;
    private FuStaffModel fuStaffModel;
    private FuAccountModel fuAccountModel;
    private String fileserverip;

    public String getFileUploadIp() {
        return fileUploadIp;
    }

    public void setFileUploadIp(String fileUploadIp) {
        this.fileUploadIp = fileUploadIp;
    }

    private String fileUploadIp;

    private static Context mContext;


    public static Context getContext() {
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        mContext = getApplicationContext();
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getWebServerUrl() {
        return webServerUrl;
    }

    public void setWebServerUrl(String webServerUrl) {
        this.webServerUrl = webServerUrl;
    }

    public FuStaffModel getFuStaffModel() {
        return fuStaffModel;
    }

    public void setFuStaffModel(FuStaffModel fuStaffModel) {
        this.fuStaffModel = fuStaffModel;
    }

    public FuAccountModel getFuAccountModel() {
        return fuAccountModel;
    }

    public void setFuAccountModel(FuAccountModel fuAccountModel) {
        this.fuAccountModel = fuAccountModel;
    }

    public String getFileserverip() {
        return fileserverip;
    }

    public void setFileserverip(String fileserverip) {
        this.fileserverip = fileserverip;
    }
}
