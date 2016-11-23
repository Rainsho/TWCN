package com.rainsho.service;

import com.rainsho.dao.PicsDAO;
import com.rainsho.dao.T2pDAO;
import com.rainsho.dao.TweetsDAO;
import com.rainsho.dao.VideosDAO;
import com.rainsho.entity.Pics;
import com.rainsho.entity.T2p;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Videos;

public class MediaService {
	private PicsDAO pdao;
	private VideosDAO vdao;
	private TweetsDAO tdao;
	private T2pDAO tpdao;

	public PicsDAO getPdao() {
		return pdao;
	}

	public void setPdao(PicsDAO pdao) {
		this.pdao = pdao;
	}

	public VideosDAO getVdao() {
		return vdao;
	}

	public void setVdao(VideosDAO vdao) {
		this.vdao = vdao;
	}

	public TweetsDAO getTdao() {
		return tdao;
	}

	public void setTdao(TweetsDAO tdao) {
		this.tdao = tdao;
	}

	public T2pDAO getTpdao() {
		return tpdao;
	}

	public void setTpdao(T2pDAO tpdao) {
		this.tpdao = tpdao;
	}

	public void savaPic(Pics pic) {
		pdao.save(pic);
	}

	public void saveVideo(Videos video) {
		vdao.save(video);
	}

	public void saveTweet(Tweets tweet) {
		tdao.save(tweet);
	}

	public void saveT2p(T2p t2p) {
		tpdao.save(t2p);
	}

	public void deleteTweet(Tweets tweet) {
		tweet = tdao.findById(tweet.getTid());
		tweet.setTstate((short) 0);
		tdao.attachDirty(tweet);
	}

}
