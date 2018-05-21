package com.smyhvae.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class FuStaffModel extends FuBaseModel {
    private String code;
    private String password;
    private String phone;
    private String email;
    private Integer sex;
    private Date birthday;
    private String searchkey;
    private String path;
    private String qrcodepath;
    private String qrcodewx;
    private Integer depid;
    private Integer status;
    private String sexString;
    private String departmentName;
    private List<FuInventoryModel> fuInventoryList;
    private List<FuPaymentModel> fuPaymentList;
    private List<FuAuthorityModel> fuAuthorityList;
    private List<FuRoleModel> fuRoleList;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQrcodepath() {
        return qrcodepath;
    }

    public void setQrcodepath(String qrcodepath) {
        this.qrcodepath = qrcodepath;
    }

    public String getQrcodewx() {
        return qrcodewx;
    }

    public void setQrcodewx(String qrcodewx) {
        this.qrcodewx = qrcodewx;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSexString() {
        return sexString;
    }

    public void setSexString(String sexString) {
        this.sexString = sexString;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<FuInventoryModel> getFuInventoryList() {
        return fuInventoryList;
    }

    public void setFuInventoryList(List<FuInventoryModel> fuInventoryList) {
        this.fuInventoryList = fuInventoryList;
    }

    public List<FuPaymentModel> getFuPaymentList() {
        return fuPaymentList;
    }

    public void setFuPaymentList(List<FuPaymentModel> fuPaymentList) {
        this.fuPaymentList = fuPaymentList;
    }

    public List<FuAuthorityModel> getFuAuthorityList() {
        return fuAuthorityList;
    }

    public void setFuAuthorityList(List<FuAuthorityModel> fuAuthorityList) {
        this.fuAuthorityList = fuAuthorityList;
    }

    public List<FuRoleModel> getFuRoleList() {
        return fuRoleList;
    }

    public void setFuRoleList(List<FuRoleModel> fuRoleList) {
        this.fuRoleList = fuRoleList;
    }
}
