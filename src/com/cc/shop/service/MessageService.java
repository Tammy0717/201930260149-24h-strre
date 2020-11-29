package com.cc.shop.service;

import java.util.List;

import com.cc.shop.dao.MessageDao;
import com.cc.shop.pojo.Message;
import com.cc.shop.pojo.Order;
import com.cc.shop.utils.PageBean;

public class MessageService {
	private MessageDao messageDao;

	public void saveMessage(Message message) {
		messageDao.save(message);
	}

	// 业务层根据用户id查询订单,带分页查询.
	public PageBean<Message> findByPage(Integer page) {
		System.out.println("页数："+page);
		PageBean<Message> pageBean = new PageBean<Message>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 20;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = messageDao.findAllMessage().size();
		System.out.println("记录条数："+totalCount);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1) * limit;
		List<Message> list = messageDao.findPageMessage( begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public Message findByMessageId(Integer messageId){
		return messageDao.findByMessageId(messageId);
	}
	
	public void deleteMessage(Message message){
		messageDao.delete(message);
	}
	
	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
