package com.smyhvae.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LoginIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.smyhvae.myapplication.action.FOO";
    private static final String ACTION_BAZ = "com.smyhvae.myapplication.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.smyhvae.myapplication.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.smyhvae.myapplication.extra.PARAM2";

    private static final String ACTION_RECV_MSG = "com.smyhvae.myapplication.action.RECEIVE_MESSAGE";

    public LoginIntentService() {
        super("LoginIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, LoginIntentService.class);
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
        Intent intent = new Intent(context, LoginIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Boolean flag = false;

        //通过intent获取主线程传来的用户名和密码字符串

        String username = intent.getStringExtra("username");
        String psw = intent.getStringExtra("psw");
        flag = doLogin(username, psw);
        Log.d("登录结果", flag.toString());



        Intent broadcastIntent = new Intent();

        broadcastIntent.setAction(ACTION_RECV_MSG);

        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

        broadcastIntent.putExtra("result", flag.toString());

        sendBroadcast(broadcastIntent);
    }

    // 定义发送请求的方法
    private Boolean doLogin(String username, String password) {

        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("login");
        fuInitAccountModel.setMethodid("login");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey("generateKeyInvincible22016060423");
        fuAccountModel.setCode(username);
        fuAccountModel.setPassword(password);
        fuAccountModel.setAccountid(75);

        fuInitAccountModel.setParameter(fuAccountModel);

        Gson gson = new Gson();
        String allParameter = gson.toJson(fuInitAccountModel);
        System.out.println("############"+allParameter+"############");

        String strFlag = "";
        // 使用Map封装请求参数
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("code", username);
//        map.put("password", password);

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + allParameter;
        // String url = HttpUtil.BASE_URL + LoginServlet; //POST方式
        Log.d(username, username);
        Log.d(password, password);
        try {
            // 发送请求
//            strFlag = HttpUtil.postRequest(url, map);  //POST方式
            strFlag = HttpUtil.getRequest(url);
            Log.d("服务器返回值", strFlag);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (strFlag.trim().equals(true)) {
            return true;
        } else {
            return false;
        }
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
