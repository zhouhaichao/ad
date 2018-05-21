package com.smyhvae.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FuSalesBillModel extends FuBaseModel {
    private Integer code;
    private Integer clientid;
    private Integer invid;
    private Integer staffid;
    private Date occurrencetime;
    private Integer status;
    private Integer printflag;
    private Integer logistics;
    private String waybillcode;
    private BigDecimal money;
    private Integer type;
    private Integer scoretype;
    private BigDecimal verificationmoney;
    private Integer verificationflag;
    private Integer verificationsalesbillid;
    private BigDecimal collectionmoney;
    private Integer pricetypeid;
    private BigDecimal discount;
    private Integer staffid2;
    private BigDecimal optime2;

    private String clientString;
    private String invString;
    private String staffString;

    private Integer salesCount;//上货次数
    private Integer salesCountForCopy;//上货次数
    private Integer salesAmountForCopy;//上货数量

    private BigDecimal total;// 该单据的明细应收金额
    private Integer amount;// 该单据的明细数量
    private BigDecimal salesTotal;// 销售数应收金额(以下四个是统计时用到)
    private Integer salesAmount;// 销售数数量
    private BigDecimal cancelTotal;// 退款数应收金额
    private Integer cancelAmount;// 退款数明细数量
    private BigDecimal realMoney;//该单据的实收金额
    private BigDecimal preSalesTotal;//不包含优惠信息的实际应收
    private BigDecimal realSalesTotal;//不包含优惠信息的实际应收(替换preSalesTotal)

    private BigDecimal balance;

    private BigDecimal cash;
    private BigDecimal swingCard;
    private BigDecimal remit;

    private BigDecimal arrears;
    private String payStatus;
    private String staffString2;
    private String opString;
    private BigDecimal salesbillArrears;

    private String pricetypeString;
    private String clientAddress;
    private String clientPhone;

    private List<FuSalesBillDetailModel> fuSalesBillDetailList;
    private List<FuSalesBillModel> verifiedSalesBillList;
    private List<FuSizeModel> sizeListForGroup;
    private List<FuMoneyModel> fuMoneyList;

    private String modelString;
    private List<FuSalesBillDetailModel> cutMoneyList;//用于查询单据明细时，保存extramoneyfix不为空的单据明细


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
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

    public Date getOccurrencetime() {
        return occurrencetime;
    }

    public void setOccurrencetime(Date occurrencetime) {
        this.occurrencetime = occurrencetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrintflag() {
        return printflag;
    }

    public void setPrintflag(Integer printflag) {
        this.printflag = printflag;
    }

    public Integer getLogistics() {
        return logistics;
    }

    public void setLogistics(Integer logistics) {
        this.logistics = logistics;
    }

    public String getWaybillcode() {
        return waybillcode;
    }

    public void setWaybillcode(String waybillcode) {
        this.waybillcode = waybillcode;
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

    public Integer getScoretype() {
        return scoretype;
    }

    public void setScoretype(Integer scoretype) {
        this.scoretype = scoretype;
    }

    public BigDecimal getVerificationmoney() {
        return verificationmoney;
    }

    public void setVerificationmoney(BigDecimal verificationmoney) {
        this.verificationmoney = verificationmoney;
    }

    public Integer getVerificationflag() {
        return verificationflag;
    }

    public void setVerificationflag(Integer verificationflag) {
        this.verificationflag = verificationflag;
    }

    public Integer getVerificationsalesbillid() {
        return verificationsalesbillid;
    }

    public void setVerificationsalesbillid(Integer verificationsalesbillid) {
        this.verificationsalesbillid = verificationsalesbillid;
    }

    public BigDecimal getCollectionmoney() {
        return collectionmoney;
    }

    public void setCollectionmoney(BigDecimal collectionmoney) {
        this.collectionmoney = collectionmoney;
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

    public BigDecimal getOptime2() {
        return optime2;
    }

    public void setOptime2(BigDecimal optime2) {
        this.optime2 = optime2;
    }

    public String getClientString() {
        return clientString;
    }

    public void setClientString(String clientString) {
        this.clientString = clientString;
    }

    public String getInvString() {
        return invString;
    }

    public void setInvString(String invString) {
        this.invString = invString;
    }

    public String getStaffString() {
        return staffString;
    }

    public void setStaffString(String staffString) {
        this.staffString = staffString;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getSalesCountForCopy() {
        return salesCountForCopy;
    }

    public void setSalesCountForCopy(Integer salesCountForCopy) {
        this.salesCountForCopy = salesCountForCopy;
    }

    public Integer getSalesAmountForCopy() {
        return salesAmountForCopy;
    }

    public void setSalesAmountForCopy(Integer salesAmountForCopy) {
        this.salesAmountForCopy = salesAmountForCopy;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public BigDecimal getCancelTotal() {
        return cancelTotal;
    }

    public void setCancelTotal(BigDecimal cancelTotal) {
        this.cancelTotal = cancelTotal;
    }

    public Integer getCancelAmount() {
        return cancelAmount;
    }

    public void setCancelAmount(Integer cancelAmount) {
        this.cancelAmount = cancelAmount;
    }

    public BigDecimal getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(BigDecimal realMoney) {
        this.realMoney = realMoney;
    }

    public BigDecimal getPreSalesTotal() {
        return preSalesTotal;
    }

    public void setPreSalesTotal(BigDecimal preSalesTotal) {
        this.preSalesTotal = preSalesTotal;
    }

    public BigDecimal getRealSalesTotal() {
        return realSalesTotal;
    }

    public void setRealSalesTotal(BigDecimal realSalesTotal) {
        this.realSalesTotal = realSalesTotal;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getSwingCard() {
        return swingCard;
    }

    public void setSwingCard(BigDecimal swingCard) {
        this.swingCard = swingCard;
    }

    public BigDecimal getRemit() {
        return remit;
    }

    public void setRemit(BigDecimal remit) {
        this.remit = remit;
    }

    public BigDecimal getArrears() {
        return arrears;
    }

    public void setArrears(BigDecimal arrears) {
        this.arrears = arrears;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getStaffString2() {
        return staffString2;
    }

    public void setStaffString2(String staffString2) {
        this.staffString2 = staffString2;
    }

    public String getOpString() {
        return opString;
    }

    public void setOpString(String opString) {
        this.opString = opString;
    }

    public BigDecimal getSalesbillArrears() {
        return salesbillArrears;
    }

    public void setSalesbillArrears(BigDecimal salesbillArrears) {
        this.salesbillArrears = salesbillArrears;
    }

    public String getPricetypeString() {
        return pricetypeString;
    }

    public void setPricetypeString(String pricetypeString) {
        this.pricetypeString = pricetypeString;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public List<FuSalesBillDetailModel> getFuSalesBillDetailList() {
        return fuSalesBillDetailList;
    }

    public void setFuSalesBillDetailList(List<FuSalesBillDetailModel> fuSalesBillDetailList) {
        this.fuSalesBillDetailList = fuSalesBillDetailList;
    }

    public List<FuSalesBillModel> getVerifiedSalesBillList() {
        return verifiedSalesBillList;
    }

    public void setVerifiedSalesBillList(List<FuSalesBillModel> verifiedSalesBillList) {
        this.verifiedSalesBillList = verifiedSalesBillList;
    }

    public List<FuSizeModel> getSizeListForGroup() {
        return sizeListForGroup;
    }

    public void setSizeListForGroup(List<FuSizeModel> sizeListForGroup) {
        this.sizeListForGroup = sizeListForGroup;
    }

    public List<FuMoneyModel> getFuMoneyList() {
        return fuMoneyList;
    }

    public void setFuMoneyList(List<FuMoneyModel> fuMoneyList) {
        this.fuMoneyList = fuMoneyList;
    }

    public String getModelString() {
        return modelString;
    }

    public void setModelString(String modelString) {
        this.modelString = modelString;
    }

    public List<FuSalesBillDetailModel> getCutMoneyList() {
        return cutMoneyList;
    }

    public void setCutMoneyList(List<FuSalesBillDetailModel> cutMoneyList) {
        this.cutMoneyList = cutMoneyList;
    }
}
