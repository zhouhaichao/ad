package com.smyhvae.util;

import android.content.Context;

import com.google.gson.Gson;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuMainModel;

import java.util.UUID;


public class Validate {
    private FuEnvironment fuEnvironment;

    public String doPassport(Context context){
        String strFlag = null;
        DeviceUuidFactory deviceUuidFactory = new DeviceUuidFactory(context);
        UUID uuid = deviceUuidFactory.getDeviceUuid();
        String url = fuEnvironment.getOAURL();
        FuMainModel fuMainModel = new FuMainModel();
        fuMainModel.setServiceid("passport");
        fuMainModel.setMethodid("checkPassport");

        FuBaseModel fuBaseModel = new FuBaseModel();
        fuBaseModel.setUuid(uuid.toString());

        fuMainModel.setParameter(fuBaseModel);

        Gson gson = new Gson();
        String allParameter = gson.toJson(fuMainModel);
        System.out.println("############"+allParameter+"############");
        url = url + allParameter;

        try {
            strFlag = HttpUtil.getRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFlag;
    }

}
