package com.rainsho.service;

import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Users;
import com.rainsho.util.StringUtil;

public class LoginService {
	private UsersDAO dao;

	public UsersDAO getDao() {
		return dao;
	}

	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}

	public void signup(Users user) {
		dao.save(user);
	}

	public boolean checkEmailExist(Users user) {
		if (dao.findByEmail(user.getEmail()).size() > 0) {
			return false;
		}
		return true;
	}

	// 由email生成用户名，若存在则加8位随机字符串
	public String generateUsername(Users user) {
		String username = user.getEmail().split("@")[0];
		if (dao.findByUsername(username).size() > 0) {
			username = username + "_" + StringUtil.randomString(8);
		}
		return username;
	}

	// 用户名或邮箱登录
	public Users loginCheck(Users user) {
		String u_or_e = user.getUsername();
		Users u1 = null;
		Users u2 = null;
		if (dao.findByUsername(u_or_e).size() != 0) {
			u1 = dao.findByUsername(u_or_e).get(0);
		}
		if (dao.findByEmail(u_or_e).size() != 0) {
			u2 = dao.findByEmail(u_or_e).get(0);
		}
		if (u1 != null && u1.getPassword().equals(user.getPassword())) {
			return u1;
		}
		if (u2 != null && u2.getPassword().equals(user.getPassword())) {
			return u2;
		}
		return null;
	}

}