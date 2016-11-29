package com.rainsho.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

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
		upLOGINUSER(huser.getUid());
		return "none";
	}

	public String unfollow() {
		Users huser = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Users suser = service.findUserById(uid);
		if (huser != null && suser != null & huser.getUid() != suser.getUid()) {
			service.unfollow(huser, suser);
		}
		upLOGINUSER(huser.getUid());
		return "none";
	}

	public void upLOGINUSER(int id) {
		Users user = service.findUserById(id);
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