package com.smyhvae.model;

/**
 * Created by Administrator on 2017/11/3.
 */

public class FuStyleClassModel extends FuBaseModel {
    private Integer styleid;
    private Integer classid;

    private String classString;

    public Integer getStyleid() {
        return styleid;
    }

    public void setStyleid(Integer styleid) {
        this.styleid = styleid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassString() {
        return classString;
    }

    public void setClassString(String classString) {
        this.classString = classString;
    }
}
