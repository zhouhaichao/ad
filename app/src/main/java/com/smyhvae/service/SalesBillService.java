package com.smyhvae.service;

import android.util.Log;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.model.FuMoneyModel;
import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.model.FuSalesBillModel;
import com.smyhvae.util.HttpUtil;

import java.util.HashMap;
import java.util.List;

public class SalesBillService {

    public String doSalesBillList(String url, int loginstaffid, int logininvid, String accessKey, int accountid,
                                  int page, int showCount, String beginCode, String start, String end, String clientid, Integer status){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("fuSalesBillList");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setPage(page);
        fuAccountModel.setShowCount(showCount);


        if (beginCode != null && !("").equals(beginCode)){
            fuAccountModel.setBeginCode(beginCode);
        }
        if (start != null && !("").equals(start)){
            fuAccountModel.setBeginOccurrencetime(start);
        }
        if (end != null && !("").equals(end)){
            fuAccountModel.setEndOccurrencetime(end);
        }
        Log.d("sfn", status+"");
        if (status != null && !("").equals(status)){
            if (status > 0){
                fuAccountModel.setStatus(status-1);
            }
        }
        if (clientid != null && !("").equals(clientid)){
            fuAccountModel.setClientid(Integer.valueOf(clientid));
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

    public String doAddSalesBillForSuspend(String url, int loginstaffid, int logininvid, String accessKey, int accountid, FuSalesBillModel fuSalesBillModel, List<FuSalesBillDetailModel> salesBillDetailList, List<FuMoneyModel> moneyList,int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("suspendToNormalSalesBill");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setClientid(fuSalesBillModel.getClientid());
        fuAccountModel.setInvid(logininvid);
        fuAccountModel.setStaffid(fuSalesBillModel.getStaffid());
//      fuAccountModel.setOccurrencetime(fuSalesBillModel.getOccurrencetime());
        fuAccountModel.setId(id);
        fuAccountModel.setLogistics(fuSalesBillModel.getLogistics());
        fuAccountModel.setType(0);
        fuAccountModel.setRemark(fuSalesBillModel.getRemark());
        fuAccountModel.setPricetypeid(fuSalesBillModel.getPricetypeid());
        fuAccountModel.setDiscount(fuSalesBillModel.getDiscount());
        fuAccountModel.setStaffid2(fuSalesBillModel.getStaffid2());
        fuAccountModel.setSalesBillDetailList(salesBillDetailList);
        fuAccountModel.setMoneyList(moneyList);
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

    public String doAddSalesBill(String url, int loginstaffid, int logininvid, String accessKey, int accountid, FuSalesBillModel fuSalesBillModel, List<FuSalesBillDetailModel> salesBillDetailList, List<FuMoneyModel> moneyList){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("addSalesBill");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setClientid(fuSalesBillModel.getClientid());
        fuAccountModel.setInvid(logininvid);
        fuAccountModel.setStaffid(fuSalesBillModel.getStaffid());
//      fuAccountModel.setOccurrencetime(fuSalesBillModel.getOccurrencetime());

        fuAccountModel.setLogistics(fuSalesBillModel.getLogistics());
        fuAccountModel.setType(0);
        fuAccountModel.setRemark(fuSalesBillModel.getRemark());
        fuAccountModel.setPricetypeid(fuSalesBillModel.getPricetypeid());
        fuAccountModel.setDiscount(fuSalesBillModel.getDiscount());
        fuAccountModel.setStaffid2(fuSalesBillModel.getStaffid2());
        fuAccountModel.setSalesBillDetailList(salesBillDetailList);
        fuAccountModel.setMoneyList(moneyList);
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


    public String doSuspendSalesBill(String url, int loginstaffid, int logininvid, String accessKey, int accountid, FuSalesBillModel fuSalesBillModel, List<FuSalesBillDetailModel> salesBillDetailList,int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("suspendSalesBill");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        if(id>=0)
            fuAccountModel.setId(id);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setClientid(fuSalesBillModel.getClientid());
        fuAccountModel.setInvid(logininvid);
        fuAccountModel.setStaffid(fuSalesBillModel.getStaffid());
//      fuAccountModel.setOccurrencetime(fuSalesBillModel.getOccurrencetime());

        fuAccountModel.setLogistics(fuSalesBillModel.getLogistics());
        fuAccountModel.setType(0);
        fuAccountModel.setRemark(fuSalesBillModel.getRemark());
        fuAccountModel.setPricetypeid(fuSalesBillModel.getPricetypeid());
        fuAccountModel.setDiscount(fuSalesBillModel.getDiscount());
        fuAccountModel.setStaffid2(fuSalesBillModel.getStaffid2());
        fuAccountModel.setSalesBillDetailList(salesBillDetailList);
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




    public String doSelectSalesBill(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("selectSalesBill");

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

    public String doDisableSalesBill(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("disableSalesBill");

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

    public String doDisableSuspendSalesBill(String url, int loginstaffid, int logininvid, String accessKey, int accountid, int id){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("salesBill");
        fuInitAccountModel.setMethodid("deleteSuspendedSalesBill");

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
