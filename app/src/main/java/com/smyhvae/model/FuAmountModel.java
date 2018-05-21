package com.smyhvae.model;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/31.
 */

public class FuAmountModel extends FuBaseModel {
    private BigDecimal sumAmount;
    private BigDecimal sumTotal;
    private BigDecimal sumRealMoney;

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }

    public BigDecimal getSumRealMoney() {
        return sumRealMoney;
    }

    public void setSumRealMoney(BigDecimal sumRealMoney) {
        this.sumRealMoney = sumRealMoney;
    }
}
