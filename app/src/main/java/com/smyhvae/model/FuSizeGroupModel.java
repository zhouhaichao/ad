/**
 * 
 */
package com.smyhvae.model;

import java.util.List;

public class FuSizeGroupModel extends FuBaseModel {

	private Integer rank;
	private Integer status;
	
	private List<FuSizeModel> fuSizeList;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<FuSizeModel> getFuSizeList() {
		return fuSizeList;
	}

	public void setFuSizeList(List<FuSizeModel> fuSizeList) {
		this.fuSizeList = fuSizeList;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
