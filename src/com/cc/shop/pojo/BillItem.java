package com.cc.shop.pojo;

public class BillItem {
	
	private Integer billid;
	//private Integer pid;
	private Integer count;
	private Double ptotal;
	
	private Product product;

	public Integer getBillid() {
		return billid;
	}

	public void setBillid(Integer billid) {
		this.billid = billid;
	}

	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPtotal() {
		return ptotal;
	}

	public void setPtotal(Double ptotal) {
		this.ptotal = ptotal;
	}
	
}
