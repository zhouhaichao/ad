package com.smyhvae.service;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/18.
 */

public class PhotoService {
    public static int iamgecount = 0;
    public Bitmap getUrlPhoto(String url, String accessKey, int loginstaffid, int logininvid, int accountid, int styleid,int isThumb)
    {
        Bitmap bitmap=null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("styleImage");
        fuInitAccountModel.setMethodid("selectStyleImage");
        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setId(styleid);
        fuAccountModel.setIsThumb(isThumb);

        //fuAccountModel.setsty
        //fuAccountModel.setInput(input);
        //fuAccountModel.setJustForName(justForName);
        fuInitAccountModel.setParameter(fuAccountModel);

        Gson gson = new Gson();
        String query = gson.toJson(fuInitAccountModel);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("query", query);



        //Log.i("TestLog","url:--"+url);
        try {
            bitmap = HttpUtil.postRequestPhoto(url, map);
            iamgecount++;
            Log.i("TestLog","访问图片数量"+iamgecount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }

}
