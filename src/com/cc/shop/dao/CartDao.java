package com.cc.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.shop.pojo.Product;
import com.cc.shop.pojo.ShopCartItem;
import com.cc.shop.pojo.User;
import com.cc.shop.utils.PageHibernateCallback;



/**
 * 商品持久层的代码
 * 
 * 
 */
public class CartDao extends HibernateDaoSupport {
	
	
	
	//根据用户id和商品id去购物车查询
	public ShopCartItem findCartBYUidPid(Integer uid,Integer pid) {
		Object[] params=new Object[]{uid,pid};
		String hql = "from ShopCartItem p where p.uid = ? and p.pid = ?";
		List<ShopCartItem> list = this.getHibernateTemplate().find(hql,params);
		if(list != null && list.size() > 0){
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				System.out.println("uid:"+list.get(i).getUid());
				System.out.println("pid"+list.get(i).getPid());
				System.out.println("Pcount"+list.get(i).getPcount());
			}
			return list.get(0);
		}
		return null;
	}
	
	
	public List findMyCart(Integer uid){
		Object[] params=new Object[]{uid};
		String hql = "from ShopCartItem p where p.uid = ?";
		List<ShopCartItem> list = this.getHibernateTemplate().find(hql,params);
		if(list != null && list.size() > 0){
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				System.out.println("uid:"+list.get(i).getUid());
				System.out.println("pid"+list.get(i).getPid());
				System.out.println("Pcount"+list.get(i).getPcount());
			}
			return list;
		}
		return null;
	}
	
	
	public void deleteShopCartItem(ShopCartItem shopCartItem){
		this.getHibernateTemplate().delete(shopCartItem);
	}
	
	
	
	
	public void save(ShopCartItem shopCartItem) {
		System.out.println(shopCartItem.getUid());
		System.out.println(shopCartItem.getPid());
		System.out.println(shopCartItem.getPcount());
		System.out.println(shopCartItem.getPrice());
		this.getHibernateTemplate().save(shopCartItem);
		System.out.println("添加商品成功");
	}
	
	public void update(ShopCartItem shopCartItem) {
		this.getHibernateTemplate().update(shopCartItem);
	}
	
	
	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}






}
