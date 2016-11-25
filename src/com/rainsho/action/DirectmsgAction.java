package com.rainsho.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rainsho.entity.Directmsgs;
import com.rainsho.entity.Users;
import com.rainsho.service.DirectmsgService;

public class DirectmsgAction {
	private DirectmsgService service;
	private int huid;
	private int suid;
	private Users huser;
	private Users suser;
	private List<Directmsgs> dmlist;
	private Directmsgs dm;

	public DirectmsgService getService() {
		return service;
	}

	public void setService(DirectmsgService service) {
		this.service = service;
	}

	public int getHuid() {
		return huid;
	}

	public void setHuid(int huid) {
		this.huid = huid;
	}

	public int getSuid() {
		return suid;
	}

	public void setSuid(int suid) {
		this.suid = suid;
	}

	public List<Directmsgs> getDmlist() {
		return dmlist;
	}

	public void setDmlist(List<Directmsgs> dmlist) {
		this.dmlist = dmlist;
	}

	public Users getHuser() {
		return huser;
	}

	public void setHuser(Users huser) {
		this.huser = huser;
	}

	public Users getSuser() {
		return suser;
	}

	public void setSuser(Users suser) {
		this.suser = suser;
	}

	public Directmsgs getDm() {
		return dm;
	}

	public void setDm(Directmsgs dm) {
		this.dm = dm;
	}

	public String toshow() {
		dmlist = new ArrayList<>();
		huser = service.findUserById(huid);
		suser = service.findUserById(suid);
		if (huser != null && suser != null && huid != suid) {
			dmlist = service.findDmByBoth(huser, suser);
		}
		return "find_dm";
	}

	public String toadd() {
		huser = service.findUserById(huid);
		suser = service.findUserById(suid);
		dm.setUsersByHuid(huser);
		dm.setUsersBySuid(suser);
		dm.setMsgtime(new Timestamp(new Date().getTime()));
		dm.setDstate((short) 1);
		service.addDm(dm);
		dmlist = new ArrayList<>();
		dmlist.add(dm);
		return "add_dm";
	}

	public String todel() {
		dm = service.findDmById(dm.getDid());
		service.delDm(dm);
		return "success";
	}
}
