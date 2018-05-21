package com.smyhvae.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2018/4/23.
 */

public class FuDynamicModel {

    private String difference;
    private Integer id;
    private String name;
    private Integer imageId;
    private Drawable image;

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
