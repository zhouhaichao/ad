package com.smyhvae.service;

import com.google.gson.Gson;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuInitAccountModel;
import com.smyhvae.util.HttpUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class ClientService {

    public String doAddClient(String url, int loginstaffid, int logininvid, String accessKey, int accountid, String name, String phone,
                              String address, BigDecimal discount, Date birthday, Integer pricetypeid, Integer staffid, Integer type, String credit,
                              String alarmcredit, Integer invid, String identity, String area, Integer kindid, Integer parentid, String remark){
        String strFlag = null;
        FuInitAccountModel fuInitAccountModel = new FuInitAccountModel();
        fuInitAccountModel.setServiceid("client");
        fuInitAccountModel.setMethodid("addClient");

        FuAccountModel fuAccountModel = new FuAccountModel();
        fuAccountModel.setAccessKey(accessKey);
        fuAccountModel.setAccountid(accountid);
        fuAccountModel.setLoginstaffid(loginstaffid);
        fuAccountModel.setLogininvid(logininvid);
        fuAccountModel.setName(name);
        fuAccountModel.setPhone(phone);
        fuAccountModel.setAddress(address);
        fuAccountModel.setDiscount(discount);
        fuAccountModel.setBirthday(birthday);
        fuAccountModel.setPricetypeid(pricetypeid);
        fuAccountModel.setStaffid(staffid);
        fuAccountModel.setType(type);
        fuAccountModel.setCredit(credit);
        fuAccountModel.setAlarmcredit(alarmcredit);
        fuAccountModel.setInvid(invid);
        fuAccountModel.setIdentity(identity);
        fuAccountModel.setArea(area);
        fuAccountModel.setKindid(kindid);
        fuAccountModel.setParentid(parentid);
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
