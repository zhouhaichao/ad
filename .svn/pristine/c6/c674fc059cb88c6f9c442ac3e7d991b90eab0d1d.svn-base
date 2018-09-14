package com.smyhvae.service;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;

public class LoginService {

    public String doLogin(String url, String code, String psw, String accessKey, int accountid){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("login");
        fuInitAccountModel.setMethodid("login");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setCode(code);
        fuAccountModel.setPassword(psw);
        fuAccountModel.setAccountid(accountid);

        fuInitAccountModel.setParameter(fuAccountModel);
        Gson gson = new Gson();
        String query = gson.toJson(fuInitAccountModel);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("query", query);

        try {
            strFlag = HttpUtil.postRequest(url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strFlag;
    }
}
