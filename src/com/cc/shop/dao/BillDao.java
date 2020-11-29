package com.cc.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.shop.pojo.BillItem;
import com.cc.shop.pojo.Product;
import com.cc.shop.pojo.ShopCartItem;
import com.cc.shop.utils.PageHibernateCallback;

public class BillDao extends HibernateDaoSupport {

	// DAO中的保存商品的方法
	public void save(BillItem billItem) {
		this.getHibernateTemplate().save(billItem);
	}

	public void update(BillItem billItem) {
		this.getHibernateTemplate().update(billItem);
	}

	public BillItem findBillItemByPid(Integer pid) {
		String hql = "from BillItem p where p.product.pid = ?";
		List<BillItem> list = this.getHibernateTemplate().find(hql, pid);
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.println("pid:" + list.get(i).getProduct().getPid());
				System.out.println("count" + list.get(i).getCount());
				System.out.println("Ptotal" + list.get(i).getPtotal());
			}
			return list.get(0);

		}
		return null;

	}

	public List<BillItem> findAllBillItem() {
		String hql = "from BillItem ";
		List<BillItem> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.println("pid:" + list.get(i).getProduct().getPid());
				System.out.println("count" + list.get(i).getCount());
				System.out.println("Ptotal" + list.get(i).getPtotal());
			}
			return list;
		}
		return list;
	}

	public int findCount() {
		return 1;
	}

	// 后台查询所有商品的方法
	public List<BillItem> findByPage(int begin, int limit) {
		String hql = "from BillItem order by product.pid desc";
		List<BillItem> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<BillItem>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
