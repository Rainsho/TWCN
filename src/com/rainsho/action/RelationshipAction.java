package com.rainsho.action;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Users;
import com.rainsho.service.RelationshipService;

public class RelationshipAction {

	private RelationshipService service;
	private int uid;

	public RelationshipService getService() {
		return service;
	}

	public void setService(RelationshipService service) {
		this.service = service;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String follow() {
		Users huser = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Users suser = service.findUserById(uid);
		if (huser != null && suser != null & huser.getUid() != suser.getUid()) {
			service.follow(huser, suser);
		}
		return "success";
	}

	public String unfollow() {
		Users huser = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Users suser = service.findUserById(uid);
		if (huser != null && suser != null & huser.getUid() != suser.getUid()) {
			service.unfollow(huser, suser);
		}
		return "success";
	}

}
