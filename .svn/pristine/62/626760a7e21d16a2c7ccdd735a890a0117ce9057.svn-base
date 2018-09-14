package com.smyhvae.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuMainModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;


public class HandlerService extends IntentService {
    private static final String ACTION_FOO = "com.smyhvae.service.action.FOO";
    private static final String ACTION_BAZ = "com.smyhvae.service.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.smyhvae.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.smyhvae.service.extra.PARAM2";

    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";

    public HandlerService() {
        super("HandlerService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, HandlerService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, HandlerService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String strFlag = null;
        String key = null;
        Integer epid = intent.getExtras().getInt("epid");
        String interfaceName = intent.getStringExtra("interfaceName");
        String url = intent.getStringExtra("webServerUrl");

        strFlag = doQueryEpidAccount(url, epid);

        key = doQueryKey(url, interfaceName);

        Intent broadcastIntent = new Intent();

        broadcastIntent.setAction(ACTION_RECV_MSG);

        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

        broadcastIntent.putExtra("result", strFlag);
        broadcastIntent.putExtra("key", key);

        sendBroadcast(broadcastIntent);
    }

    public String doQueryEpidAccount(String url, Integer epid){
        String strFlag = null;
        FuMainModel fuMainModel = new FuMainModel();
        fuMainModel.setServiceid("account");
        fuMainModel.setMethodid("queryEpidAccount");

        FuBaseModel fuBaseModel = new FuBaseModel();
        fuBaseModel.setEpid(epid);

        fuMainModel.setParameter(fuBaseModel);

        Gson gson = new Gson();
        String query = gson.toJson(fuMainModel);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("query", query);

        try {
            strFlag = HttpUtil.postRequest(url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFlag;
    }

    public String doQueryKey(String url, String interfaceName){
        String key = null;
        FuMainModel fuMainModel = new FuMainModel();
        fuMainModel.setServiceid("accessKey");
        fuMainModel.setMethodid(interfaceName);

        FuBaseModel fuBaseModel = new FuBaseModel();

        fuMainModel.setParameter(fuBaseModel);
        Gson gson = new Gson();
        String query = gson.toJson(fuMainModel);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("query", query);

        try {
            key = HttpUtil.postRequest(url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }


    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
