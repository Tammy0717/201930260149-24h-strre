package com.cc.shop.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 * 
 * 
 */
public class Cart implements Serializable{
	
	//放置购物车商品的list
	public List<ShopCartItem> list;
	
	// 购物总计:
	private double total;

	public double getTotal() {
		return total;
	}

	
	
	public void setTotal(double total) {
		this.total = total;
	}

	
	public List<ShopCartItem> getList() {
		return list;
	}



	public void setList(List<ShopCartItem> list) {
		this.list = list;
	}
}
