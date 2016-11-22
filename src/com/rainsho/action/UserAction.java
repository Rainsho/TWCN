package com.rainsho.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;
import com.rainsho.service.UserService;

public class UserAction {
	private UserService service;
	private Users user;
	private String u;
	private List<Tweets> list;

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

	// action name = user*
	public String page() {
		System.out.println("action:" + u);
		user = service.findUserByUsername(u);
		if (user == null) {
			return "no_such_user";
		}
		list = service.findTweetByUser(user);
		return "user_page";
	}

	public String index() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Users> users = service.findFollow(user);
		list = service.findIndexTweets(users);
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
		return "setting";
	}

}