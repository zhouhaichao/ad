package com.smyhvae.view;

import android.graphics.drawable.Drawable;


public class ListItem {
    private Drawable image;
    private Integer ImageId;
    private Integer id;
    private String code;
    private String name;
    private String amount;
    private String pricetypeString;
    private String price;
    private String marketdate;

    private String occurrencetime;
    private String clientString;
    private Integer preSalesTotal;
    private Integer realMoney;
    private Integer cash;
    private Integer swingCard;
    private Integer remit;
    private Integer status;

    public Integer getImageId() {
        return ImageId;
    }

    public void setImageId(Integer imageId) {
        ImageId = imageId;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPricetypeString() {
        return pricetypeString;
    }

    public void setPricetypeString(String pricetypeString) {
        this.pricetypeString = pricetypeString;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketdate() {
        return marketdate;
    }

    public void setMarketdate(String marketdate) {
        this.marketdate = marketdate;
    }

    public String getOccurrencetime() {
        return occurrencetime;
    }

    public void setOccurrencetime(String occurrencetime) {
        this.occurrencetime = occurrencetime;
    }

    public String getClientString() {
        return clientString;
    }

    public void setClientString(String clientString) {
        this.clientString = clientString;
    }

    public Integer getPreSalesTotal() {
        return preSalesTotal;
    }

    public void setPreSalesTotal(Integer preSalesTotal) {
        this.preSalesTotal = preSalesTotal;
    }

    public Integer getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Integer realMoney) {
        this.realMoney = realMoney;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getSwingCard() {
        return swingCard;
    }

    public void setSwingCard(Integer swingCard) {
        this.swingCard = swingCard;
    }

    public Integer getRemit() {
        return remit;
    }

    public void setRemit(Integer remit) {
        this.remit = remit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}