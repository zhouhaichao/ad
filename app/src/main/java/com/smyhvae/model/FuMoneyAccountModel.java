/**
 * 
 */
package com.smyhvae.model;

public class FuMoneyAccountModel extends FuBaseModel {

	/**
	 * 账款账户
	 */

	private String code;
	private Integer status;
	private Integer firmware;
	private Integer isdefault;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFirmware() {
		return firmware;
	}

	public void setFirmware(Integer firmware) {
		this.firmware = firmware;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

}
