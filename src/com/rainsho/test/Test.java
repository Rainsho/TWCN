package com.rainsho.test;

import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Users;

public class Test {

	private UsersDAO dao;
	private Users user;

	public UsersDAO getDao() {
		return dao;
	}

	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	// test function
	public String execute() {
		user = dao.findById(1);
		return "success";
	}
	
	public static void main(String[] args) {
	}
}
