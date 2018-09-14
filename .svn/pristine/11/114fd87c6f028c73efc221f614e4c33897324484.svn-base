package com.smyhvae.model;

import java.math.BigDecimal;

public class FuExtraMoneyFixModel extends FuBaseModel {

	/**
	 * 额外补差实体
	 */

	private Integer balancetype;//正负类型(0 负, 1 正)
	private Integer calculatetype;//计算类型(0 从销售额剔除, 1 参与销售额计算)
	private Integer status;

	private String balancetypeString;
	private String calculatetypeString;

	private BigDecimal moneyfix;
	

	public Integer getBalancetype() {
		return balancetype;
	}

	public void setBalancetype(Integer balancetype) {
		this.balancetype = balancetype;
		if(balancetype != null){
			this.balancetypeString = this.balancetype == 0 ? "减少" : "增加";
		}
	}

	public Integer getCalculatetype() {
		return calculatetype;
	}

	public void setCalculatetype(Integer calculatetype) {
		this.calculatetype = calculatetype;
		if(calculatetype != null){
			this.calculatetypeString = this.calculatetype == 0 ? "否" : "是";
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBalancetypeString() {
		return balancetypeString;
	}

	public void setBalancetypeString(String balancetypeString) {
		this.balancetypeString = balancetypeString;
	}

	public String getCalculatetypeString() {
		return calculatetypeString;
	}

	public void setCalculatetypeString(String calculatetypeString) {
		this.calculatetypeString = calculatetypeString;
	}

	public BigDecimal getMoneyfix() {
		return moneyfix;
	}

	public void setMoneyfix(BigDecimal moneyfix) {
		this.moneyfix = moneyfix;
	}
}
