package com.rainsho.action;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

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
		return "success";
	}

	public String unlike() {
		Tweets tweet = service.findTweetById(tid);
		service.tweetUnlike(tweet);
		return "success";
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
		return "success";
	}

}