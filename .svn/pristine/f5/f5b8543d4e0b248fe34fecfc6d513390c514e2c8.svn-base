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

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ValidateService extends IntentService {
    private static final String ACTION_FOO = "com.smyhvae.service.action.FOO";
    private static final String ACTION_BAZ = "com.smyhvae.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.smyhvae.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.smyhvae.service.extra.PARAM2";

    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";

    public ValidateService() {
        super("ValidateService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ValidateService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ValidateService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String strFlag = null;

        //通过intent获取主线程传来的用户名和密码字符串


        strFlag = doValidate(ValidateService.this);

        Intent broadcastIntent = new Intent();

        broadcastIntent.setAction(ACTION_RECV_MSG);

        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

        broadcastIntent.putExtra("result", strFlag);

        sendBroadcast(broadcastIntent);
    }

    public String doValidate(Context context){
        String strFlag = null;
        FuEnvironment fuEnvironment = new FuEnvironment();
        DeviceUuidFactory deviceUuidFactory = new DeviceUuidFactory(context);
        UUID uuid = deviceUuidFactory.getDeviceUuid();
        String url = fuEnvironment.getOAURL()+"app/entry.htm?";
        FuMainModel fuMainModel = new FuMainModel();
        fuMainModel.setServiceid("passport");
        fuMainModel.setMethodid("checkPassportStaffI");

        FuBaseModel fuBaseModel = new FuBaseModel();
        fuBaseModel.setUuid(uuid.toString());

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

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
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
