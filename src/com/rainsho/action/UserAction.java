package com.rainsho.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Likes;
import com.rainsho.entity.Relationships;
import com.rainsho.entity.Replays;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;
import com.rainsho.service.UserService;

public class UserAction {
	private UserService service;
	private Users user;
	private String u;
	private List<Tweets> list;
	private Set<Integer> rs_set_suid;
	private String keyword;
	private List<Users> search_list;
	private List<Replays> receive_list;
	private List<Likes> like_list;
	private List<Users> recommend_user;

	public List<Users> getRecommend_user() {
		return recommend_user;
	}

	public void setRecommend_user(List<Users> recommend_user) {
		this.recommend_user = recommend_user;
	}

	public List<Likes> getLike_list() {
		return like_list;
	}

	public void setLike_list(List<Likes> like_list) {
		this.like_list = like_list;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Tweets> getList() {
		return list;
	}

	public void setList(List<Tweets> list) {
		this.list = list;
	}

	public Set<Integer> getRs_set_suid() {
		return rs_set_suid;
	}

	public void setRs_set_suid(Set<Integer> rs_set_suid) {
		this.rs_set_suid = rs_set_suid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Users> getSearch_list() {
		return search_list;
	}

	public void setSearch_list(List<Users> search_list) {
		this.search_list = search_list;
	}

	public List<Replays> getReceive_list() {
		return receive_list;
	}

	public void setReceive_list(List<Replays> receive_list) {
		this.receive_list = receive_list;
	}

	// action name = user*
	public String page() {
		user = service.findUserByUsername(u);
		if (user == null) {
			return "no_such_user";
		}
		list = service.findTweetByUser(user);
		recommend_user = service.findRecommendUsers();
		resetRs();
		return "user_page";
	}

	public String index() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Users> users = service.findFollow(user);
		list = service.findIndexTweets(users);
		recommend_user = service.findRecommendUsers();
		resetRs();
		return "user_page";
	}

	public String logout() {
		user = null;
		ServletActionContext.getRequest().getSession()
				.removeAttribute("LOGIN_USER");
		return "logout";
	}

	public String update() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		resetRs();
		return "setting";
	}

	public String following() {
		user = service.findUserByUsername(u);
		if (user == null) {
			return "no_such_user";
		}
		resetRs();
		return "following_page";
	}

	public String search() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		search_list = new ArrayList<Users>();
		list = new ArrayList<Tweets>();
		if (keyword.equals("")) {
			keyword = "未输入关键字";
			return "search_page";
		}
		search_list = service.searchUser(keyword);
		list = service.searchTweet(keyword);
		resetRs();
		return "search_page";
	}

	public void resetRs() {
		rs_set_suid = new HashSet<Integer>();
		for (Relationships x : ((Users) ServletActionContext.getRequest()
				.getSession().getAttribute("LOGIN_USER"))
				.getRelationshipsesForHuid()) {
			rs_set_suid.add(x.getUsersBySuid().getUid());
		}
	}

	public String flushlogin() {
		// update session
		service.upLOGINUSER();
		return "flush_session";
	}

	public String ntf() {
		// 收到的评论来自回复和评论本人推特
		receive_list = service.findRRp();
		// 收到的点赞
		like_list = service.findRLike();
		// 发出的评论取自LOGIN_USER
		service.upLOGINUSER();
		return "ntf_page";
	}

}