package com.rainsho.action;

import java.util.HashMap;
import java.util.List;

import com.rainsho.service.NewPushService;

public class NewPushAction {
	private HashMap<String, Object> jsonResult = new HashMap<>();
	private NewPushService service;
	private int suid;

	public int getSuid() {
		return suid;
	}

	public void setSuid(int suid) {
		this.suid = suid;
	}

	public HashMap<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(HashMap<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public NewPushService getService() {
		return service;
	}

	public void setService(NewPushService service) {
		this.service = service;
	}

	public String check() {
		int newT = service.getNewTweets();
		jsonResult.put("newT", newT);
		int newR = service.getNewReplays();
		jsonResult.put("newR", newR);
		int newL = service.getNewLikes();
		jsonResult.put("newL", newL);
		int newD = service.getNewDMCount();
		jsonResult.put("newD", newD);
		List<Integer[]> newD_uid = service.getNewDMUserUid();
		jsonResult.put("newD_uid", newD_uid);
		return "success";
	}

	// update LAST_READ_LIKE
	public String uplrl() {
		service.upLastReadLike();
		return "none";
	}

	// update read_replay
	public String uprrp() {
		service.upReadReplay();
		return "none";
	}

	// update read_directmsg
	public String updm() {
		service.upReadDM(suid);
		return "none";
	}

}