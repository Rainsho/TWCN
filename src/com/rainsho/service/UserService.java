package com.rainsho.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

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

	public Users findUserByEmail(String email) {
		return dao.findByEmail(email).size() == 0 ? null : dao.findByEmail(
				email).get(0);
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

	public void updateUser(Users user) {
		dao.attachDirty(user);
	}

	public Users findById(int uid) {
		return dao.findById(uid);
	}

	public List<Users> searchUser(String keyword) {
		return dao.search(keyword);
	}

	public List<Tweets> searchTweet(String keyword) {
		return tdao.search(keyword);
	}
	
	public void upLOGINUSER() {
		Users user = (Users) ServletActionContext.getRequest().getSession().getAttribute("LOGIN_USER");
		user = dao.findById(user.getUid());
		// MEGER 加载属性
		Hibernate.initialize(user.getTweetses());
		Hibernate.initialize(user.getLikeses());
		Hibernate.initialize(user.getForwardses());
		Hibernate.initialize(user.getMentionses());
		Hibernate.initialize(user.getDirectmsgsesForHuid());
		Hibernate.initialize(user.getDirectmsgsesForSuid());
		Hibernate.initialize(user.getReplaysesForHuid());
		Hibernate.initialize(user.getReplaysesForSuid());
		Hibernate.initialize(user.getRelationshipsesForHuid());
		Hibernate.initialize(user.getRelationshipsesForSuid());
		ServletActionContext.getRequest().getSession()
				.setAttribute("LOGIN_USER", user);
	}

}