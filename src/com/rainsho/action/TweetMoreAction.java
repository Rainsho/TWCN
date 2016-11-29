package com.rainsho.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Forwards;
import com.rainsho.entity.Replays;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;
import com.rainsho.service.TweetMoreService;

// action name = tm*
public class TweetMoreAction {
	private TweetMoreService service;
	private int tid;
	private int suid;
	private String rcontent;
	private int rid;
	// ajax test
	private Replays r;
	private List<Tweets> list;
	private Forwards forward;

	public Forwards getForward() {
		return forward;
	}

	public void setForward(Forwards forward) {
		this.forward = forward;
	}

	public List<Tweets> getList() {
		return list;
	}

	public void setList(List<Tweets> list) {
		this.list = list;
	}

	public Replays getR() {
		return r;
	}

	public void setR(Replays r) {
		this.r = r;
	}

	public TweetMoreService getService() {
		return service;
	}

	public void setService(TweetMoreService service) {
		this.service = service;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getSuid() {
		return suid;
	}

	public void setSuid(int suid) {
		this.suid = suid;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String like() {
		Tweets tweet = service.findTweetById(tid);
		service.tweetLike(tweet);
		return "none";
	}

	public String unlike() {
		Tweets tweet = service.findTweetById(tid);
		service.tweetUnlike(tweet);
		return "none";
	}

	public String addreplay() {
		Users huser = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Tweets tweet = service.findTweetById(tid);
		Replays replay = new Replays();
		replay.setUsersByHuid(huser);
		replay.setTweets(tweet);
		replay.setRcontent(rcontent);
		replay.setReplaytime(new Timestamp(new Date().getTime()));
		replay.setRstate((short) 1);
		if (suid != 0) {
			replay.setUsersBySuid(service.findUserById(suid));
			if (rcontent.indexOf("：") != -1) {
				rcontent = rcontent.substring(rcontent.indexOf("：") + 1);
				replay.setRcontent(rcontent);
			}
		}
		service.addReplay(replay);
		suid = 0;
		r = replay;
		return "addreplay";
	}

	public String delreplay() {
		Replays replay = service.findReplayById(rid);
		if (replay != null) {
			service.delReplay(replay);
		}
		rid = 0;
		return "none";
	}

	public String rptweet() {
		list = new ArrayList<Tweets>();
		list.add(service.findTweetById(tid));
		return "rptweet";
	}

	public String addforward() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		Tweets tweet = service.findTweetById(tid);
		Forwards forward_new = new Forwards();
		forward_new.setFcontent(forward.getFcontent());
		forward_new.setTweets(tweet);
		forward_new.setUsers(user);
		forward_new.setForwardtime(new Timestamp(new Date().getTime()));
		forward_new.setFstate((short) 1);
		service.addForward(forward_new);
		forward = forward_new;
		return "addforward";
	}

	public String delforward() {
		forward = service.findForwardById(forward.getFid());
		service.delForward(forward);
		return "none";
	}

}