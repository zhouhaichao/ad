package com.smyhvae.model;

/**
 * Created by Administrator on 2017/11/3.
 */

public class FuStyleColorModel extends FuBaseModel {
    private Integer colorid;

    private String colorString;

    private Integer groupid;
    private Integer rank;

    private String groupString;

    public Integer getColorid() {
        return colorid;
    }

    public void setColorid(Integer colorid) {
        this.colorid = colorid;
    }

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
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

    public String getGroupString() {
        return groupString;
    }

    public void setGroupString(String groupString) {
        this.groupString = groupString;
    }
}
