package com.cc.shop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.shop.pojo.BillItem;
import com.cc.shop.pojo.Message;
import com.cc.shop.pojo.Order;
import com.cc.shop.pojo.Product;
import com.cc.shop.utils.PageHibernateCallback;

public class MessageDao extends HibernateDaoSupport {

	public void save(Message message) {
		this.getHibernateTemplate().save(message);
		System.out.println("添加商品成功");
	}

	// Dao层查询我的订单分页查询:查询数据
	public List<Message> findPageMessage(int begin, int limit) {
		String hql = "from Message ";
		List<Message> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Message>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public List<Message> findAllMessage() {
		String hql = "from Message";
		List<Message> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.println("username:"
						+ list.get(i).getUser().getUsername());
				System.out.println("message" + list.get(i).getMessage());
				System.out.println("date:"
						+ list.get(i).getMessagedate().toString());
			}
			return list;
		}
		return list;
	}

	public Message findByMessageId(Integer messageId) {

		return this.getHibernateTemplate().get(Message.class, messageId);

	}

	public void delete(Message message) {
		this.getHibernateTemplate().delete(message);
	}

}
