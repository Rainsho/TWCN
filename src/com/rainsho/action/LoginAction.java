package com.rainsho.action;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Users;
import com.rainsho.service.LoginService;

public class LoginAction {
	private LoginService service;
	private Users user;
	private String login_msg;
	private String signup_msg;

	public LoginService getService() {
		return service;
	}

	public void setService(LoginService service) {
		this.service = service;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getSignup_msg() {
		return signup_msg;
	}

	public void setSignup_msg(String signup_msg) {
		this.signup_msg = signup_msg;
	}

	public String getLogin_msg() {
		return login_msg;
	}

	public void setLogin_msg(String login_msg) {
		this.login_msg = login_msg;
	}

	public String signup() {
		reset();
		Users user_new = new Users();
		if (service.checkEmailExist(user)) {
			user_new.setEmail(user.getEmail());
			user_new.setNickname(user.getNickname());
			user_new.setPassword(user.getPassword());
			user_new.setUsername(service.generateUsername(user));
			user_new.setRegisttime(new Timestamp(new Date().getTime()));
			user_new.setAvatar("img/twitter-icon.png");
			user_new.setUstate((short) 1);
			service.signup(user_new);
			ServletActionContext.getRequest().getSession()
					.setAttribute("LOGIN_USER", user_new);
			return "success";
		} else {
			signup_msg = "该邮件地址已被注册。";
			return "signup_fail";
		}
	}

	// 登录调用默认方法
	public String execute() {
		reset();
		user = service.loginCheck(user);
		if (user == null) {
			login_msg = "登录信息不匹配。请重新检查并重试。";
			return "login_fail";
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute("LOGIN_USER", user);
		return "success";
	}

	// 清理login页面信息
	private void reset() {
		login_msg = null;
		signup_msg = null;
	}
}