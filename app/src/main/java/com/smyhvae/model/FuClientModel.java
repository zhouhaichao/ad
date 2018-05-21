package com.smyhvae.model;


import java.math.BigDecimal;
import java.util.Date;

public class FuClientModel extends FuBaseModel {
    private String shortname;
    private String phone;
    private String address;
    private String email;
    private Integer sex;
    private Integer age;
    private Date birthday;
    private String searchkey;
    private BigDecimal discount;
    private BigDecimal credit;
    private BigDecimal alarmcredit;
    private BigDecimal monthcredit;
    private BigDecimal monthalarmcredit;
    private BigDecimal yearcredit;
    private BigDecimal yearalarmcredit;
    private Integer score;
    private Integer pricetypeid;
    private Integer staffid;
    private Integer type;
    private Integer invid;
    private String identity;//身份标识
    private Integer status;
    private Integer firmware;//追加字段(是否固件(0否,1是))
    private Integer parentid;
    private String area;//客户地区
    private Integer kindid;//客户类别

    private String sexString;
    private String staffName;//客户名称
    private String invName;//门店

    private String pricetypeString;
    private String parentString;
    private String kindString;

    private BigDecimal totalAmount;
    private BigDecimal arrears;//欠款
    private BigDecimal collecting;//代收
    private Integer salesinvid;//单据invid

    private Integer arrearsStatus; //账款状态

    private BigDecimal prePayMoney;//预付款

    private Integer clientidForLogistics;//物流商欠款处，比较特殊，注意

    private String staffCode;

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getAlarmcredit() {
        return alarmcredit;
    }

    public void setAlarmcredit(BigDecimal alarmcredit) {
        this.alarmcredit = alarmcredit;
    }

    public BigDecimal getMonthcredit() {
        return monthcredit;
    }

    public void setMonthcredit(BigDecimal monthcredit) {
        this.monthcredit = monthcredit;
    }

    public BigDecimal getMonthalarmcredit() {
        return monthalarmcredit;
    }

    public void setMonthalarmcredit(BigDecimal monthalarmcredit) {
        this.monthalarmcredit = monthalarmcredit;
    }

    public BigDecimal getYearcredit() {
        return yearcredit;
    }

    public void setYearcredit(BigDecimal yearcredit) {
        this.yearcredit = yearcredit;
    }

    public BigDecimal getYearalarmcredit() {
        return yearalarmcredit;
    }

    public void setYearalarmcredit(BigDecimal yearalarmcredit) {
        this.yearalarmcredit = yearalarmcredit;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPricetypeid() {
        return pricetypeid;
    }

    public void setPricetypeid(Integer pricetypeid) {
        this.pricetypeid = pricetypeid;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInvid() {
        return invid;
    }

    public void setInvid(Integer invid) {
        this.invid = invid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFirmware() {
        return firmware;
    }

    public void setFirmware(Integer firmware) {
        this.firmware = firmware;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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

    public String getSexString() {
        return sexString;
    }

    public void setSexString(String sexString) {
        this.sexString = sexString;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getPricetypeString() {
        return pricetypeString;
    }

    public void setPricetypeString(String pricetypeString) {
        this.pricetypeString = pricetypeString;
    }

    public String getParentString() {
        return parentString;
    }

    public void setParentString(String parentString) {
        this.parentString = parentString;
    }

    public String getKindString() {
        return kindString;
    }

    public void setKindString(String kindString) {
        this.kindString = kindString;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getArrears() {
        return arrears;
    }

    public void setArrears(BigDecimal arrears) {
        this.arrears = arrears;
    }

    public BigDecimal getCollecting() {
        return collecting;
    }

    public void setCollecting(BigDecimal collecting) {
        this.collecting = collecting;
    }

    public Integer getSalesinvid() {
        return salesinvid;
    }

    public void setSalesinvid(Integer salesinvid) {
        this.salesinvid = salesinvid;
    }

    public Integer getArrearsStatus() {
        return arrearsStatus;
    }

    public void setArrearsStatus(Integer arrearsStatus) {
        this.arrearsStatus = arrearsStatus;
    }

    public BigDecimal getPrePayMoney() {
        return prePayMoney;
    }

    public void setPrePayMoney(BigDecimal prePayMoney) {
        this.prePayMoney = prePayMoney;
    }

    public Integer getClientidForLogistics() {
        return clientidForLogistics;
    }

    public void setClientidForLogistics(Integer clientidForLogistics) {
        this.clientidForLogistics = clientidForLogistics;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
