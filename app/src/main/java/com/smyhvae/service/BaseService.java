package com.smyhvae.service;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;


public class BaseService {

    public String doListForAC(String url, String methodid, int loginstaffid, int logininvid, String accessKey, int accountid, String input, Integer justForName){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("ac");
        fuInitAccountModel.setMethodid(methodid);

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setInput(input);
        fuAccountModel.setJustForName(justForName);

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

    public String doList(String url, int loginstaffid, int logininvid, String accessKey, int accountid, String serviceid, String methoid){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid(serviceid);
        fuInitAccountModel.setMethodid(methoid);

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);

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

    public String doSelectClient(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int clientid){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("client");
        fuInitAccountModel.setMethodid("selectClient");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setId(clientid);

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

    public String doSelectData(String url, String serviceid, String methoid, int loginstaffid, int logininvid, String accessKey, int accountid, int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid(serviceid);
        fuInitAccountModel.setMethodid(methoid);

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setId(id);

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
