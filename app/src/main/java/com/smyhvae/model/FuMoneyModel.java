package com.smyhvae.model;

import java.math.BigDecimal;
import java.util.Date;

public class FuMoneyModel extends FuBaseModel {

	/**
	 * 账款
	 */

	private Integer moneyaccountid;
	private Integer paymentid;
	private BigDecimal money;
	private Integer clientid;
	private Integer invid;
	private Integer salesbillid;
	private Date optime2;//操作时间(之前把optime当成occurrencetime使用，所以此处使用optime2)
	private Integer flag2;
	
	private String moneyaccountString;
	private String paymentString;
	private String clientString;
	private String invString;
	private String opString;

	private Integer code;
	
	private Integer status;
	private String typeString;
	private Date occurrencetime;
	private BigDecimal balance;

	private Integer type;

	public Integer getMoneyaccountid() {
		return moneyaccountid;
	}

	public void setMoneyaccountid(Integer moneyaccountid) {
		this.moneyaccountid = moneyaccountid;
	}

	public Integer getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}

	public Integer getInvid() {
		return invid;
	}

	public void setInvid(Integer invid) {
		this.invid = invid;
	}

	public Integer getSalesbillid() {
		return salesbillid;
	}

	public void setSalesbillid(Integer salesbillid) {
		this.salesbillid = salesbillid;
	}

	public Date getOptime2() {
		return optime2;
	}

	public void setOptime2(Date optime2) {
		this.optime2 = optime2;
	}

	public Integer getFlag2() {
		return flag2;
	}

	public void setFlag2(Integer flag2) {
		this.flag2 = flag2;
	}

	public String getMoneyaccountString() {
		return moneyaccountString;
	}

	public void setMoneyaccountString(String moneyaccountString) {
		this.moneyaccountString = moneyaccountString;
	}

	public String getPaymentString() {
		return paymentString;
	}

	public void setPaymentString(String paymentString) {
		this.paymentString = paymentString;
	}

	public String getClientString() {
		return clientString;
	}

	public void setClientString(String clientString) {
		this.clientString = clientString;
	}

	public String getInvString() {
		return invString;
	}

	public void setInvString(String invString) {
		this.invString = invString;
	}

	public String getOpString() {
		return opString;
	}

	public void setOpString(String opString) {
		this.opString = opString;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public Date getOccurrencetime() {
		return occurrencetime;
	}

	public void setOccurrencetime(Date occurrencetime) {
		this.occurrencetime = occurrencetime;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
