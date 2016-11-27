package com.rainsho.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rainsho.dao.DirectmsgsDAO;
import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Directmsgs;
import com.rainsho.entity.Users;

public class DirectmsgService {
	private DirectmsgsDAO dao;
	private UsersDAO udao;

	public DirectmsgsDAO getDao() {
		return dao;
	}

	public void setDao(DirectmsgsDAO dao) {
		this.dao = dao;
	}

	public UsersDAO getUdao() {
		return udao;
	}

	public void setUdao(UsersDAO udao) {
		this.udao = udao;
	}

	public Users findUserById(int id) {
		return udao.findById(id);
	}

	public List<Directmsgs> findDmByBoth(Users huser, Users suser) {
		return dao.findDmByBoth(huser, suser);
	}

	public void addDm(Directmsgs dm) {
		dao.save(dm);
	}

	public Directmsgs findDmById(int id) {
		return dao.findById(id);
	}

	public void delDm(Directmsgs dm) {
		dm.setDstate((short) 0);
		dao.attachDirty(dm);
	}

	public List<Directmsgs> findmap() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Directmsgs> list = new ArrayList<Directmsgs>();
		for (Directmsgs x : dao.findDmMap(user).values()) {
			list.add(x);
		}
		return list;
	}

}
