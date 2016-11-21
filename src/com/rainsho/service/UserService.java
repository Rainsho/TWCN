package com.rainsho.service;

import java.util.List;

import com.rainsho.dao.TweetsDAO;
import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

public class UserService {
	private UsersDAO dao;
	private TweetsDAO tdao;

	public UsersDAO getDao() {
		return dao;
	}

	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}

	public TweetsDAO getTdao() {
		return tdao;
	}

	public void setTdao(TweetsDAO tdao) {
		this.tdao = tdao;
	}

	public Users findUserByUsername(String username) {
		return dao.findByUsername(username).size() == 0 ? null : dao
				.findByUsername(username).get(0);
	}

	public List<Tweets> findTweetByUser(Users user) {
		return tdao.findByUser(user);
	}

}