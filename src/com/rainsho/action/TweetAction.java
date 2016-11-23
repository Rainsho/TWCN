package com.rainsho.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Pics;
import com.rainsho.entity.T2p;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;
import com.rainsho.entity.Videos;
import com.rainsho.service.MediaService;
import com.rainsho.util.StringUtil;

public class TweetAction {
	private HashMap<String, Object> jsonResult;
	private String[] file;
	private String[] fileFileName;
	private String[] fileContentType;
	private MediaService mService;
	private Tweets tweet;

	// internal
	private String path;
	private List<Pics> pics = new ArrayList<Pics>(0);
	private List<Videos> videos = new ArrayList<Videos>(0);

	public HashMap<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(HashMap<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String[] getFile() {
		return file;
	}

	public void setFile(String[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public MediaService getmService() {
		return mService;
	}

	public void setmService(MediaService mService) {
		this.mService = mService;
	}

	public Tweets getTweet() {
		return tweet;
	}

	public void setTweet(Tweets tweet) {
		this.tweet = tweet;
	}

	public String uploadmedia() {
		// 清理集合
		resets();
		path = ServletActionContext.getServletContext().getRealPath("upload");
		for (int i = 0; i < file.length; i++) {
			doUpload(i);
		}
		// 清理传参
		reset();
		ServletActionContext.getRequest().getSession()
				.setAttribute("preparePics", pics);
		ServletActionContext.getRequest().getSession()
				.setAttribute("prepareVideos", videos);
		return "success";
	}

	public void doUpload(int i) {
		try {
			InputStream in = new FileInputStream(file[i]);
			String fn = StringUtil.randomString(8) + (new Date().getTime());
			String ext = fileFileName[i].substring(fileFileName[i]
					.lastIndexOf("."));
			File uploadFile = new File(path, fn + ext);
			OutputStream out = new FileOutputStream(uploadFile);
			byte[] b = new byte[1024];
			int length;
			while ((length = in.read(b)) > 0) {
				out.write(b, 0, length);
			}
			in.close();
			out.close();
			// save to database
			if (ext.equals(".mp4")) {
				Videos video = new Videos(fileFileName[i], "upload" + "/" + fn
						+ ext);
				mService.saveVideo(video);
				videos.add(video);
			} else {
				Pics pic = new Pics(fileFileName[i], "upload" + "/" + fn + ext);
				mService.savaPic(pic);
				pics.add(pic);
			}
			// js 测试时间
			Thread.sleep(800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String tweet() {
		List<Pics> pics = (List<Pics>) ServletActionContext.getRequest()
				.getSession().getAttribute("preparePics");
		List<Videos> videos = (List<Videos>) ServletActionContext.getRequest()
				.getSession().getAttribute("prepareVideos");
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		tweet.setUsers(user);
		tweet.setTweettime(new Timestamp(new Date().getTime()));
		tweet.setTstate((short) 1);
		if (videos != null && videos.size() > 0) {
			tweet.setVideos(videos.get(0));
		}
		mService.saveTweet(tweet);
		if (pics != null) {
			for (Pics x : pics) {
				T2p t2p = new T2p(tweet, x);
				mService.saveT2p(t2p);
			}
		}
		// 清理集合
		resets();
		return "success";
	}

	public void reset() {
		file = null;
		fileFileName = null;
		fileContentType = null;

	}

	public void resets() {
		pics = new ArrayList<Pics>(0);
		videos = new ArrayList<Videos>(0);
		ServletActionContext.getRequest().getSession()
				.removeAttribute("preparePics");
		ServletActionContext.getRequest().getSession()
				.removeAttribute("prepareVideos");
	}

	public String delete() {
		mService.deleteTweet(tweet);
		return "success";
	}

}
