package com.cc.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.cc.shop.dao.BillDao;
import com.cc.shop.pojo.BillItem;
import com.cc.shop.pojo.Order;
import com.cc.shop.pojo.Product;
import com.cc.shop.utils.PageBean;

public class BillService {

	private BillDao billDao;

	// 根据商品id查找是否有销售记录
	public BillItem findBillItemByPid(Integer pid) {
		BillItem billItem = billDao.findBillItemByPid(pid);
		return billItem;
	}

	// 当有订单支付提交时，更新销售记录表
	public void updateBillItem(BillItem billItem) {
		billDao.update(billItem);
	}

	public void saveBillItem(BillItem billItem) {
		billDao.save(billItem);
	}

	// 查找所有的销售信息
	public List findAllBillItem() {
		List<BillItem> list =new ArrayList<BillItem>();
		list = billDao.findAllBillItem();
		
		return list;
	}

	// 业务层查询所有订单方法
	public PageBean<BillItem> findAll(Integer page) {
		PageBean<BillItem> pageBean = new PageBean<BillItem>();
		// 设置参数
		pageBean.setPage(page);
		// 设置每页显示的记录数:
		int limit = 15;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount =findAllBillItem().size();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<BillItem> list = billDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public BillDao getBillDao() {
		return billDao;
	}

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

}
