package com.smyhvae.util;

import android.graphics.Bitmap;

import com.google.gson.JsonPrimitive;
import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuAmountModel;
import com.smyhvae.model.FuAuthorityModel;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuClientModel;
import com.smyhvae.model.FuColorGroupModel;
import com.smyhvae.model.FuColorModel;
import com.smyhvae.model.FuDynamicModel;
import com.smyhvae.model.FuExtraMoneyFixModel;
import com.smyhvae.model.FuInventoryBankAccountModel;
import com.smyhvae.model.FuInventoryModel;
import com.smyhvae.model.FuMoneyAccountModel;
import com.smyhvae.model.FuMoneyModel;
import com.smyhvae.model.FuPaymentModel;
import com.smyhvae.model.FuRoleModel;
import com.smyhvae.model.FuSalesBillDetailModel;
import com.smyhvae.model.FuSalesBillModel;
import com.smyhvae.model.FuSizeGroupModel;
import com.smyhvae.model.FuSizeModel;
import com.smyhvae.model.FuStaffModel;
import com.smyhvae.model.FuStockModel;
import com.smyhvae.model.FuStyleBarcodeInfoModel;
import com.smyhvae.model.FuStyleClassModel;
import com.smyhvae.model.FuStyleColorModel;
import com.smyhvae.model.FuStyleImageModel;
import com.smyhvae.model.FuStyleModel;
import com.smyhvae.model.FuStylePriceTypeModel;
import com.smyhvae.model.FuStyleSizeModel;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataUtil {

    public FuAccountModel getAccountData(String data) {
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");
        FuAccountModel fuAccountModel = new FuAccountModel();
        Integer id = jsonUtility.getIntegerValueFromJSON(result, "id");
        String name = jsonUtility.getStringValueFromJSON(result, "name");
        Integer isdefault = jsonUtility.getIntegerValueFromJSON(result, "isdefault");
        Integer epid = jsonUtility.getIntegerValueFromJSON(result, "epid");
        Integer colorsizemode = jsonUtility.getIntegerValueFromJSON(result, "colorsizemode");
        Integer scoremode = jsonUtility.getIntegerValueFromJSON(result, "scoremode");
        Integer balancetype = jsonUtility.getIntegerValueFromJSON(result, "balancetype");
        Integer calculatetype = jsonUtility.getIntegerValueFromJSON(result, "calculatetype");
        Integer inratio = jsonUtility.getIntegerValueFromJSON(result, "inratio");
        Integer outratio = jsonUtility.getIntegerValueFromJSON(result, "outratio");
        Integer appendclientinfo = jsonUtility.getIntegerValueFromJSON(result, "appendclientinfo");
        Integer uselastprice = jsonUtility.getIntegerValueFromJSON(result, "uselastprice");
        Integer iscolortitlefirst = jsonUtility.getIntegerValueFromJSON(result, "iscolortitlefirst");
        Integer tiprepback = jsonUtility.getIntegerValueFromJSON(result, "tiprepback");
        Integer tipbackvalidation = jsonUtility.getIntegerValueFromJSON(result, "tipbackvalidation");
        Integer mp_effective = jsonUtility.getIntegerValueFromJSON(result, "mp_effective");
        Integer marketcommodity = jsonUtility.getIntegerValueFromJSON(result, "marketcommodity");
        Integer autoupdatestyleinfo = jsonUtility.getIntegerValueFromJSON(result, "autoupdatestyleinfo");
        Integer needprintarrears = jsonUtility.getIntegerValueFromJSON(result, "needprintarrears");
        Integer msb_effective = jsonUtility.getIntegerValueFromJSON(result, "msb_effective");
        Integer validateforcheckbill = jsonUtility.getIntegerValueFromJSON(result, "validateforcheckbill");
        Integer datb_effective = jsonUtility.getIntegerValueFromJSON(result, "datb_effective");
        Integer autofixclient = jsonUtility.getIntegerValueFromJSON(result, "autofixclient");
        Integer verification_effective = jsonUtility.getIntegerValueFromJSON(result, "verification_effective");
        Integer nonnegative = jsonUtility.getIntegerValueFromJSON(result, "nonnegative");
        Integer statementforarrears = jsonUtility.getIntegerValueFromJSON(result, "statementforarrears");
        Integer defaultstaff = jsonUtility.getIntegerValueFromJSON(result, "defaultstaff");
        Integer inventoryverify = jsonUtility.getIntegerValueFromJSON(result, "inventoryverify");
        Integer autofixdiscount = jsonUtility.getIntegerValueFromJSON(result, "autofixdiscount");
        Integer stylecolorsizeinfo = jsonUtility.getIntegerValueFromJSON(result, "stylecolorsizeinfo");
        Integer groupbilling = jsonUtility.getIntegerValueFromJSON(result, "groupbilling");
        Integer partnerstaff = jsonUtility.getIntegerValueFromJSON(result, "partnerstaff");
        Integer not_modify_bill_date = jsonUtility.getIntegerValueFromJSON(result, "not_modify_bill_date");
        Integer autosync = jsonUtility.getIntegerValueFromJSON(result, "autosync");
        Integer not_modify_styleinfo = jsonUtility.getIntegerValueFromJSON(result, "not_modify_styleinfo");
        Integer mpt_effective = jsonUtility.getIntegerValueFromJSON(result, "mpt_effective");
        Integer md_effective = jsonUtility.getIntegerValueFromJSON(result, "md_effective");
        Integer invstock = jsonUtility.getIntegerValueFromJSON(result, "invstock");
        Integer autocreatestylecode = jsonUtility.getIntegerValueFromJSON(result, "autocreatestylecode");
        Integer inventoryforarrears = jsonUtility.getIntegerValueFromJSON(result, "inventoryforarrears");
        Integer primecostmode = jsonUtility.getIntegerValueFromJSON(result, "primecostmode");
        fuAccountModel.setId(id);
        fuAccountModel.setName(name);
        fuAccountModel.setIsdefault(isdefault);
        fuAccountModel.setEpid(epid);
        fuAccountModel.setColorsizemode(colorsizemode);
        fuAccountModel.setScoremode(scoremode);
        fuAccountModel.setBalancetype(balancetype);
        fuAccountModel.setCalculatetype(calculatetype);
        fuAccountModel.setInratio(inratio);
        fuAccountModel.setOutratio(outratio);
        fuAccountModel.setAppendclientinfo(appendclientinfo);
        fuAccountModel.setUselastprice(uselastprice);
        fuAccountModel.setIscolortitlefirst(iscolortitlefirst);
        fuAccountModel.setTiprepback(tiprepback);
        fuAccountModel.setTipbackvalidation(tipbackvalidation);
        fuAccountModel.setMp_effective(mp_effective);
        fuAccountModel.setMarketcommodity(marketcommodity);
        fuAccountModel.setAutoupdatestyleinfo(autoupdatestyleinfo);
        fuAccountModel.setNeedprintarrears(needprintarrears);
        fuAccountModel.setMsb_effective(msb_effective);
        fuAccountModel.setValidateforcheckbill(validateforcheckbill);
        fuAccountModel.setDatb_effective(datb_effective);
        fuAccountModel.setAutofixclient(autofixclient);
        fuAccountModel.setVerification_effective(verification_effective);
        fuAccountModel.setNonnegative(nonnegative);
        fuAccountModel.setStatementforarrears(statementforarrears);
        fuAccountModel.setDefaultstaff(defaultstaff);
        fuAccountModel.setInventoryverify(inventoryverify);
        fuAccountModel.setAutofixdiscount(autofixdiscount);
        fuAccountModel.setStylecolorsizeinfo(stylecolorsizeinfo);
        fuAccountModel.setGroupbilling(groupbilling);
        fuAccountModel.setPartnerstaff(partnerstaff);
        fuAccountModel.setNot_modify_bill_date(not_modify_bill_date);
        fuAccountModel.setAutosync(autosync);
        fuAccountModel.setNot_modify_styleinfo(not_modify_styleinfo);
        fuAccountModel.setMpt_effective(mpt_effective);
        fuAccountModel.setMd_effective(md_effective);
        fuAccountModel.setInvstock(invstock);
        fuAccountModel.setAutocreatestylecode(autocreatestylecode);
        fuAccountModel.setInventoryforarrears(inventoryforarrears);
        fuAccountModel.setPrimecostmode(primecostmode);
        return fuAccountModel;
    }

    public FuBaseModel message(String data){
        FuBaseModel baseModel = new FuBaseModel();
        try{
            Map<String, Object> queryMap = JsonToMap.toMap(data);
            Integer resultCode = ((JsonPrimitive)(queryMap.get("resultCode"))).getAsInt();
            String message = ((JsonPrimitive)(queryMap.get("message"))).getAsString();
            baseModel.setResultCode(resultCode);
            baseModel.setMessage(message);
        }catch (Exception e)
        {
            e.printStackTrace();
        }



        return baseModel;
    }

    public int count(String data){
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Integer count = ((JsonPrimitive)(queryMap.get("count"))).getAsInt();
        return count;
    }

    public FuAmountModel sum(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "sumAmount");
        FuAmountModel amountModel = new FuAmountModel();
        amountModel.setSumAmount(jsonUtility.getBigDecimalValueFromJSON(result, "amount"));
        amountModel.setSumTotal(jsonUtility.getBigDecimalValueFromJSON(result, "preSalesTotal"));
        amountModel.setSumRealMoney(jsonUtility.getBigDecimalValueFromJSON(result, "realMoney"));
        return amountModel;
    }

    public List getData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuAccountModel> accountModelList = new ArrayList<FuAccountModel>();
        for (Map<String, Object> map: result){
            FuAccountModel fuAccountModel = new FuAccountModel();

            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer isdefault = jsonUtility.getIntegerValueFromJSON(map, "isdefault");
            Integer epid = jsonUtility.getIntegerValueFromJSON(map, "epid");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");

            fuAccountModel.setName(name);
            fuAccountModel.setIsdefault(isdefault);
            fuAccountModel.setEpid(epid);
            fuAccountModel.setId(id);

            accountModelList.add(fuAccountModel);
        }
        return accountModelList;
    }

    public String getKey(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);

        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");

        String key = jsonUtility.getStringValueFromJSON(result, "key");

        return key;
    }

    public FuStaffModel getStaffData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");
        FuStaffModel fuStaffModel = new FuStaffModel();
        //staff
        String code = jsonUtility.getStringValueFromJSON(result, "code");
        String password = jsonUtility.getStringValueFromJSON(result, "password");
        String name = jsonUtility.getStringValueFromJSON(result, "name");
        String searchkey = jsonUtility.getStringValueFromJSON(result, "searchkey");
        String qrcodewx = jsonUtility.getStringValueFromJSON(result, "qrcodewx");
        Integer depid = jsonUtility.getIntegerValueFromJSON(result, "depid");
        Integer status = jsonUtility.getIntegerValueFromJSON(result, "status");

        //门店
        List<Map<String, Object>> fuInventoryList = jsonUtility.getListValueFromJSON(result, "fuInventoryList");
        List<FuInventoryModel> inventoryModelList = new ArrayList<>();
        for (Map<String, Object> one : fuInventoryList){
            FuInventoryModel inventoryModel = new FuInventoryModel();
            String phone = jsonUtility.getStringValueFromJSON(one, "phone");
            String cellphone = jsonUtility.getStringValueFromJSON(one, "cellphone");
            String wechat = jsonUtility.getStringValueFromJSON(one, "wechat");
            String alipay = jsonUtility.getStringValueFromJSON(one, "alipay");
            String accountname = jsonUtility.getStringValueFromJSON(one, "accountname");
            String address = jsonUtility.getStringValueFromJSON(one, "address");
            Integer type = jsonUtility.getIntegerValueFromJSON(one, "type");
            Integer pricetypeid = jsonUtility.getIntegerValueFromJSON(one, "pricetypeid");
            Integer depid2 = jsonUtility.getIntegerValueFromJSON(one, "depid");
            Integer islocked = jsonUtility.getIntegerValueFromJSON(one, "islocked");
            Integer usepurchaseprice = jsonUtility.getIntegerValueFromJSON(one, "usepurchaseprice");
            String realalipay = jsonUtility.getStringValueFromJSON(one, "realalipay");
            String wechatpay = jsonUtility.getStringValueFromJSON(one, "wechatpay");
            Integer printrealalipay = jsonUtility.getIntegerValueFromJSON(one, "printrealalipay");
            Integer printwechatpay = jsonUtility.getIntegerValueFromJSON(one, "printwechatpay");
            BigDecimal discount = jsonUtility.getBigDecimalValueFromJSON(one, "discount");
            String realalipaystring = jsonUtility.getStringValueFromJSON(one, "realalipaystring");
            String wechatpaystring = jsonUtility.getStringValueFromJSON(one, "wechatpaystring");
            String priceTypeString = jsonUtility.getStringValueFromJSON(one, "priceTypeString");
            String title2 = jsonUtility.getStringValueFromJSON(one, "title2");

            //门店银行账户
            List<Map<String, Object>> fuInventoryBankAccountList = jsonUtility.getListValueFromJSON(one, "fuInventoryBankAccountList");
            List<FuInventoryBankAccountModel> inventoryBankAccountList = new ArrayList<>();
            for (Map<String, Object> two : fuInventoryBankAccountList){
                FuInventoryBankAccountModel inventoryBankAccountModel = new FuInventoryBankAccountModel();
                Integer invid = jsonUtility.getIntegerValueFromJSON(two, "invid");
                String cardname = jsonUtility.getStringValueFromJSON(two, "cardname");
                Integer id = jsonUtility.getIntegerValueFromJSON(two, "id");
                String nameForBankAccount = jsonUtility.getStringValueFromJSON(two, "name");
                Integer accountid = jsonUtility.getIntegerValueFromJSON(two, "accountid");
                Integer flag = jsonUtility.getIntegerValueFromJSON(two, "flag");
                Integer opid = jsonUtility.getIntegerValueFromJSON(two, "opid");
                Date optime = null;
                try {
                    optime = jsonUtility.getDateValueFromJSON(two, "optime");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                inventoryBankAccountModel.setInvid(invid);
                inventoryBankAccountModel.setCardname(cardname);
                inventoryBankAccountModel.setId(id);
                inventoryBankAccountModel.setName(nameForBankAccount);
                inventoryBankAccountModel.setAccountid(accountid);
                inventoryBankAccountModel.setFlag(flag);
                inventoryBankAccountModel.setOpid(opid);
                inventoryBankAccountModel.setOptime(optime);

                inventoryBankAccountList.add(inventoryBankAccountModel);
            }
            Integer invid = jsonUtility.getIntegerValueFromJSON(one, "id");
            String invString = jsonUtility.getStringValueFromJSON(one, "name");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(one, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(one, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(one, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(one, "opid");

            inventoryModel.setPhone(phone);
            inventoryModel.setCellphone(cellphone);
            inventoryModel.setWechat(wechat);
            inventoryModel.setAlipay(alipay);
            inventoryModel.setAccountname(accountname);
            inventoryModel.setAddress(address);
            inventoryModel.setType(type);
            inventoryModel.setPricetypeid(pricetypeid);
            inventoryModel.setDepid(depid2);
            inventoryModel.setIslocked(islocked);
            inventoryModel.setUsepurchaseprice(usepurchaseprice);
            inventoryModel.setRealalipay(realalipay);
            inventoryModel.setWechatpay(wechatpay);
            inventoryModel.setPrintrealalipay(printrealalipay);
            inventoryModel.setPrintwechatpay(printwechatpay);
            inventoryModel.setDiscount(discount);
            inventoryModel.setRealalipaystring(realalipaystring);
            inventoryModel.setWechatpaystring(wechatpaystring);
            inventoryModel.setPriceTypeString(priceTypeString);
            inventoryModel.setPriceTypeString(title2);
            inventoryModel.setFuInventoryBankAccountList(inventoryBankAccountList);
            inventoryModel.setId(invid);
            inventoryModel.setName(invString);
            inventoryModel.setAccountid(accountid);
            inventoryModel.setFlag(flag);
            inventoryModel.setOptime(optime);
            inventoryModel.setOpid(opid);

            inventoryModelList.add(inventoryModel);
        }

        List<Map<String, Object>> fuPaymentModelList = jsonUtility.getListValueFromJSON(result, "fuPaymentList");
        List<FuPaymentModel> paymentModelList = new ArrayList<>();
        for (Map<String, Object> payment : fuPaymentModelList){
            FuPaymentModel fuPaymentModel = new FuPaymentModel();
            Integer type = jsonUtility.getIntegerValueFromJSON(payment, "type");
            Integer paymentid = jsonUtility.getIntegerValueFromJSON(payment, "id");
            String paymentString = jsonUtility.getStringValueFromJSON(payment, "name");
            Integer flag = jsonUtility.getIntegerValueFromJSON(payment, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(payment, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            fuPaymentModel.setType(type);
            fuPaymentModel.setId(paymentid);
            fuPaymentModel.setName(paymentString);
            fuPaymentModel.setFlag(flag);
            fuStaffModel.setOptime(optime);
            paymentModelList.add(fuPaymentModel);
        }

        List<Map<String, Object>> fuAuthorityList = jsonUtility.getListValueFromJSON(result, "fuAuthorityList");
        List<FuAuthorityModel> authorityModelList = new ArrayList<>();
        for (Map<String, Object> map : fuAuthorityList){
            FuAuthorityModel fuAuthorityModel = new FuAuthorityModel();
            Integer authorityid = jsonUtility.getIntegerValueFromJSON(map, "id");
            String authorityString = jsonUtility.getStringValueFromJSON(map, "name");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(map, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            fuAuthorityModel.setId(authorityid);
            fuAuthorityModel.setName(authorityString);
            fuAuthorityModel.setAccountid(accountid);
            fuAuthorityModel.setFlag(flag);
            fuAuthorityModel.setOptime(optime);
            authorityModelList.add(fuAuthorityModel);
        }

        List<Map<String, Object>> fuRoleList = jsonUtility.getListValueFromJSON(result, "fuRoleList");
        List<FuRoleModel> roleModelList = new ArrayList<>();
        for (Map<String, Object> map : fuRoleList){
            FuRoleModel fuRoleModel = new FuRoleModel();
            String roleString = jsonUtility.getStringValueFromJSON(map, "name");

            fuRoleModel.setName(roleString);
            roleModelList.add(fuRoleModel);
        }

        Integer id = jsonUtility.getIntegerValueFromJSON(result, "id");
        Integer accountid = jsonUtility.getIntegerValueFromJSON(result, "accountid");
        String accountString = jsonUtility.getStringValueFromJSON(result, "accountString");

        fuStaffModel.setCode(code);
        fuStaffModel.setPassword(password);
        fuStaffModel.setName(name);
        fuStaffModel.setSearchkey(searchkey);
        fuStaffModel.setQrcodewx(qrcodewx);
        fuStaffModel.setDepid(depid);
        fuStaffModel.setStatus(status);
        fuStaffModel.setFuInventoryList(inventoryModelList);
        fuStaffModel.setFuPaymentList(paymentModelList);
        fuStaffModel.setFuAuthorityList(authorityModelList);
        fuStaffModel.setFuRoleList(roleModelList);

        fuStaffModel.setId(id);
        fuStaffModel.setAccountid(accountid);
        fuStaffModel.setAccountString(accountString);

        return fuStaffModel;
    }

    public String getFileServerip(String data)
    {
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");

        return jsonUtility.getStringValueFromJSON(result,"fileserverip");
    }

    public List getStyleListData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuStyleModel> styleModelList = new ArrayList<FuStyleModel>();
        for (Map<String, Object> map : result){
            FuStyleModel fuStyleModel = new FuStyleModel();
            String code = jsonUtility.getStringValueFromJSON(map, "code");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            List<Map<String, Object>> fuStylePriceTypeModelList = jsonUtility.getListValueFromJSON(map, "fuStylePriceTypeModelList");
            List<FuStylePriceTypeModel> stylePriceTypeModelList = new ArrayList<>();
            for (Map<String, Object> one : fuStylePriceTypeModelList){
                FuStylePriceTypeModel fuStylePriceTypeModel = new FuStylePriceTypeModel();
                Integer styleid = jsonUtility.getIntegerValueFromJSON(one, "styleid");
                Integer pricetypeid = jsonUtility.getIntegerValueFromJSON(one, "pricetypeid");
                BigDecimal price = jsonUtility.getBigDecimalValueFromJSON(one, "price");
                BigDecimal ratio = jsonUtility.getBigDecimalValueFromJSON(one, "ratio");
                String pricetypeString = jsonUtility.getStringValueFromJSON(one, "pricetypeString");
                Integer statusprice = jsonUtility.getIntegerValueFromJSON(one, "status");
                Integer id = jsonUtility.getIntegerValueFromJSON(one, "id");
                Integer accountid = jsonUtility.getIntegerValueFromJSON(one, "accountid");
                Integer flag = jsonUtility.getIntegerValueFromJSON(one, "flag");
                Date optime = null;
                try {
                    optime = jsonUtility.getDateValueFromJSON(one, "optime");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Integer opid = jsonUtility.getIntegerValueFromJSON(one, "opid");

                fuStylePriceTypeModel.setStyleid(styleid);
                fuStylePriceTypeModel.setPricetypeid(pricetypeid);
                fuStylePriceTypeModel.setPrice(price);
                fuStylePriceTypeModel.setRatio(ratio);
                fuStylePriceTypeModel.setPricetypeString(pricetypeString);
                fuStylePriceTypeModel.setStatus(statusprice);
                fuStylePriceTypeModel.setId(id);
                fuStylePriceTypeModel.setAccountid(accountid);
                fuStylePriceTypeModel.setFlag(flag);
                fuStylePriceTypeModel.setOptime(optime);
                fuStylePriceTypeModel.setOpid(opid);

                stylePriceTypeModelList.add(fuStylePriceTypeModel);
            }

            List<Map<String, Object>> fuStyleImageModelList = jsonUtility.getListValueFromJSON(map, "fuStyleImageModelList");
            List<FuStyleImageModel> styleImageModelList = new ArrayList<>();
            for (Map<String, Object> one : fuStyleImageModelList){
                FuStyleImageModel fuStyleImageModel = new FuStyleImageModel();
                fuStyleImageModel.setStyleId(jsonUtility.getIntegerValueFromJSON(one, "styleid"));
                fuStyleImageModel.setPath(jsonUtility.getStringValueFromJSON(one, "path"));
                fuStyleImageModel.setId(jsonUtility.getIntegerValueFromJSON(one, "id"));
                fuStyleImageModel.setFlag(jsonUtility.getIntegerValueFromJSON(one, "flag"));
                fuStyleImageModel.setOptime(jsonUtility.getStringValueFromJSON(one, "optime"));
                fuStyleImageModel.setOpId(jsonUtility.getIntegerValueFromJSON(one, "opid"));
                fuStyleImageModel.setAccountId(jsonUtility.getIntegerValueFromJSON(one, "accountid"));

                styleImageModelList.add(fuStyleImageModel);
            }


            Date marketdate = null;
            try {
                marketdate = jsonUtility.getDateValueFromJSON(map, "marketdate");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            fuStyleModel.setCode(code);
            fuStyleModel.setName(name);
            fuStyleModel.setFuStylePriceTypeModelList(stylePriceTypeModelList);
            fuStyleModel.setFuStyleImageModelList(styleImageModelList);
            fuStyleModel.setMarketdate(marketdate);
            fuStyleModel.setAmount(amount);
            fuStyleModel.setAccountid(accountid);
            fuStyleModel.setId(id);
            fuStyleModel.setStatus(status);

            styleModelList.add(fuStyleModel);
        }
        return styleModelList;
    }
    public List getSalesBillListData(String data) {
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuSalesBillModel> salesBillModelList = new ArrayList<>();
        for (Map<String, Object> map: result){
            FuSalesBillModel fuSalesBillModel = new FuSalesBillModel();
            Integer code = jsonUtility.getIntegerValueFromJSON(map, "code");
            Integer clientid = jsonUtility.getIntegerValueFromJSON(map, "clientid");
            Integer invid = jsonUtility.getIntegerValueFromJSON(map, "invid");
            Date occurrencetime = null;
            try {
                occurrencetime = jsonUtility.getDateValueFromJSON(map, "occurrencetime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer printflag = jsonUtility.getIntegerValueFromJSON(map, "printflag");
            String clientString = jsonUtility.getStringValueFromJSON(map, "clientString");
            String invString = jsonUtility.getStringValueFromJSON(map, "invString");
            BigDecimal total = jsonUtility.getBigDecimalValueFromJSON(map, "total");
            Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
            BigDecimal preSalesTotal = jsonUtility.getBigDecimalValueFromJSON(map, "preSalesTotal");
            String opString = jsonUtility.getStringValueFromJSON(map, "opString");
            BigDecimal arrears = jsonUtility.getBigDecimalValueFromJSON(map, "arrears");
            BigDecimal realMoney = jsonUtility.getBigDecimalValueFromJSON(map, "realMoney");
            BigDecimal salesbillArrears = jsonUtility.getBigDecimalValueFromJSON(map, "salesbillArrears");
            String payStatus = jsonUtility.getStringValueFromJSON(map, "payStatus");
            BigDecimal verificationmoney = jsonUtility.getBigDecimalValueFromJSON(map, "verificationmoney");
            BigDecimal cash = jsonUtility.getBigDecimalValueFromJSON(map, "cash");
            BigDecimal swingCard = jsonUtility.getBigDecimalValueFromJSON(map, "swingCard");
            BigDecimal remit = jsonUtility.getBigDecimalValueFromJSON(map, "remit");
            BigDecimal collectionmoney = jsonUtility.getBigDecimalValueFromJSON(map, "collectionmoney");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(map, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
            String remark = jsonUtility.getStringValueFromJSON(map, "remark");

            fuSalesBillModel.setCode(code);
            fuSalesBillModel.setClientid(clientid);
            fuSalesBillModel.setInvid(invid);
            fuSalesBillModel.setOccurrencetime(occurrencetime);
            fuSalesBillModel.setStatus(status);
            fuSalesBillModel.setPrintflag(printflag);
            fuSalesBillModel.setClientString(clientString);
            fuSalesBillModel.setInvString(invString);
            fuSalesBillModel.setTotal(total);
            fuSalesBillModel.setAmount(amount);
            fuSalesBillModel.setPreSalesTotal(preSalesTotal);
            fuSalesBillModel.setOpString(opString);
            fuSalesBillModel.setArrears(arrears);
            fuSalesBillModel.setRealMoney(realMoney);
            fuSalesBillModel.setSalesbillArrears(salesbillArrears);
            fuSalesBillModel.setPayStatus(payStatus);
            fuSalesBillModel.setVerificationmoney(verificationmoney);
            fuSalesBillModel.setCash(cash);
            fuSalesBillModel.setSwingCard(swingCard);
            fuSalesBillModel.setRemit(remit);
            fuSalesBillModel.setCollectionmoney(collectionmoney);
            fuSalesBillModel.setId(id);
            fuSalesBillModel.setAccountid(accountid);
            fuSalesBillModel.setFlag(flag);
            fuSalesBillModel.setOptime(optime);
            fuSalesBillModel.setOpid(opid);
            fuSalesBillModel.setRemark(remark);
            salesBillModelList.add(fuSalesBillModel);
        }
        return salesBillModelList;
    }

    public List getDataListForAC(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuBaseModel> baseModelList = new ArrayList<>();
        for (Map<String , Object> map : result){
            FuBaseModel fuBaseModel = new FuBaseModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            String difference = jsonUtility.getStringValueFromJSON(map, "difference");

            fuBaseModel.setId(id);
            fuBaseModel.setName(name);
            fuBaseModel.setDifference(difference);
            baseModelList.add(fuBaseModel);
        }
        return baseModelList;
    }

    public List getDataListForStyleAC(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuDynamicModel> dynamicModelList = new ArrayList<>();
        for (Map<String , Object> map : result){
            FuDynamicModel fuBaseModel = new FuDynamicModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            String difference = jsonUtility.getStringValueFromJSON(map, "difference");
            Integer imageId = jsonUtility.getIntegerValueFromJSON(map, "imageId");

            fuBaseModel.setId(id);
            fuBaseModel.setName(name);
            fuBaseModel.setDifference(difference);
            fuBaseModel.setImageId(imageId);
            dynamicModelList.add(fuBaseModel);
        }
        return dynamicModelList;
    }


    public List getList(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuBaseModel> baseModelList = new ArrayList<>();
        for (Map<String, Object> map : result){
            FuBaseModel fuBaseModel = new FuBaseModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            fuBaseModel.setId(id);
            fuBaseModel.setName(name);

            baseModelList.add(fuBaseModel);
        }
        return baseModelList;
    }

    public FuStyleModel getStyleData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");
        FuStyleModel fuStyleModel = new FuStyleModel();

        String code = jsonUtility.getStringValueFromJSON(result, "code");
        String codesearchkey = jsonUtility.getStringValueFromJSON(result, "codesearchkey");
        String searchkey = jsonUtility.getStringValueFromJSON(result, "searchkey");
        String season = jsonUtility.getStringValueFromJSON(result, "season");
        Integer brandid = jsonUtility.getIntegerValueFromJSON(result, "brandid");
        Integer clientid = jsonUtility.getIntegerValueFromJSON(result, "clientid");
        Date marketdate= null;
        try {
            marketdate = jsonUtility.getDateValueFromJSON(result, "marketdate");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BigDecimal price = jsonUtility.getBigDecimalValueFromJSON(result, "price");
        Integer status = jsonUtility.getIntegerValueFromJSON(result, "status");
        String standardbarcode = jsonUtility.getStringValueFromJSON(result, "standardbarcode");
        String suppliercode = jsonUtility.getStringValueFromJSON(result, "suppliercode");
        String brandString = jsonUtility.getStringValueFromJSON(result, "brandString");
        String clientString = jsonUtility.getStringValueFromJSON(result, "clientString");
        String colorString = jsonUtility.getStringValueFromJSON(result, "colorString");
        String sizeString = jsonUtility.getStringValueFromJSON(result, "sizeString");
        Integer colorid = jsonUtility.getIntegerValueFromJSON(result, "colorid");
        Integer sizeid = jsonUtility.getIntegerValueFromJSON(result, "sizeid");
        Integer sumAmount = jsonUtility.getIntegerValueFromJSON(result, "sumAmount");
        Integer salesCount = jsonUtility.getIntegerValueFromJSON(result,"salesCount");
        Integer salesAmount = jsonUtility.getIntegerValueFromJSON(result,"salesAmount");
        Integer cancelAmount = jsonUtility.getIntegerValueFromJSON(result,"cancelAmount");
        BigDecimal lastprice = jsonUtility.getBigDecimalValueFromJSON(result,"lastprice");

        List<Map<String, Object>> fuStyleImageModelList = jsonUtility.getListValueFromJSON(result, "fuStyleImageModelList");
        List<FuStyleImageModel> styleImageModelList = new ArrayList<>();
        for(Map<String, Object> one : fuStyleImageModelList)
        {
            FuStyleImageModel fuStyleImageModel = new FuStyleImageModel();
            fuStyleImageModel.setStyleId(jsonUtility.getIntegerValueFromJSON(one, "styleid"));
            fuStyleImageModel.setPath(jsonUtility.getStringValueFromJSON(one, "path"));
            fuStyleImageModel.setId(jsonUtility.getIntegerValueFromJSON(one, "id"));
            fuStyleImageModel.setFlag(jsonUtility.getIntegerValueFromJSON(one, "flag"));
            fuStyleImageModel.setOptime(jsonUtility.getStringValueFromJSON(one, "optime"));
            fuStyleImageModel.setOpId(jsonUtility.getIntegerValueFromJSON(one, "opid"));
            fuStyleImageModel.setAccountId(jsonUtility.getIntegerValueFromJSON(one, "accountid"));
            styleImageModelList.add(fuStyleImageModel);
        }

        List<Map<String, Object>> fuStyleClassModelList = jsonUtility.getListValueFromJSON(result, "fuStyleClassModelList");
        List<FuStyleClassModel> styleClassModelList = new ArrayList<>();
        for (Map<String, Object> map : fuStyleClassModelList){
            FuStyleClassModel fuStyleClassModel = new FuStyleClassModel();
            Integer styleid = jsonUtility.getIntegerValueFromJSON(map, "styleid");
            Integer classid = jsonUtility.getIntegerValueFromJSON(map, "classid");
            String classString = jsonUtility.getStringValueFromJSON(map, "classString");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(map, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");

            fuStyleClassModel.setStyleid(styleid);
            fuStyleClassModel.setClassid(classid);
            fuStyleClassModel.setClassString(classString);
            fuStyleClassModel.setId(id);
            fuStyleClassModel.setAccountid(accountid);
            fuStyleClassModel.setFlag(flag);
            fuStyleClassModel.setOptime(optime);
            fuStyleClassModel.setOpid(opid);
            styleClassModelList.add(fuStyleClassModel);
        }

        List<Map<String, Object>> fuStylePriceTypeModelList = jsonUtility.getListValueFromJSON(result, "fuStylePriceTypeModelList");
        List<FuStylePriceTypeModel> stylePriceTypeModelList = new ArrayList<>();
        for (Map<String, Object> map : fuStylePriceTypeModelList){
            FuStylePriceTypeModel fuStylePriceTypeModel = new FuStylePriceTypeModel();
            Integer styleid =jsonUtility.getIntegerValueFromJSON(map, "styleid");
            Integer pricetypeid = jsonUtility.getIntegerValueFromJSON(map, "pricetypeid");
            BigDecimal price2 = jsonUtility.getBigDecimalValueFromJSON(map, "price");
            BigDecimal ratio = jsonUtility.getBigDecimalValueFromJSON(map, "ratio");
            String pricetypeString = jsonUtility.getStringValueFromJSON(map, "pricetypeString");
            Integer status2 = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(map, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
            fuStylePriceTypeModel.setStyleid(styleid);
            fuStylePriceTypeModel.setPricetypeid(pricetypeid);
            fuStylePriceTypeModel.setPrice(price2);
            fuStylePriceTypeModel.setRatio(ratio);
            fuStylePriceTypeModel.setPricetypeString(pricetypeString);
            fuStylePriceTypeModel.setStatus(status2);
            fuStylePriceTypeModel.setId(id);
            fuStylePriceTypeModel.setAccountid(accountid);
            fuStylePriceTypeModel.setFlag(flag);
            fuStylePriceTypeModel.setOptime(optime);
            fuStylePriceTypeModel.setOpid(opid);
            stylePriceTypeModelList.add(fuStylePriceTypeModel);
        }

        List<Map<String, Object>> resultStyleColorModelList = jsonUtility.getListValueFromJSON(result, "resultStyleColorModelList");
        List<FuStyleColorModel> styleColorModelList = new ArrayList<>();
        for (Map<String, Object> map: resultStyleColorModelList){
            FuStyleColorModel fuStyleColorModel = new FuStyleColorModel();
            Integer colorid2 = jsonUtility.getIntegerValueFromJSON(map, "colorid");
            String colorString2 = jsonUtility.getStringValueFromJSON(map, "colorString");
            Integer groupid = jsonUtility.getIntegerValueFromJSON(map, "groupid");
            Integer rank = jsonUtility.getIntegerValueFromJSON(map, "rank");
            String groupString = jsonUtility.getStringValueFromJSON(map, "groupString");
            fuStyleColorModel.setColorid(colorid2);
            fuStyleColorModel.setColorString(colorString2);
            fuStyleColorModel.setGroupid(groupid);
            fuStyleColorModel.setRank(rank);
            fuStyleColorModel.setGroupString(groupString);
            styleColorModelList.add(fuStyleColorModel);
        }

        List<Map<String, Object>> resultStyleSizeModelList = jsonUtility.getListValueFromJSON(result, "resultStyleSizeModelList");
        List<FuStyleSizeModel> styleSizeModelList = new ArrayList<>();
        for (Map<String, Object> map: resultStyleSizeModelList){
            FuStyleSizeModel fuStyleSizeModel = new FuStyleSizeModel();
            Integer sizeid2 = jsonUtility.getIntegerValueFromJSON(map, "sizeid");
            String sizeString2 = jsonUtility.getStringValueFromJSON(map, "sizeString");
            Integer groupid = jsonUtility.getIntegerValueFromJSON(map, "groupid");
            Integer rank = jsonUtility.getIntegerValueFromJSON(map, "rank");
            Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
            String groupString = jsonUtility.getStringValueFromJSON(map, "groupString");
            fuStyleSizeModel.setSizeid(sizeid2);
            fuStyleSizeModel.setSizeString(sizeString2);
            fuStyleSizeModel.setGroupid(groupid);
            fuStyleSizeModel.setRank(rank);
            fuStyleSizeModel.setAmount(amount);
            fuStyleSizeModel.setGroupString(groupString);
            styleSizeModelList.add(fuStyleSizeModel);
        }

        List<Map<String, Object>> fuStockList = jsonUtility.getListValueFromJSON(result, "fuStockList");
        List<FuStockModel> stockModelList = new ArrayList<>();
        if (fuStockList != null){
            for (Map<String, Object> map : fuStockList){
                FuStockModel stockModel = new FuStockModel();
                Integer stylecolorsizeid = jsonUtility.getIntegerValueFromJSON(map, "stylecolorsizeid");
                Integer invid = jsonUtility.getIntegerValueFromJSON(map, "invid");
                Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
                Integer amountonroad = jsonUtility.getIntegerValueFromJSON(map, "amountonroad");
                Integer styleid = jsonUtility.getIntegerValueFromJSON(map, "styleid");
                Integer colorid2 = jsonUtility.getIntegerValueFromJSON(map, "colorid");
                Integer sizeid2 = jsonUtility.getIntegerValueFromJSON(map,"sizeid");
                String styleString = jsonUtility.getStringValueFromJSON(map, "styleString");
                String colorString2 = jsonUtility.getStringValueFromJSON(map, "colorString");
                String sizeString2 = jsonUtility.getStringValueFromJSON(map, "sizeString");
                String invString = jsonUtility.getStringValueFromJSON(map, "invString");
                BigDecimal price2 = jsonUtility.getBigDecimalValueFromJSON(map, "price");
                BigDecimal sumTotal = jsonUtility.getBigDecimalValueFromJSON(map, "sumTotal");
                String styleCode = jsonUtility.getStringValueFromJSON(map, "styleCode");
                BigDecimal averagePurchasePrice = jsonUtility.getBigDecimalValueFromJSON(map, "averagePurchasePrice");
                Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
                Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
                Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
                Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
                stockModel.setStylecolorsizeid(stylecolorsizeid);
                stockModel.setInvid(invid);
                stockModel.setAmount(amount);
                stockModel.setAmountonroad(amountonroad);
                stockModel.setStyleid(styleid);
                stockModel.setColorid(colorid2);
                stockModel.setSizeid(sizeid2);
                stockModel.setStyleString(styleString);
                stockModel.setStyleCode(styleCode);
                stockModel.setColorString(colorString2);
                stockModel.setSizeString(sizeString2);
                stockModel.setInvString(invString);
                stockModel.setPrice(price2);
                stockModel.setSumTotal(sumTotal);
                stockModel.setAveragePurchasePrice(averagePurchasePrice);
                stockModel.setId(id);
                stockModel.setAccountid(accountid);
                stockModel.setFlag(flag);
                stockModel.setOpid(opid);
                stockModelList.add(stockModel);
            }
        }

        List<Map<String, Object>> fuStyleBarcodeInfoList = jsonUtility.getListValueFromJSON(result, "fuStyleBarcodeInfoList");
        List<FuStyleBarcodeInfoModel> styleBarcodeInfoModelList = new ArrayList<>();
        if (fuStyleBarcodeInfoList != null){
            for (Map<String, Object> map : fuStyleBarcodeInfoList){
                FuStyleBarcodeInfoModel fuStyleBarcodeInfoModel = new FuStyleBarcodeInfoModel();
                Integer styleid = jsonUtility.getIntegerValueFromJSON(map, "styleid");
                String title = jsonUtility.getStringValueFromJSON(map, "title");
                String content = jsonUtility.getStringValueFromJSON(map, "content");
                String titlesearchkey = jsonUtility.getStringValueFromJSON(map, "titlesearchkey");
                String contentsearchkey = jsonUtility.getStringValueFromJSON(map, "contentsearchkey");
                Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
                Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
                Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
                Date optime = null;
                try {
                    optime = jsonUtility.getDateValueFromJSON(map, "optime");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");

                fuStyleBarcodeInfoModel.setStyleid(styleid);
                fuStyleBarcodeInfoModel.setTitle(title);
                fuStyleBarcodeInfoModel.setContent(content);
                fuStyleBarcodeInfoModel.setTitlesearchkey(titlesearchkey);
                fuStyleBarcodeInfoModel.setContentsearchkey(contentsearchkey);
                fuStyleBarcodeInfoModel.setId(id);
                fuStyleBarcodeInfoModel.setAccountid(accountid);
                fuStyleBarcodeInfoModel.setFlag(flag);
                fuStyleBarcodeInfoModel.setOptime(optime);
                fuStyleBarcodeInfoModel.setOpid(opid);
                styleBarcodeInfoModelList.add(fuStyleBarcodeInfoModel);
            }
        }
        Integer id = jsonUtility.getIntegerValueFromJSON(result, "id");
        String name = jsonUtility.getStringValueFromJSON(result, "name");
        Integer accountid = jsonUtility.getIntegerValueFromJSON(result, "accountid");
        Integer flag = jsonUtility.getIntegerValueFromJSON(result, "flag");
        Date optime = null;
        try {
            optime = jsonUtility.getDateValueFromJSON(result, "optime");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer opid = jsonUtility.getIntegerValueFromJSON(result, "opid");
        String remark = jsonUtility.getStringValueFromJSON(result, "remark");

        fuStyleModel.setCode(code);
        fuStyleModel.setCodesearchkey(codesearchkey);
        fuStyleModel.setSearchkey(searchkey);
        fuStyleModel.setSeason(season);
        fuStyleModel.setBrandid(brandid);
        fuStyleModel.setClientid(clientid);
        fuStyleModel.setMarketdate(marketdate);
        fuStyleModel.setPrice(price);
        fuStyleModel.setStatus(status);
        fuStyleModel.setSumAmount(sumAmount);
        fuStyleModel.setCancelAmount(cancelAmount);
        fuStyleModel.setLastprice(lastprice);
        fuStyleModel.setSalesAmount(salesAmount);
        fuStyleModel.setSalesCount(salesCount);
        fuStyleModel.setStandardbarcode(standardbarcode);
        fuStyleModel.setSuppliercode(suppliercode);
        fuStyleModel.setBrandString(brandString);
        fuStyleModel.setClientString(clientString);
        fuStyleModel.setColorString(colorString);
        fuStyleModel.setSizeString(sizeString);
        fuStyleModel.setColorid(colorid);
        fuStyleModel.setSizeid(sizeid);
        fuStyleModel.setFuStyleClassModelList(styleClassModelList);
        fuStyleModel.setFuStyleImageModelList(styleImageModelList);
        fuStyleModel.setFuStylePriceTypeModelList(stylePriceTypeModelList);
        fuStyleModel.setResultStyleColorModelList(styleColorModelList);
        fuStyleModel.setResultStyleSizeModelList(styleSizeModelList);
        fuStyleModel.setFuStockList(stockModelList);
        fuStyleModel.setFuStyleBarcodeInfoList(styleBarcodeInfoModelList);
        fuStyleModel.setId(id);
        fuStyleModel.setName(name);
        fuStyleModel.setAccountid(accountid);
        fuStyleModel.setFlag(flag);
        fuStyleModel.setOptime(optime);
        fuStyleModel.setOpid(opid);
        fuStyleModel.setRemark(remark);
        return fuStyleModel;
    }

    public FuClientModel getClientData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");
        FuClientModel fuClientModel = new FuClientModel();
        String phone = jsonUtility.getStringValueFromJSON(result, "phone");
        String address = jsonUtility.getStringValueFromJSON(result, "address");
        String searchkey = jsonUtility.getStringValueFromJSON(result, "searchkey");
        BigDecimal discount = jsonUtility.getBigDecimalValueFromJSON(result, "discount");
        BigDecimal credit = jsonUtility.getBigDecimalValueFromJSON(result, "credit");
        BigDecimal alarmcredit = jsonUtility.getBigDecimalValueFromJSON(result, "alarmcredit");
        BigDecimal monthcredit = jsonUtility.getBigDecimalValueFromJSON(result, "monthcredit");
        BigDecimal monthalarmcredit = jsonUtility.getBigDecimalValueFromJSON(result, "monthalarmcredit");
        BigDecimal yearcredit = jsonUtility.getBigDecimalValueFromJSON(result, "yearcredit");
        BigDecimal yearalarmcredit = jsonUtility.getBigDecimalValueFromJSON(result, "yearalarmcredit");
        Integer score = jsonUtility.getIntegerValueFromJSON(result, "score");
        Integer pricetypeid = jsonUtility.getIntegerValueFromJSON(result, "pricetypeid");
        Integer staffid = jsonUtility.getIntegerValueFromJSON(result, "staffid");
        Integer type = jsonUtility.getIntegerValueFromJSON(result, "type");
        Integer invid = jsonUtility.getIntegerValueFromJSON(result, "invid");
        String identity = jsonUtility.getStringValueFromJSON(result, "identity");
        Integer status = jsonUtility.getIntegerValueFromJSON(result, "status");
        Integer firmware = jsonUtility.getIntegerValueFromJSON(result, "firmware");
        String area = jsonUtility.getStringValueFromJSON(result, "area");
        Integer kindid = jsonUtility.getIntegerValueFromJSON(result, "kindid");
        String staffName = jsonUtility.getStringValueFromJSON(result, "staffName");
        String invName = jsonUtility.getStringValueFromJSON(result, "invName");
        String pricetypeString = jsonUtility.getStringValueFromJSON(result, "pricetypeString");
        String kindString = jsonUtility.getStringValueFromJSON(result, "kindString");
        BigDecimal arrears = jsonUtility.getBigDecimalValueFromJSON(result, "arrears");
        BigDecimal prePayMoney = jsonUtility.getBigDecimalValueFromJSON(result, "prePayMoney");
        Integer id = jsonUtility.getIntegerValueFromJSON(result, "id");
        String name = jsonUtility.getStringValueFromJSON(result, "name");
        Integer accountid = jsonUtility.getIntegerValueFromJSON(result, "accountid");
        Integer flag = jsonUtility.getIntegerValueFromJSON(result, "flag");
        Date optime = null;
        try {
            optime = jsonUtility.getDateValueFromJSON(result, "optime");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer opid = jsonUtility.getIntegerValueFromJSON(result, "opid");
        String remark = jsonUtility.getStringValueFromJSON(result, "remark");

        fuClientModel.setPhone(phone);
        fuClientModel.setAddress(address);
        fuClientModel.setSearchkey(searchkey);
        fuClientModel.setDiscount(discount);
        fuClientModel.setCredit(credit);
        fuClientModel.setAlarmcredit(alarmcredit);
        fuClientModel.setMonthcredit(monthcredit);
        fuClientModel.setMonthalarmcredit(monthalarmcredit);
        fuClientModel.setYearcredit(yearcredit);
        fuClientModel.setYearalarmcredit(yearalarmcredit);
        fuClientModel.setScore(score);
        fuClientModel.setPricetypeid(pricetypeid);
        fuClientModel.setStaffid(staffid);
        fuClientModel.setType(type);
        fuClientModel.setInvid(invid);
        fuClientModel.setIdentity(identity);
        fuClientModel.setStatus(status);
        fuClientModel.setFirmware(firmware);
        fuClientModel.setArea(area);
        fuClientModel.setKindid(kindid);
        fuClientModel.setStaffName(staffName);
        fuClientModel.setInvName(invName);
        fuClientModel.setPricetypeString(pricetypeString);
        fuClientModel.setKindString(kindString);
        fuClientModel.setArrears(arrears);
        fuClientModel.setPrePayMoney(prePayMoney);
        fuClientModel.setId(id);
        fuClientModel.setName(name);
        fuClientModel.setAccountid(accountid);
        fuClientModel.setFlag(flag);
        fuClientModel.setOptime(optime);
        fuClientModel.setOpid(opid);
        fuClientModel.setRemark(remark);
        return fuClientModel;
    }

    public List getExtraMoneyFixListData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuExtraMoneyFixModel> extraMoneyFixModelList = new ArrayList<FuExtraMoneyFixModel>();
        for (Map<String, Object> map : result){
            FuExtraMoneyFixModel fuExtraMoneyFixModel = new FuExtraMoneyFixModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer balancetype = jsonUtility.getIntegerValueFromJSON(map, "balancetype");
            Integer calculatetype = jsonUtility.getIntegerValueFromJSON(map, "calculatetype");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(map, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
            String remark = jsonUtility.getStringValueFromJSON(map, "remark");
            fuExtraMoneyFixModel.setId(id);
            fuExtraMoneyFixModel.setName(name);
            fuExtraMoneyFixModel.setBalancetype(balancetype);
            fuExtraMoneyFixModel.setCalculatetype(calculatetype);
            fuExtraMoneyFixModel.setStatus(status);
            fuExtraMoneyFixModel.setAccountid(accountid);
            fuExtraMoneyFixModel.setFlag(flag);
            fuExtraMoneyFixModel.setOptime(optime);
            fuExtraMoneyFixModel.setOpid(opid);
            fuExtraMoneyFixModel.setRemark(remark);
            extraMoneyFixModelList.add(fuExtraMoneyFixModel);
        }
        return extraMoneyFixModelList;
    }

    public List getMonetAccountListData(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuMoneyAccountModel> moneyAccountModelList = new ArrayList<FuMoneyAccountModel>();
        for (Map<String, Object> map : result){
            FuMoneyAccountModel fuMoneyAccountModel = new FuMoneyAccountModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String code = jsonUtility.getStringValueFromJSON(map, "code");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer firmware = jsonUtility.getIntegerValueFromJSON(map, "firmware");
            Integer isdefault = jsonUtility.getIntegerValueFromJSON(map, "isdefault");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(map, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
            String remark = jsonUtility.getStringValueFromJSON(map, "remark");
            fuMoneyAccountModel.setId(id);
            fuMoneyAccountModel.setCode(code);
            fuMoneyAccountModel.setName(name);
            fuMoneyAccountModel.setStatus(status);
            fuMoneyAccountModel.setFirmware(firmware);
            fuMoneyAccountModel.setIsdefault(isdefault);
            fuMoneyAccountModel.setAccountid(accountid);
            fuMoneyAccountModel.setFlag(flag);
            fuMoneyAccountModel.setOptime(optime);
            fuMoneyAccountModel.setOpid(opid);
            fuMoneyAccountModel.setRemark(remark);
            moneyAccountModelList.add(fuMoneyAccountModel);
        }
        return moneyAccountModelList;
    }


    public FuSalesBillModel getSalesBillData(String data) {
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");
        FuSalesBillModel fuSalesBillModel = new FuSalesBillModel();

        Integer id = jsonUtility.getIntegerValueFromJSON(result, "id");
        Integer code = jsonUtility.getIntegerValueFromJSON(result, "code");
        Integer clientid = jsonUtility.getIntegerValueFromJSON(result, "clientid");
        Integer invid = jsonUtility.getIntegerValueFromJSON(result, "invid");
        Integer staffid = jsonUtility.getIntegerValueFromJSON(result, "staffid");
        Date occurrencetime = null;
        try {
            occurrencetime = jsonUtility.getDateValueFromJSON(result, "occurrencetime");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer status = jsonUtility.getIntegerValueFromJSON(result, "status");
        Integer printflag = jsonUtility.getIntegerValueFromJSON(result, "printflag");
        String waybillcode = jsonUtility.getStringValueFromJSON(result, "waybillcode");
        BigDecimal money = jsonUtility.getBigDecimalValueFromJSON(result, "money");
        BigDecimal verificationmoney = jsonUtility.getBigDecimalValueFromJSON(result, "verificationmoney");
        Integer verificationflag = jsonUtility.getIntegerValueFromJSON(result, "verificationflag");
        Integer verificationsalesbillid = jsonUtility.getIntegerValueFromJSON(result, "verificationsalesbillid");
        Integer type = jsonUtility.getIntegerValueFromJSON(result, "type");
        Integer accountid = jsonUtility.getIntegerValueFromJSON(result, "accountid");
        Integer flag = jsonUtility.getIntegerValueFromJSON(result, "flag");
        Date optime = null;
        try {
            optime = jsonUtility.getDateValueFromJSON(result, "optime");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer opid = jsonUtility.getIntegerValueFromJSON(result, "opid");
        String remark = jsonUtility.getStringValueFromJSON(result, "remark");
        String invString = jsonUtility.getStringValueFromJSON(result, "invString");
        String staffString = jsonUtility.getStringValueFromJSON(result, "staffString");
        String clientString = jsonUtility.getStringValueFromJSON(result, "clientString");
        BigDecimal collectionmoney = jsonUtility.getBigDecimalValueFromJSON(result, "collectionmoney");
        BigDecimal discount = jsonUtility.getBigDecimalValueFromJSON(result, "discount");
        Integer staffid2 = jsonUtility.getIntegerValueFromJSON(result, "staffid2");
        String staffString2 = jsonUtility.getStringValueFromJSON(result, "staffString2");
        Integer pricetypeid = jsonUtility.getIntegerValueFromJSON(result, "pricetypeid");
        String pricetypeString = jsonUtility.getStringValueFromJSON(result, "pricetypeString");
        String clientAddress = jsonUtility.getStringValueFromJSON(result, "clientAddress");
        String clientPhone = jsonUtility.getStringValueFromJSON(result, "clientPhone");
        BigDecimal arrears = jsonUtility.getBigDecimalValueFromJSON(result, "arrears");



        List<Map<String, Object>> fuSalesBillDetailList=jsonUtility.getListValueFromJSON(result, "fuSalesBillDetailList");
        List<FuSalesBillDetailModel> salesBillDetailList=new ArrayList<FuSalesBillDetailModel>();
        for(Map<String, Object> map: fuSalesBillDetailList){
            FuSalesBillDetailModel fuSalesBillDetailModel=new FuSalesBillDetailModel();
            Integer styleid = jsonUtility.getIntegerValueFromJSON(map, "styleid");
            Integer colorid = jsonUtility.getIntegerValueFromJSON(map, "colorid");
            Integer sizeid = jsonUtility.getIntegerValueFromJSON(map, "sizeid");
            String styleCode = jsonUtility.getStringValueFromJSON(map, "styleCode");
            String styleString = jsonUtility.getStringValueFromJSON(map, "styleString");
            String remarkForDetail=jsonUtility.getStringValueFromJSON(map, "remark");
            Integer stylecolorsizeid = jsonUtility.getIntegerValueFromJSON(map, "stylecolorsizeid");
            String colorString = jsonUtility.getStringValueFromJSON(map, "colorString");
            String sizeString = jsonUtility.getStringValueFromJSON(map, "sizeString");
            BigDecimal price = jsonUtility.getBigDecimalValueFromJSON(map, "price");
            BigDecimal discountForDetail = jsonUtility.getBigDecimalValueFromJSON(map, "discount");
            Integer amount2 = jsonUtility.getIntegerValueFromJSON(map, "amount");
            BigDecimal total = jsonUtility.getBigDecimalValueFromJSON(map, "total");
            Integer groupcountDetail = jsonUtility.getIntegerValueFromJSON(map, "groupcountDetail");
            String standardbarcode = jsonUtility.getStringValueFromJSON(map, "standardbarcode");
            Integer salesCount = jsonUtility.getIntegerValueFromJSON(map, "salesCount");
            Integer salesAmount = jsonUtility.getIntegerValueFromJSON(map, "salesAmount");
            Integer salesCountForCopy = jsonUtility.getIntegerValueFromJSON(map, "salesCountForCopy");
            BigDecimal originalprice = jsonUtility.getBigDecimalValueFromJSON(map, "originalprice");
            List<Map<String, Object>> fuStylePriceModelList = jsonUtility.getListValueFromJSON(map, "fuStylePriceTypeModelList");
            List<FuStylePriceTypeModel> stylePriceModelList = new ArrayList<>();
            if(fuStylePriceModelList!=null)//抹零的时候没有图片列表
            {
                for(Map<String, Object> one : fuStylePriceModelList)
                {
                    FuStylePriceTypeModel fuStylePriceModel = new FuStylePriceTypeModel();
                    fuStylePriceModel.setStyleid(jsonUtility.getIntegerValueFromJSON(one, "styleid"));
                    fuStylePriceModel.setPricetypeid(jsonUtility.getIntegerValueFromJSON(one, "pricetypeid"));
                    fuStylePriceModel.setPricetypeString(jsonUtility.getStringValueFromJSON(one, "pricetypeString"));
                    fuStylePriceModel.setPrice(jsonUtility.getBigDecimalValueFromJSON(one,"price"));
                    fuStylePriceModel.setStatus(jsonUtility.getIntegerValueFromJSON(one,"status"));
                    fuStylePriceModel.setRatio(jsonUtility.getBigDecimalValueFromJSON(one,"ratio"));
                    stylePriceModelList.add(fuStylePriceModel);
                }
            }

            List<Map<String, Object>> fuStyleImageModelList = jsonUtility.getListValueFromJSON(map, "fuStyleImageModelList");
            List<FuStyleImageModel> styleImageModelList = new ArrayList<>();
            if(fuStyleImageModelList!=null)//抹零的时候没有图片列表
            {
                for(Map<String, Object> one : fuStyleImageModelList)
                {
                    FuStyleImageModel fuStyleImageModel = new FuStyleImageModel();
                    fuStyleImageModel.setStyleId(jsonUtility.getIntegerValueFromJSON(one, "styleid"));
                    fuStyleImageModel.setPath(jsonUtility.getStringValueFromJSON(one, "path"));
                    fuStyleImageModel.setId(jsonUtility.getIntegerValueFromJSON(one, "id"));
                    fuStyleImageModel.setFlag(jsonUtility.getIntegerValueFromJSON(one, "flag"));
                    fuStyleImageModel.setOptime(jsonUtility.getStringValueFromJSON(one, "optime"));
                    fuStyleImageModel.setOpId(jsonUtility.getIntegerValueFromJSON(one, "opid"));
                    fuStyleImageModel.setAccountId(jsonUtility.getIntegerValueFromJSON(one, "accountid"));
                    styleImageModelList.add(fuStyleImageModel);
                }
            }
            fuSalesBillDetailModel.setStyleid(styleid);
            fuSalesBillDetailModel.setColorid(colorid);
            fuSalesBillDetailModel.setSizeid(sizeid);
            fuSalesBillDetailModel.setStyleCode(styleCode);
            fuSalesBillDetailModel.setStyleString(styleString);
            fuSalesBillDetailModel.setColorString(colorString);
            fuSalesBillDetailModel.setSizeString(sizeString);
            fuSalesBillDetailModel.setPrice(price);
            fuSalesBillDetailModel.setDiscount(discountForDetail);
            fuSalesBillDetailModel.setStylecolorsizeid(stylecolorsizeid);
            fuSalesBillDetailModel.setAmount(amount2);
            fuSalesBillDetailModel.setRemark(remarkForDetail);
            fuSalesBillDetailModel.setTotal(total);
            fuSalesBillDetailModel.setGroupcountDetail(groupcountDetail);
            fuSalesBillDetailModel.setStandardbarcode(standardbarcode);
            fuSalesBillDetailModel.setSalesCount(salesCount);
            fuSalesBillDetailModel.setSalesAmount(salesAmount);
            fuSalesBillDetailModel.setSalesAmountForCopy(salesCountForCopy);
            fuSalesBillDetailModel.setOriginalprice(originalprice);
            fuSalesBillDetailModel.setFuStyleImageModelList(styleImageModelList);
            fuSalesBillDetailModel.setFuStylePriceTypeModelList(stylePriceModelList);
            salesBillDetailList.add(fuSalesBillDetailModel);
        }

        List<Map<String, Object>> sizeListForGroup = jsonUtility.getListValueFromJSON(result, "sizeListForGroup");
        List<FuSizeModel> sizeModelList = new ArrayList<>();
        if (sizeListForGroup != null){
            for (Map<String, Object> map : sizeListForGroup){
                FuSizeModel fuSizeModel = new FuSizeModel();
                Integer sizeid = jsonUtility.getIntegerValueFromJSON(map, "id");
                String name = jsonUtility.getStringValueFromJSON(map, "name");
                fuSizeModel.setId(sizeid);
                fuSizeModel.setName(name);
                sizeModelList.add(fuSizeModel);
            }
        }

        List<Map<String, Object>> fuMoneyList = jsonUtility.getListValueFromJSON(result, "fuMoneyList");
        List<FuMoneyModel> moneyList = new ArrayList<>();
        for (Map<String, Object> map : fuMoneyList){
            FuMoneyModel fuMoneyModel = new FuMoneyModel();
            Integer moneyaccountid = jsonUtility.getIntegerValueFromJSON(map, "moneyaccountid");
            Integer paymentid = jsonUtility.getIntegerValueFromJSON(map, "paymentid");
            BigDecimal money2 = jsonUtility.getBigDecimalValueFromJSON(map, "money");
            Integer clientid2 = jsonUtility.getIntegerValueFromJSON(map, "clientid");
            Integer invid2 = jsonUtility.getIntegerValueFromJSON(map, "invid");
            String moneyaccountString = jsonUtility.getStringValueFromJSON(map, "moneyaccountString");
            String paymentString = jsonUtility.getStringValueFromJSON(map, "paymentString");
            fuMoneyModel.setMoneyaccountid(moneyaccountid);
            fuMoneyModel.setPaymentid(paymentid);
            fuMoneyModel.setMoney(money2);
            fuMoneyModel.setClientid(clientid2);
            fuMoneyModel.setInvid(invid2);
            fuMoneyModel.setMoneyaccountString(moneyaccountString);
            fuMoneyModel.setPaymentString(paymentString);
            moneyList.add(fuMoneyModel);
        }

        List<Map<String, Object>> verifiedSalesBillList = jsonUtility.getListValueFromJSON(result, "verifiedSalesBillList");
        List<FuSalesBillModel> verfidList = new ArrayList<>();
        if (verifiedSalesBillList != null){
            for (Map<String, Object> map : verifiedSalesBillList){
                FuSalesBillModel salesBillModel = new FuSalesBillModel();
                Integer codeForVerfid = jsonUtility.getIntegerValueFromJSON(map, "code");
                Integer invid2 = jsonUtility.getIntegerValueFromJSON(map, "invid");
                Date occurrencetime2 = null;
                try {
                    occurrencetime2 = jsonUtility.getDateValueFromJSON(map, "occurrencetime");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                BigDecimal money2 = jsonUtility.getBigDecimalValueFromJSON(map, "money");
                String invString2 = jsonUtility.getStringValueFromJSON(map, "invString");
                BigDecimal total = jsonUtility.getBigDecimalValueFromJSON(map, "total");
                Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
                BigDecimal preSalesTotal = jsonUtility.getBigDecimalValueFromJSON(map, "preSalesTotal");
                String modelString = jsonUtility.getStringValueFromJSON(map, "modelString");
                Integer id2 = jsonUtility.getIntegerValueFromJSON(map, "id");
                salesBillModel.setCode(codeForVerfid);
                salesBillModel.setInvid(invid2);
                salesBillModel.setOccurrencetime(occurrencetime2);
                salesBillModel.setMoney(money2);
                salesBillModel.setInvString(invString2);
                salesBillModel.setTotal(total);
                salesBillModel.setAmount(amount);
                salesBillModel.setPreSalesTotal(preSalesTotal);
                salesBillModel.setModelString(modelString);
                salesBillModel.setId(id2);
                verfidList.add(salesBillModel);
            }
        }

        List<Map<String, Object>> cutMoneyList=jsonUtility.getListValueFromJSON(result, "cutMoneyList");
        List<FuSalesBillDetailModel> fuCutMoneyList=new ArrayList<>();
        for(Map<String, Object> map: cutMoneyList){
            FuSalesBillDetailModel salesBillDetail = new FuSalesBillDetailModel();
            Integer salesbillid = jsonUtility.getIntegerValueFromJSON(map, "salesbillid");
            BigDecimal price = jsonUtility.getBigDecimalValueFromJSON(map, "price");
            Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
            BigDecimal discount2 = jsonUtility.getBigDecimalValueFromJSON(map, "discount");
            BigDecimal total = jsonUtility.getBigDecimalValueFromJSON(map, "total");
            Integer extramoneyfixid = jsonUtility.getIntegerValueFromJSON(map, "extramoneyfixid");
            Integer isscore = jsonUtility.getIntegerValueFromJSON(map, "isscore");
            BigDecimal originalprice = jsonUtility.getBigDecimalValueFromJSON(map, "originalprice");
            String styleString = jsonUtility.getStringValueFromJSON(map, "styleString");
            Integer id2 = jsonUtility.getIntegerValueFromJSON(map, "id");
            Integer opid2 = jsonUtility.getIntegerValueFromJSON(result, "opid");
            Integer accountid2 = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag2 = jsonUtility.getIntegerValueFromJSON(map, "flag");

            salesBillDetail.setSalesbillid(salesbillid);
            salesBillDetail.setPrice(price);
            salesBillDetail.setAmount(amount);
            salesBillDetail.setDiscount(discount2);
            salesBillDetail.setTotal(total);
            salesBillDetail.setExtramoneyfixid(extramoneyfixid);
            salesBillDetail.setIsscore(isscore);
            salesBillDetail.setOriginalprice(originalprice);
            salesBillDetail.setStyleString(styleString);
            salesBillDetail.setId(id2);
            salesBillDetail.setAccountid(accountid2);
            salesBillDetail.setFlag(flag2);
            salesBillDetail.setOpid(opid2);

            fuCutMoneyList.add(salesBillDetail);
        }

        fuSalesBillModel.setId(id);
        fuSalesBillModel.setCode(code);
        fuSalesBillModel.setClientid(clientid);
        fuSalesBillModel.setInvid(invid);
        fuSalesBillModel.setStaffid(staffid);
        fuSalesBillModel.setOccurrencetime(occurrencetime);
        fuSalesBillModel.setStatus(status);
        fuSalesBillModel.setPrintflag(printflag);
        fuSalesBillModel.setWaybillcode(waybillcode);
        fuSalesBillModel.setMoney(money);
        fuSalesBillModel.setVerificationmoney(verificationmoney);
        fuSalesBillModel.setVerificationflag(verificationflag);
        fuSalesBillModel.setVerificationsalesbillid(verificationsalesbillid);
        fuSalesBillModel.setType(type);
        fuSalesBillModel.setAccountid(accountid);
        fuSalesBillModel.setFlag(flag);
        fuSalesBillModel.setOptime(optime);
        fuSalesBillModel.setOpid(opid);
        fuSalesBillModel.setRemark(remark);
        fuSalesBillModel.setInvString(invString);
        fuSalesBillModel.setStaffString(staffString);
        fuSalesBillModel.setClientString(clientString);
        fuSalesBillModel.setCollectionmoney(collectionmoney);
        fuSalesBillModel.setCollectionmoney(collectionmoney);
        fuSalesBillModel.setDiscount(discount);
        fuSalesBillModel.setStaffid2(staffid2);
        fuSalesBillModel.setStaffString2(staffString2);
        fuSalesBillModel.setPricetypeid(pricetypeid);
        fuSalesBillModel.setPricetypeString(pricetypeString);
        fuSalesBillModel.setClientAddress(clientAddress);
        fuSalesBillModel.setClientPhone(clientPhone);
        fuSalesBillModel.setArrears(arrears);
        fuSalesBillModel.setFuSalesBillDetailList(salesBillDetailList);
        fuSalesBillModel.setSizeListForGroup(sizeModelList);
        fuSalesBillModel.setFuMoneyList(moneyList);
        fuSalesBillModel.setVerifiedSalesBillList(verfidList);
        fuSalesBillModel.setCutMoneyList(fuCutMoneyList);
        return fuSalesBillModel;
    }

    public List getColorList(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuColorModel> colorModelList = new ArrayList<>();
        for (Map<String, Object> map : result){
            FuColorModel fuColorModel = new FuColorModel();
            Integer code = jsonUtility.getIntegerValueFromJSON(map, "code");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer groupid = jsonUtility.getIntegerValueFromJSON(map, "groupid");
            Integer rank = jsonUtility.getIntegerValueFromJSON(map, "rank");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            String groupString = jsonUtility.getStringValueFromJSON(map, "groupString");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");

            fuColorModel.setCode(code);
            fuColorModel.setName(name);
            fuColorModel.setGroupid(groupid);
            fuColorModel.setRank(rank);
            fuColorModel.setStatus(status);
            fuColorModel.setGroupString(groupString);
            fuColorModel.setId(id);
            fuColorModel.setAccountid(accountid);
            fuColorModel.setFlag(flag);
            colorModelList.add(fuColorModel);
        }
        return colorModelList;
    }

    public List getSizeList(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuSizeModel> sizeModelList = new ArrayList<>();
        for (Map<String, Object> map : result){
            FuSizeModel fuSizeModel = new FuSizeModel();
            Integer code = jsonUtility.getIntegerValueFromJSON(map, "code");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer groupid = jsonUtility.getIntegerValueFromJSON(map, "groupid");
            Integer rank = jsonUtility.getIntegerValueFromJSON(map, "rank");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer amount = jsonUtility.getIntegerValueFromJSON(map, "amount");
            String groupString = jsonUtility.getStringValueFromJSON(map, "groupString");
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");

            fuSizeModel.setCode(code);
            fuSizeModel.setName(name);
            fuSizeModel.setGroupid(groupid);
            fuSizeModel.setRank(rank);
            fuSizeModel.setStatus(status);
            fuSizeModel.setAmount(amount);
            fuSizeModel.setGroupString(groupString);
            fuSizeModel.setId(id);
            fuSizeModel.setAccountid(accountid);
            fuSizeModel.setFlag(flag);
            fuSizeModel.setOpid(opid);
            sizeModelList.add(fuSizeModel);
        }
        return sizeModelList;
    }


    public List getColorGroupList(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuColorGroupModel> colorGroupModelList = new ArrayList<>();
        for (Map<String, Object> map : result){
            FuColorGroupModel fuColorGroupModel = new FuColorGroupModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer rank= jsonUtility.getIntegerValueFromJSON(map, "rank");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
            fuColorGroupModel.setId(id);
            fuColorGroupModel.setName(name);
            fuColorGroupModel.setRank(rank);
            fuColorGroupModel.setStatus(status);
            fuColorGroupModel.setAccountid(accountid);
            fuColorGroupModel.setFlag(flag);
            fuColorGroupModel.setOpid(opid);

            colorGroupModelList.add(fuColorGroupModel);
        }
        return colorGroupModelList;
    }

    public List getSizeGroupList(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        List<Map<String, Object>> result = jsonUtility.getListValueFromJSON(queryMap, "result");
        List<FuSizeGroupModel> sizeGroupModelList = new ArrayList<>();
        for (Map<String, Object> map : result){
            FuSizeGroupModel fuSizeGroupModel = new FuSizeGroupModel();
            Integer id = jsonUtility.getIntegerValueFromJSON(map, "id");
            String name = jsonUtility.getStringValueFromJSON(map, "name");
            Integer rank= jsonUtility.getIntegerValueFromJSON(map, "rank");
            Integer status = jsonUtility.getIntegerValueFromJSON(map, "status");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(map, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(map, "flag");
            Integer opid = jsonUtility.getIntegerValueFromJSON(map, "opid");
            fuSizeGroupModel.setId(id);
            fuSizeGroupModel.setName(name);
            fuSizeGroupModel.setRank(rank);
            fuSizeGroupModel.setStatus(status);
            fuSizeGroupModel.setAccountid(accountid);
            fuSizeGroupModel.setFlag(flag);
            fuSizeGroupModel.setOpid(opid);

            sizeGroupModelList.add(fuSizeGroupModel);
        }
        return sizeGroupModelList;
    }

    public FuStaffModel getStaffDataForQRCODE(String data){
        JSONUtility jsonUtility = new JSONUtility();
        Map<String, Object> queryMap = JsonToMap.toMap(data);
        Map<String, Object> result = jsonUtility.getObjectValueFromJSON(queryMap, "result");
        FuStaffModel fuStaffModel = new FuStaffModel();
        //staff
        String code = jsonUtility.getStringValueFromJSON(result, "code");
        String password = jsonUtility.getStringValueFromJSON(result, "password");
        String name = jsonUtility.getStringValueFromJSON(result, "name");
        String searchkey = jsonUtility.getStringValueFromJSON(result, "searchkey");
        String qrcodewx = jsonUtility.getStringValueFromJSON(result, "qrcodewx");

        Integer depid = jsonUtility.getIntegerValueFromJSON(result, "depid");
        Integer status = jsonUtility.getIntegerValueFromJSON(result, "status");

        //门店
        List<Map<String, Object>> fuInventoryList = jsonUtility.getListValueFromJSON(result, "fuInventoryList");
        List<FuInventoryModel> inventoryModelList = new ArrayList<>();
        for (Map<String, Object> one : fuInventoryList){
            FuInventoryModel inventoryModel = new FuInventoryModel();
            String phone = jsonUtility.getStringValueFromJSON(one, "phone");
            String cellphone = jsonUtility.getStringValueFromJSON(one, "cellphone");
            String wechat = jsonUtility.getStringValueFromJSON(one, "wechat");
            String alipay = jsonUtility.getStringValueFromJSON(one, "alipay");
            String accountname = jsonUtility.getStringValueFromJSON(one, "accountname");
            String address = jsonUtility.getStringValueFromJSON(one, "address");
            Integer type = jsonUtility.getIntegerValueFromJSON(one, "type");
            Integer pricetypeid = jsonUtility.getIntegerValueFromJSON(one, "pricetypeid");
            Integer depid2 = jsonUtility.getIntegerValueFromJSON(one, "depid");
            Integer islocked = jsonUtility.getIntegerValueFromJSON(one, "islocked");
            Integer usepurchaseprice = jsonUtility.getIntegerValueFromJSON(one, "usepurchaseprice");
            String realalipay = jsonUtility.getStringValueFromJSON(one, "realalipay");
            String wechatpay = jsonUtility.getStringValueFromJSON(one, "wechatpay");
            Integer printrealalipay = jsonUtility.getIntegerValueFromJSON(one, "printrealalipay");
            Integer printwechatpay = jsonUtility.getIntegerValueFromJSON(one, "printwechatpay");
            BigDecimal discount = jsonUtility.getBigDecimalValueFromJSON(one, "discount");
            String realalipaystring = jsonUtility.getStringValueFromJSON(one, "realalipaystring");
            String wechatpaystring = jsonUtility.getStringValueFromJSON(one, "wechatpaystring");
            String priceTypeString = jsonUtility.getStringValueFromJSON(one, "priceTypeString");
            String title2 = jsonUtility.getStringValueFromJSON(one, "title2");

            Integer invid = jsonUtility.getIntegerValueFromJSON(one, "id");
            String invString = jsonUtility.getStringValueFromJSON(one, "name");
            Integer accountid = jsonUtility.getIntegerValueFromJSON(one, "accountid");
            Integer flag = jsonUtility.getIntegerValueFromJSON(one, "flag");
            Date optime = null;
            try {
                optime = jsonUtility.getDateValueFromJSON(one, "optime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer opid = jsonUtility.getIntegerValueFromJSON(one, "opid");

            inventoryModel.setPhone(phone);
            inventoryModel.setCellphone(cellphone);
            inventoryModel.setWechat(wechat);
            inventoryModel.setAlipay(alipay);
            inventoryModel.setAccountname(accountname);
            inventoryModel.setAddress(address);
            inventoryModel.setType(type);
            inventoryModel.setPricetypeid(pricetypeid);
            inventoryModel.setDepid(depid2);
            inventoryModel.setIslocked(islocked);
            inventoryModel.setUsepurchaseprice(usepurchaseprice);
            inventoryModel.setRealalipay(realalipay);
            inventoryModel.setWechatpay(wechatpay);
            inventoryModel.setPrintrealalipay(printrealalipay);
            inventoryModel.setPrintwechatpay(printwechatpay);
            inventoryModel.setDiscount(discount);
            inventoryModel.setRealalipaystring(realalipaystring);
            inventoryModel.setWechatpaystring(wechatpaystring);
            inventoryModel.setPriceTypeString(priceTypeString);
            inventoryModel.setPriceTypeString(title2);
            inventoryModel.setId(invid);
            inventoryModel.setName(invString);
            inventoryModel.setAccountid(accountid);
            inventoryModel.setFlag(flag);
            inventoryModel.setOptime(optime);
            inventoryModel.setOpid(opid);
            inventoryModelList.add(inventoryModel);
        }

        List<Map<String, Object>> fuRoleList = jsonUtility.getListValueFromJSON(result, "fuRoleList");
        List<FuRoleModel> roleModelList = new ArrayList<>();
        for (Map<String, Object> map : fuRoleList){
            FuRoleModel fuRoleModel = new FuRoleModel();
            String roleString = jsonUtility.getStringValueFromJSON(map, "name");

            fuRoleModel.setName(roleString);
            roleModelList.add(fuRoleModel);
        }
        Integer id = jsonUtility.getIntegerValueFromJSON(result, "id");
        Integer accountid = jsonUtility.getIntegerValueFromJSON(result, "accountid");

        fuStaffModel.setCode(code);
        fuStaffModel.setPassword(password);
        fuStaffModel.setName(name);
        fuStaffModel.setSearchkey(searchkey);
        fuStaffModel.setQrcodewx(qrcodewx);
        fuStaffModel.setDepid(depid);
        fuStaffModel.setStatus(status);
        fuStaffModel.setFuInventoryList(inventoryModelList);
        fuStaffModel.setFuRoleList(roleModelList);

        fuStaffModel.setId(id);
        fuStaffModel.setAccountid(accountid);

        return fuStaffModel;
    }
}