package com.rainsho.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.rainsho.dao.LikesDAO;
import com.rainsho.dao.ReplaysDAO;
import com.rainsho.dao.TweetsDAO;
import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Likes;
import com.rainsho.entity.Replays;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

public class TweetMoreService {
	private TweetsDAO tdao;
	private LikesDAO ldao;
	private ReplaysDAO rdao;
	private UsersDAO udao;

	public TweetsDAO getTdao() {
		return tdao;
	}

	public void setTdao(TweetsDAO tdao) {
		this.tdao = tdao;
	}

	public LikesDAO getLdao() {
		return ldao;
	}

	public void setLdao(LikesDAO ldao) {
		this.ldao = ldao;
	}

	public ReplaysDAO getRdao() {
		return rdao;
	}

	public void setRdao(ReplaysDAO rdao) {
		this.rdao = rdao;
	}

	public UsersDAO getUdao() {
		return udao;
	}

	public void setUdao(UsersDAO udao) {
		this.udao = udao;
	}

	public Tweets findTweetById(int id) {
		return tdao.findById(id);
	}

	public Likes findLikeById(int id) {
		return ldao.findById(id);
	}

	public Replays findReplayById(int id) {
		return rdao.findById(id);
	}

	public Users findUserById(int id) {
		return udao.findById(id);
	}

	public void tweetLike(Tweets tweet) {
		if (tweet == null) {
			return;
		}
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Likes like = new Likes(tweet, user, new Timestamp(new Date().getTime()));
		ldao.save(like);
	}

	public void tweetUnlike(Tweets tweet) {
		if (tweet == null) {
			return;
		}
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Likes like = ldao.findByBoth(tweet, user);
		ldao.delete(like);
	}

	public void addReplay(Replays replay) {
		rdao.save(replay);
	}

	public void delReplay(Replays replay) {
		replay.setRstate((short) 0);
		rdao.attachDirty(replay);
	}

}