package com.rainsho.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rainsho.dao.DirectmsgsDAO;
import com.rainsho.dao.LikesDAO;
import com.rainsho.dao.RelationshipsDAO;
import com.rainsho.dao.ReplaysDAO;
import com.rainsho.dao.TweetsDAO;
import com.rainsho.entity.Replays;
import com.rainsho.entity.Users;

public class NewPushService {
	private TweetsDAO tdao;
	private ReplaysDAO rdao;
	private LikesDAO ldao;
	private DirectmsgsDAO ddao;
	private RelationshipsDAO rsdao;

	public RelationshipsDAO getRsdao() {
		return rsdao;
	}

	public void setRsdao(RelationshipsDAO rsdao) {
		this.rsdao = rsdao;
	}

	public TweetsDAO getTdao() {
		return tdao;
	}

	public void setTdao(TweetsDAO tdao) {
		this.tdao = tdao;
	}

	public ReplaysDAO getRdao() {
		return rdao;
	}

	public void setRdao(ReplaysDAO rdao) {
		this.rdao = rdao;
	}

	public LikesDAO getLdao() {
		return ldao;
	}

	public void setLdao(LikesDAO ldao) {
		this.ldao = ldao;
	}

	public DirectmsgsDAO getDdao() {
		return ddao;
	}

	public void setDdao(DirectmsgsDAO ddao) {
		this.ddao = ddao;
	}

	public int getNewTweets() {
		Timestamp ts = (Timestamp) ServletActionContext.getRequest()
				.getSession().getAttribute("LAST_READ");
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Users> users = rsdao.findFollow(user);
		return tdao.findNewTweets(users, ts);
	}

	public int getNewReplays() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Replays> list = rdao.findReceivedReplays(user);
		int count = 0;
		for (Replays x : list) {
			// findReceivedReplays 以排除state=0的了
			if (x.getRstate() < 3) {
				count++;
			}
		}
		return count;
	}

	public int getNewLikes() {
		Timestamp ts = (Timestamp) ServletActionContext.getRequest()
				.getSession().getAttribute("LAST_READ_LIKE");
		if (ts == null) {
			ts = new Timestamp(new Date().getTime());
			ServletActionContext.getRequest().getSession()
					.setAttribute("LAST_READ_LIKE", ts);
		}
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		return ldao.findNewLikes(user, ts);
	}

	public void upLastReadLike() {
		Timestamp ts = new Timestamp(new Date().getTime());
		ServletActionContext.getRequest().getSession()
				.setAttribute("LAST_READ_LIKE", ts);
	}

	public List<Integer[]> getNewDMUserUid() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		return ddao.findNewDMUserUid(user);
	}

	public int getNewDMCount() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		return ddao.findNewDMCount(user);
	}

	public void upReadReplay() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Replays> list = rdao.findReceivedReplays(user);
		rdao.upReadReplay(list);
	}

	public void upReadDM(int suid) {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		ddao.upReadDM(user, suid);
	}

}