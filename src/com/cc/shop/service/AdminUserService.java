package com.cc.shop.service;

import org.springframework.transaction.annotation.Transactional;

import com.cc.shop.dao.AdminUserDao;
import com.cc.shop.pojo.AdminUser;



@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
