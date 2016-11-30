package com.rainsho.action;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.rainsho.entity.Users;
import com.rainsho.service.LoginService;

public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginService service;
	private Users user;
	private String login_msg;
	private String signup_msg;
	private int remember_me;

	public int getRemember_me() {
		return remember_me;
	}

	public void setRemember_me(int remember_me) {
		this.remember_me = remember_me;
	}

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
		if (remember_me == 1) {
			service.remember_me();
		}
		return "success";
	}

	// 清理login页面信息
	private void reset() {
		login_msg = null;
		signup_msg = null;
	}

	public void validateSignup() {
		// scope=prototype 不用重置FieldErrors
		// this.clearFieldErrors();
		if (!user.getNickname().matches("^[\\w\\u4e00-\\u9fa5]{4,16}$")) {
			this.addFieldError("signup", "昵称为4-16位中英或数字");
			return;
		}
		if (!user.getEmail().matches("(\\w+)(@)([\\w-_\\.]+)(\\.\\w{2,})")) {
			this.addFieldError("signup", "邮箱非法，请再次确认");
			return;
		}
	}

}