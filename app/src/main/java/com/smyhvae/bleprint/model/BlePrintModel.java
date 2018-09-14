package com.smyhvae.bleprint.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/12.
 */

public class BlePrintModel implements Serializable{

    private String title;

    public BlePrintModel() {
    }

    public BlePrintModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
