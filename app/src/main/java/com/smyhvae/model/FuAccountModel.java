package com.smyhvae.model;


import android.graphics.Bitmap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FuAccountModel extends FuBaseModel {
    private String code;
    private String password;
    private String beginCode;
    private String beginOccurrencetime;
    private String endOccurrencetime;
    private Integer status;
    private Integer clientid;
    private String input;
    private Integer justForName;

    private Integer classid;
    private String season;
    private Integer brandid;

    public Integer getIsThumb() {
        return isThumb;
    }

    public void setIsThumb(Integer isThumb) {
        this.isThumb = isThumb;
    }

    private Integer isThumb;

    private Integer isdefault;
    private Integer colorsizemode;
    private Integer scoremode;//积分模式(0:关闭 1:开启)
    private Integer balancetype;//正负类型(0 负, 1 正)
    private Integer calculatetype;//计算类型(0 从销售额剔除, 1 参与销售额计算)
    private Integer inratio;//积分入口系数
    private Integer outratio;//积分出口系数
    private Integer appendclientinfo;//是否追加客户信息
    private Integer uselastprice;//是否使用上次价
    private Integer iscolortitlefirst;//是否颜色为优先标题(0 否, 1 是)
    private Integer tiprepback;//是否补货退货提醒(0 否, 1 是)
    private Integer tipbackvalidation;//是否进行退货验证,即退货数需<=购买数(0 否, 1 是)
    private Integer mp_effective;//是否修改单价权限生效(0 否, 1 是)
    private Integer marketcommodity;//是否补货支持(0 否, 1 是)
    private Integer autoupdatestyleinfo;//是否采购自动更新货品信息(0 否, 1 是)
    private Integer needprintarrears;//是否打印账款信息(0 否, 1 是)
    private Integer msb_effective;//是否修改单据权限生效(0 否, 1 是)
    private Integer validateforcheckbill;//单据操作受盘点限制(0 否, 1 是)
    private Integer datb_effective;//是否修改任意时间单据权限生效(0 否, 1 是)
    private Integer autofixclient;//自动修正客户(0否,1是)
    private Integer verification_effective;//是否销账权限生效(0 否, 1 是)
    private Integer nonnegative;//只允许非负库存(0 否, 1 是)
    private Integer statementforarrears;//是否明细对账单按欠款(0 否, 1 是)
    private Integer defaultstaff;//默认当前操作员为开单店员(0 否, 1 是)
    private Integer inventoryverify;//跨门店销账(0 否, 1 是)
    private Integer autofixdiscount;//修改金额来自动计算折扣值(0 否, 1 是)
    private Integer stylecolorsizeinfo;//款信息落实到颜色尺码(0 否, 1 是)
    private Integer groupbilling;//按组开单(0 否, 1 是)
    private Integer partnerstaff;//合作店员(0 否, 1 是)
    private Integer not_modify_bill_date;//不允许修改单据日期(0 否, 1 是)
    private Integer autosync;//自动同步(0 否, 1 是)
    private Integer not_modify_styleinfo;//不允许修改货品信息(0 否, 1 是)
    private Integer mpt_effective;//是否修改价格类型权限生效(0 否, 1 是)
    private Integer md_effective;//是否修改折扣权限生效(0 否, 1 是)
    private Integer invstock;//只允许查看本门店库存(0 否, 1 是)
    private Integer autocreatestylecode;//自动创建款号(0 否, 1 是)
    private Integer inventoryforarrears;//多门店合并账款(0 否, 1 是)
    private Integer primecostmode;//成本价计算方式(0.默认使用成本价；1.使用进货价)

    private Integer invid;
    private Integer staffid;
    private Integer styleid;

    public Integer getStyleId() {
        return styleid;
    }

    public void setStyleId(Integer styleId) {
        this.styleid = styleId;
    }

    private String rgbcode;
    private Integer groupid;
    private Integer rank;

    public String getRgbcode() {
        return rgbcode;
    }

    public void setRgbcode(String rgbcode) {
        this.rgbcode = rgbcode;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    private Integer logistics;
    private BigDecimal money;
    private Integer type;
    private BigDecimal collectionmoney;
    private String occurrencetime;
    private Integer pricetypeid;
    private BigDecimal discount;
    private Integer staffid2;
    private String marketdate;
    private String price;
    private String standardbarcode;
    private String suppliercode;

    private String phone;
    private String address;
    private Date birthday;
    private String credit;
    private String alarmcredit;
    private String identity;
    private String area;
    private Integer kindid;
    private Integer parentid;

    private List<FuStyleClassModel> styleClasses;
    private List<FuColorModel> colors;
    private List<FuSizeModel> sizes;
    private List<FuStylePriceTypeModel> pricetypes;

    private List<FuSalesBillDetailModel> salesBillDetailList;
    private List<FuMoneyModel> moneyList;

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBeginCode() {
        return beginCode;
    }

    public void setBeginCode(String beginCode) {
        this.beginCode = beginCode;
    }

    public String getBeginOccurrencetime() {
        return beginOccurrencetime;
    }

    public void setBeginOccurrencetime(String beginOccurrencetime) {
        this.beginOccurrencetime = beginOccurrencetime;
    }

    public String getEndOccurrencetime() {
        return endOccurrencetime;
    }

    public void setEndOccurrencetime(String endOccurrencetime) {
        this.endOccurrencetime = endOccurrencetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Integer getJustForName() {
        return justForName;
    }

    public void setJustForName(Integer justForName) {
        this.justForName = justForName;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getColorsizemode() {
        return colorsizemode;
    }

    public void setColorsizemode(Integer colorsizemode) {
        this.colorsizemode = colorsizemode;
    }

    public Integer getScoremode() {
        return scoremode;
    }

    public void setScoremode(Integer scoremode) {
        this.scoremode = scoremode;
    }

    public Integer getBalancetype() {
        return balancetype;
    }

    public void setBalancetype(Integer balancetype) {
        this.balancetype = balancetype;
    }

    public Integer getCalculatetype() {
        return calculatetype;
    }

    public void setCalculatetype(Integer calculatetype) {
        this.calculatetype = calculatetype;
    }

    public Integer getInratio() {
        return inratio;
    }

    public void setInratio(Integer inratio) {
        this.inratio = inratio;
    }

    public Integer getOutratio() {
        return outratio;
    }

    public void setOutratio(Integer outratio) {
        this.outratio = outratio;
    }

    public Integer getAppendclientinfo() {
        return appendclientinfo;
    }

    public void setAppendclientinfo(Integer appendclientinfo) {
        this.appendclientinfo = appendclientinfo;
    }

    public Integer getUselastprice() {
        return uselastprice;
    }

    public void setUselastprice(Integer uselastprice) {
        this.uselastprice = uselastprice;
    }

    public Integer getIscolortitlefirst() {
        return iscolortitlefirst;
    }

    public void setIscolortitlefirst(Integer iscolortitlefirst) {
        this.iscolortitlefirst = iscolortitlefirst;
    }

    public Integer getTiprepback() {
        return tiprepback;
    }

    public void setTiprepback(Integer tiprepback) {
        this.tiprepback = tiprepback;
    }

    public Integer getTipbackvalidation() {
        return tipbackvalidation;
    }

    public void setTipbackvalidation(Integer tipbackvalidation) {
        this.tipbackvalidation = tipbackvalidation;
    }

    public Integer getMp_effective() {
        return mp_effective;
    }

    public void setMp_effective(Integer mp_effective) {
        this.mp_effective = mp_effective;
    }

    public Integer getMarketcommodity() {
        return marketcommodity;
    }

    public void setMarketcommodity(Integer marketcommodity) {
        this.marketcommodity = marketcommodity;
    }

    public Integer getAutoupdatestyleinfo() {
        return autoupdatestyleinfo;
    }

    public void setAutoupdatestyleinfo(Integer autoupdatestyleinfo) {
        this.autoupdatestyleinfo = autoupdatestyleinfo;
    }

    public Integer getNeedprintarrears() {
        return needprintarrears;
    }

    public void setNeedprintarrears(Integer needprintarrears) {
        this.needprintarrears = needprintarrears;
    }

    public Integer getMsb_effective() {
        return msb_effective;
    }

    public void setMsb_effective(Integer msb_effective) {
        this.msb_effective = msb_effective;
    }

    public Integer getValidateforcheckbill() {
        return validateforcheckbill;
    }

    public void setValidateforcheckbill(Integer validateforcheckbill) {
        this.validateforcheckbill = validateforcheckbill;
    }

    public Integer getDatb_effective() {
        return datb_effective;
    }

    public void setDatb_effective(Integer datb_effective) {
        this.datb_effective = datb_effective;
    }

    public Integer getAutofixclient() {
        return autofixclient;
    }

    public void setAutofixclient(Integer autofixclient) {
        this.autofixclient = autofixclient;
    }

    public Integer getVerification_effective() {
        return verification_effective;
    }

    public void setVerification_effective(Integer verification_effective) {
        this.verification_effective = verification_effective;
    }

    public Integer getNonnegative() {
        return nonnegative;
    }

    public void setNonnegative(Integer nonnegative) {
        this.nonnegative = nonnegative;
    }

    public Integer getStatementforarrears() {
        return statementforarrears;
    }

    public void setStatementforarrears(Integer statementforarrears) {
        this.statementforarrears = statementforarrears;
    }

    public Integer getDefaultstaff() {
        return defaultstaff;
    }

    public void setDefaultstaff(Integer defaultstaff) {
        this.defaultstaff = defaultstaff;
    }

    public Integer getInventoryverify() {
        return inventoryverify;
    }

    public void setInventoryverify(Integer inventoryverify) {
        this.inventoryverify = inventoryverify;
    }

    public Integer getAutofixdiscount() {
        return autofixdiscount;
    }

    public void setAutofixdiscount(Integer autofixdiscount) {
        this.autofixdiscount = autofixdiscount;
    }

    public Integer getStylecolorsizeinfo() {
        return stylecolorsizeinfo;
    }

    public void setStylecolorsizeinfo(Integer stylecolorsizeinfo) {
        this.stylecolorsizeinfo = stylecolorsizeinfo;
    }

    public Integer getGroupbilling() {
        return groupbilling;
    }

    public void setGroupbilling(Integer groupbilling) {
        this.groupbilling = groupbilling;
    }

    public Integer getPartnerstaff() {
        return partnerstaff;
    }

    public void setPartnerstaff(Integer partnerstaff) {
        this.partnerstaff = partnerstaff;
    }

    public Integer getNot_modify_bill_date() {
        return not_modify_bill_date;
    }

    public void setNot_modify_bill_date(Integer not_modify_bill_date) {
        this.not_modify_bill_date = not_modify_bill_date;
    }

    public Integer getAutosync() {
        return autosync;
    }

    public void setAutosync(Integer autosync) {
        this.autosync = autosync;
    }

    public Integer getNot_modify_styleinfo() {
        return not_modify_styleinfo;
    }

    public void setNot_modify_styleinfo(Integer not_modify_styleinfo) {
        this.not_modify_styleinfo = not_modify_styleinfo;
    }

    public Integer getMpt_effective() {
        return mpt_effective;
    }

    public void setMpt_effective(Integer mpt_effective) {
        this.mpt_effective = mpt_effective;
    }

    public Integer getMd_effective() {
        return md_effective;
    }

    public void setMd_effective(Integer md_effective) {
        this.md_effective = md_effective;
    }

    public Integer getInvstock() {
        return invstock;
    }

    public void setInvstock(Integer invstock) {
        this.invstock = invstock;
    }

    public Integer getAutocreatestylecode() {
        return autocreatestylecode;
    }

    public void setAutocreatestylecode(Integer autocreatestylecode) {
        this.autocreatestylecode = autocreatestylecode;
    }

    public Integer getInventoryforarrears() {
        return inventoryforarrears;
    }

    public void setInventoryforarrears(Integer inventoryforarrears) {
        this.inventoryforarrears = inventoryforarrears;
    }

    public Integer getPrimecostmode() {
        return primecostmode;
    }

    public void setPrimecostmode(Integer primecostmode) {
        this.primecostmode = primecostmode;
    }

    public Integer getInvid() {
        return invid;
    }

    public void setInvid(Integer invid) {
        this.invid = invid;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public Integer getLogistics() {
        return logistics;
    }

    public void setLogistics(Integer logistics) {
        this.logistics = logistics;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getCollectionmoney() {
        return collectionmoney;
    }

    public void setCollectionmoney(BigDecimal collectionmoney) {
        this.collectionmoney = collectionmoney;
    }

    public String getOccurrencetime() {
        return occurrencetime;
    }

    public void setOccurrencetime(String occurrencetime) {
        this.occurrencetime = occurrencetime;
    }

    public Integer getPricetypeid() {
        return pricetypeid;
    }

    public void setPricetypeid(Integer pricetypeid) {
        this.pricetypeid = pricetypeid;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getStaffid2() {
        return staffid2;
    }

    public void setStaffid2(Integer staffid2) {
        this.staffid2 = staffid2;
    }

    public String getMarketdate() {
        return marketdate;
    }

    public void setMarketdate(String marketdate) {
        this.marketdate = marketdate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStandardbarcode() {
        return standardbarcode;
    }

    public void setStandardbarcode(String standardbarcode) {
        this.standardbarcode = standardbarcode;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getAlarmcredit() {
        return alarmcredit;
    }

    public void setAlarmcredit(String alarmcredit) {
        this.alarmcredit = alarmcredit;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getKindid() {
        return kindid;
    }

    public void setKindid(Integer kindid) {
        this.kindid = kindid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public List<FuStyleClassModel> getStyleClasses() {
        return styleClasses;
    }

    public void setStyleClasses(List<FuStyleClassModel> styleClasses) {
        this.styleClasses = styleClasses;
    }

    public List<FuColorModel> getColors() {
        return colors;
    }

    public void setColors(List<FuColorModel> colors) {
        this.colors = colors;
    }

    public List<FuSizeModel> getSizes() {
        return sizes;
    }

    public void setSizes(List<FuSizeModel> sizes) {
        this.sizes = sizes;
    }

    public List<FuStylePriceTypeModel> getPricetypes() {
        return pricetypes;
    }

    public void setPricetypes(List<FuStylePriceTypeModel> pricetypes) {
        this.pricetypes = pricetypes;
    }

    public List<FuSalesBillDetailModel> getSalesBillDetailList() {
        return salesBillDetailList;
    }

    public void setSalesBillDetailList(List<FuSalesBillDetailModel> salesBillDetailList) {
        this.salesBillDetailList = salesBillDetailList;
    }

    public List<FuMoneyModel> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(List<FuMoneyModel> moneyList) {
        this.moneyList = moneyList;
    }
}
