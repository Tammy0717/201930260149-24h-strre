package com.cc.shop.pojo;

public class ShopCartItem {
	
	private Integer cartitemid;
	private Integer uid;
	private Integer pid;
	private Integer pcount;
	private Double price;
	private String image;
	private String pname;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	private Double ptotal;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPtotal() {
		return ptotal;
	}
	public void setPtotal(Double ptotal) {
		this.ptotal = ptotal;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCartitemid() {
		return cartitemid;
	}
	public void setCartitemid(Integer cartitemid) {
		this.cartitemid = cartitemid;
	}
	public Integer getPcount() {
		return pcount;
	}
	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}
	
	
	
}
                                                                                                                                                                                           