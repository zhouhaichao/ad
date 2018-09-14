package com.smyhvae.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuMainModel;
import com.smyhvae.util.DeviceUuidFactory;
import com.smyhvae.util.FuEnvironment;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;
import java.util.UUID;

public class RegeditService extends IntentService {
    private static final String ACTION_FOO = "com.smyhvae.service.action.FOO";
    private static final String ACTION_BAZ = "com.smyhvae.service.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.smyhvae.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.smyhvae.service.extra.PARAM2";

    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";

    public RegeditService() {
        super("RegeditService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, RegeditService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, RegeditService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String strFlag = null;

        //通过intent获取主线程传来的用户名和密码字符串

        String epCode = intent.getStringExtra("epCode");
        strFlag = doRegedit(RegeditService.this, epCode);

        Intent broadcastIntent = new Intent();

        broadcastIntent.setAction(ACTION_RECV_MSG);

        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

        broadcastIntent.putExtra("result", strFlag);

        sendBroadcast(broadcastIntent);
    }

    public String doRegedit(Context context, String epCode){
        String strFlag = null;
        FuEnvironment fuEnvironment = new FuEnvironment();
        DeviceUuidFactory deviceUuidFactory = new DeviceUuidFactory(context);
        UUID uuid = deviceUuidFactory.getDeviceUuid();
        String url = fuEnvironment.getOAURL()+"app/entry.htm?";
        FuMainModel fuMainModel = new FuMainModel();
        fuMainModel.setServiceid("passport");
        fuMainModel.setMethodid("bindPassportStaffI");

        FuBaseModel fuBaseModel = new FuBaseModel();
        fuBaseModel.setUuid(uuid.toString());
        fuBaseModel.setEpCode(epCode);

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

    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
