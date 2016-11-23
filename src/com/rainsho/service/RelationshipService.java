package com.rainsho.service;

import com.rainsho.dao.RelationshipsDAO;

public class RelationshipService {
	private RelationshipsDAO dao;

	public RelationshipsDAO getDao() {
		return dao;
	}

	public void setDao(RelationshipsDAO dao) {
		this.dao = dao;
	}

}
