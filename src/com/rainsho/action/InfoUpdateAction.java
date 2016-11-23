package com.rainsho.action;

import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Users;
import com.rainsho.service.UserService;

public class InfoUpdateAction {
	private UserService uService;
	private HashMap<String, Object> jsonResult;
	private String tocheck;
	private Users user;
	private int type;

	public UserService getuService() {
		return uService;
	}

	public void setuService(UserService uService) {
		this.uService = uService;
	}

	public HashMap<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(HashMap<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getTocheck() {
		return tocheck;
	}

	public void setTocheck(String tocheck) {
		this.tocheck = tocheck;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String isUsernameExist() {
		jsonResult = new HashMap<String, Object>();
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		// session中user信息不完整，lazy加载模式导致部分数据不存在！
		// user = uService.findUserByUsername(user.getUsername());
		jsonResult.put("origininfo", user.getUsername());
		if (uService.findUserByUsername(tocheck) == null) {
			jsonResult.put("checkresult", false);
		} else {
			jsonResult.put("checkresult", true);
		}
		return "success";
	}

	public String isEmailExist() {
		jsonResult = new HashMap<String, Object>();
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		// user = uService.findUserByUsername(user.getUsername());
		jsonResult.put("origininfo", user.getEmail());
		if (uService.findUserByEmail(tocheck) == null) {
			jsonResult.put("checkresult", false);
		} else {
			jsonResult.put("checkresult", true);
		}
		return "success";
	}

	public String checkPswd() {
		jsonResult = new HashMap<String, Object>();
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		if (user.getPassword().equals(tocheck)) {
			jsonResult.put("checkresult", true);
		} else {
			jsonResult.put("checkresult", false);
		}
		return "success";
	}

	public String updateUser() {
		Users user_new = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		if (type == 1) {
			user_new.setUsername(user.getUsername());
			user_new.setEmail(user.getEmail());
		}
		if (type == 2) {
			user_new.setPassword(user.getPassword());
		}
		uService.updateUser(user_new);
		jsonResult = new HashMap<String, Object>();
		jsonResult.put("updateresult", true);
		return "success";
	}

}
