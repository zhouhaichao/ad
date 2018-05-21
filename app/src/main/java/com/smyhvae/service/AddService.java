package com.smyhvae.service;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/16.
 */

//针对商品添加
public class AddService {

    public  String addcolor(String url, int loginstaffid, int logininvid, int accountid, String accessKey,String colorName,String rgbcode,Integer groupId,Integer rank)
    {
        String strFlag = null;

        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("color");
        fuInitAccountModel.setMethodid("addColor");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setName(colorName);
        fuAccountModel.setRgbcode(rgbcode);
        fuAccountModel.setGroupid(groupId);
        fuAccountModel.setRank(rank);

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

    public  String addSize(String url, int loginstaffid, int logininvid, int accountid, String accessKey,String sizeName,Integer groupId,Integer rank)
    {
        String strFlag = null;

        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("size");
        fuInitAccountModel.setMethodid("addSize");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setName(sizeName);
        fuAccountModel.setGroupid(groupId);
        fuAccountModel.setRank(rank);

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

    public  String addBrand(String url, int loginstaffid, int logininvid, int accountid, String accessKey,String brandName)
    {
        String strFlag = null;

        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("brand");
        fuInitAccountModel.setMethodid("addBrand");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setName(brandName);


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

    public  String addClass(String url, int loginstaffid, int logininvid, int accountid, String accessKey,String ClassName)
    {
        String strFlag = null;

        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("class");
        fuInitAccountModel.setMethodid("addClass");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setName(ClassName);


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
