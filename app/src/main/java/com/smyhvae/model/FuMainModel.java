package com.smyhvae.model;

public class FuMainModel {
    private String serviceid;
    private String methodid;
    private FuBaseModel parameter;

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getMethodid() {
        return methodid;
    }

    public void setMethodid(String methodid) {
        this.methodid = methodid;
    }

    public FuBaseModel getParameter() {
        return parameter;
    }

    public void setParameter(FuBaseModel parameter) {
        this.parameter = parameter;
    }
}
