package com.smyhvae.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class FuInventoryModel extends FuBaseModel {
    private String phone;
    private String cellphone;
    private String wechat;
    private String alipay;
    private String accountname;
    private String email;
    private String address;
    private Integer type;
    private Integer pricetypeid;
    private Integer depid;
    private Integer islocked;
    private Integer usepurchaseprice;
    private String realalipay;
    private String wechatpay;
    private Integer printrealalipay;
    private Integer printwechatpay;
    private BigDecimal discount;
    private String realalipaystring;
    private String wechatpaystring;
    private String title2;

    private String priceTypeString;
    private String typeString;

    //上级门店id
    private Integer parentinvid;
    private String parentinvString;
    private List<FuInventoryBankAccountModel> fuInventoryBankAccountList;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPricetypeid() {
        return pricetypeid;
    }

    public void setPricetypeid(Integer pricetypeid) {
        this.pricetypeid = pricetypeid;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public Integer getIslocked() {
        return islocked;
    }

    public void setIslocked(Integer islocked) {
        this.islocked = islocked;
    }

    public Integer getUsepurchaseprice() {
        return usepurchaseprice;
    }

    public void setUsepurchaseprice(Integer usepurchaseprice) {
        this.usepurchaseprice = usepurchaseprice;
    }

    public String getRealalipay() {
        return realalipay;
    }

    public void setRealalipay(String realalipay) {
        this.realalipay = realalipay;
    }

    public String getWechatpay() {
        return wechatpay;
    }

    public void setWechatpay(String wechatpay) {
        this.wechatpay = wechatpay;
    }

    public Integer getPrintrealalipay() {
        return printrealalipay;
    }

    public void setPrintrealalipay(Integer printrealalipay) {
        this.printrealalipay = printrealalipay;
    }

    public Integer getPrintwechatpay() {
        return printwechatpay;
    }

    public void setPrintwechatpay(Integer printwechatpay) {
        this.printwechatpay = printwechatpay;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getRealalipaystring() {
        return realalipaystring;
    }

    public void setRealalipaystring(String realalipaystring) {
        this.realalipaystring = realalipaystring;
    }

    public String getWechatpaystring() {
        return wechatpaystring;
    }

    public void setWechatpaystring(String wechatpaystring) {
        this.wechatpaystring = wechatpaystring;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getPriceTypeString() {
        return priceTypeString;
    }

    public void setPriceTypeString(String priceTypeString) {
        this.priceTypeString = priceTypeString;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public Integer getParentinvid() {
        return parentinvid;
    }

    public void setParentinvid(Integer parentinvid) {
        this.parentinvid = parentinvid;
    }

    public String getParentinvString() {
        return parentinvString;
    }

    public void setParentinvString(String parentinvString) {
        this.parentinvString = parentinvString;
    }

    public List<FuInventoryBankAccountModel> getFuInventoryBankAccountList() {
        return fuInventoryBankAccountList;
    }

    public void setFuInventoryBankAccountList(List<FuInventoryBankAccountModel> fuInventoryBankAccountList) {
        this.fuInventoryBankAccountList = fuInventoryBankAccountList;
    }
}
