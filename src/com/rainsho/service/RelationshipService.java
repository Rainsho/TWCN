package com.rainsho.service;

import java.sql.Timestamp;
import java.util.Date;

import com.rainsho.dao.RelationshipsDAO;
import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Relationships;
import com.rainsho.entity.Users;

public class RelationshipService {
	private RelationshipsDAO dao;
	private UsersDAO udao;

	public RelationshipsDAO getDao() {
		return dao;
	}

	public void setDao(RelationshipsDAO dao) {
		this.dao = dao;
	}

	public UsersDAO getUdao() {
		return udao;
	}

	public void setUdao(UsersDAO udao) {
		this.udao = udao;
	}

	public void updateRs(Relationships rs) {
		dao.attachDirty(rs);
	}

	public void saveRs(Relationships rs) {
		dao.save(rs);
	}

	public Relationships findRsByBoth(Users huser, Users suser) {
		return dao.findByBoth(huser, suser);
	}

	public Users findUserById(int uid) {
		return udao.findById(uid);
	}

	public void follow(Users huser, Users suser) {
		Relationships rs = findRsByBoth(huser, suser);
		if (rs == null) {
			rs = new Relationships();
			rs.setUsersByHuid(huser);
			rs.setUsersBySuid(suser);
			rs.setFollowtime(new Timestamp(new Date().getTime()));
			rs.setRsstate((short) 1);
			saveRs(rs);
		} else {
			rs.setRsstate((short) 1);
			updateRs(rs);
		}
	}

	public void unfollow(Users huser, Users suser) {
		Relationships rs = findRsByBoth(huser, suser);
		rs.setRsstate((short) 0);
		updateRs(rs);
	}

}
