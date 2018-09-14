package com.smyhvae.service;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.model.FuSizeModel;
import com.smyhvae.model.FuStyleClassModel;
import com.smyhvae.model.FuStylePriceTypeModel;
import com.smyhvae.util.HttpUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StyleService {
    public String doStyleList(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int page, int showCount,
                              String code, Integer classid, String season, String name, Integer brandid, Integer status){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("style");
        fuInitAccountModel.setMethodid("fuStyleList");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setPage(page);
        fuAccountModel.setShowCount(showCount);
        fuAccountModel.setCode(code);
        fuAccountModel.setClassid(classid);
        fuAccountModel.setSeason(season);
        fuAccountModel.setName(name);
        fuAccountModel.setBrandid(brandid);
        if (status != null && !("").equals(status)){
            if (status > 0){
                status = status-1;
                fuAccountModel.setStatus(status);
            }
        }
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

    public String doSelectStyle(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("style");
        fuInitAccountModel.setMethodid("selectStyle");

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
    public String doSelectStyleWithClient(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int id,int clientID){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("style");
        fuInitAccountModel.setMethodid("selectStyle");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setClientid(clientID);
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

    public String doAddStyle(String url, int loginstaffid, int logininvid, String accessKey, int accountid, String code, String name, Integer brandid,
                             String season, Integer clientid, String marketdate, String price, String suppliercode, String standardbarcode, String remark,
                             List<FuStyleClassModel> styleClasses, List<FuColorModel> colors, List<FuSizeModel> sizes, List<FuStylePriceTypeModel> pricetypes){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("style");
        fuInitAccountModel.setMethodid("addStyle");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setCode(code);
        fuAccountModel.setName(name);
        fuAccountModel.setBrandid(brandid);
        fuAccountModel.setSeason(season);
        fuAccountModel.setClientid(clientid);
        fuAccountModel.setMarketdate(marketdate);
        fuAccountModel.setPrice(price);
        fuAccountModel.setStyleClasses(styleClasses);
        fuAccountModel.setColors(colors);
        fuAccountModel.setSizes(sizes);
        fuAccountModel.setPricetypes(pricetypes);
        fuAccountModel.setSuppliercode(suppliercode);
        fuAccountModel.setStandardbarcode(standardbarcode);
        fuAccountModel.setRemark(remark);
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


    public String doDeleteStyleIamge(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int ImageId)
    {
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("styleImage");
        fuInitAccountModel.setMethodid("deleteStyleImage");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setId(ImageId);
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



    public String doAddStyleImage(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int styleId, File file)
    {
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("styleImage");
        fuInitAccountModel.setMethodid("addStyleImage");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);

        fuAccountModel.setStyleId(styleId);
        fuInitAccountModel.setParameter(fuAccountModel);

        Gson gson = new Gson();
        String query = gson.toJson(fuInitAccountModel);


        final Map<String, String> params = new HashMap<String, String>();
        params.put("query", query);

        final Map<String, File> files = new HashMap<String, File>();
        files.put("fileTypefileName", file);


        try {

            strFlag = HttpUtil.post(url, params, files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFlag;

    }



    public String doUpadteStyle(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int id, String code, String name, Integer brandid,
                             String season, Integer clientid, String marketdate, String price, String suppliercode, String standardbarcode, String remark,
                             List<FuStyleClassModel> styleClasses, List<FuColorModel> colors, List<FuSizeModel> sizes, List<FuStylePriceTypeModel> pricetypes){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("style");
        fuInitAccountModel.setMethodid("updateStyle");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setId(id);
        fuAccountModel.setCode(code);
        fuAccountModel.setName(name);
        fuAccountModel.setBrandid(brandid);
        fuAccountModel.setSeason(season);
        fuAccountModel.setClientid(clientid);
        fuAccountModel.setMarketdate(marketdate);
        fuAccountModel.setPrice(price);
        fuAccountModel.setStyleClasses(styleClasses);
        fuAccountModel.setColors(colors);
        fuAccountModel.setSizes(sizes);
        fuAccountModel.setPricetypes(pricetypes);
        fuAccountModel.setSuppliercode(suppliercode);
        fuAccountModel.setStandardbarcode(standardbarcode);
        fuAccountModel.setRemark(remark);
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
