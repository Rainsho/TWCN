package com.rainsho.service;

import java.util.List;

import com.rainsho.dao.RelationshipsDAO;
import com.rainsho.dao.TweetsDAO;
import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

public class UserService {
	private UsersDAO dao;
	private TweetsDAO tdao;
	private RelationshipsDAO rdao;

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

	public RelationshipsDAO getRdao() {
		return rdao;
	}

	public void setRdao(RelationshipsDAO rdao) {
		this.rdao = rdao;
	}

	public Users findUserByUsername(String username) {
		return dao.findByUsername(username).size() == 0 ? null : dao
				.findByUsername(username).get(0);
	}

	public List<Tweets> findTweetByUser(Users user) {
		return tdao.findByUser(user);
	}
	
	public List<Tweets> findIndexTweets(List<Users> list) {
		return tdao.indexTweets(list);
	}
	
	public List<Users> findFollow(Users user) {
		return rdao.findFollow(user);
	}

}