package com.rainsho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * T2p entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t2p", catalog = "twcn")
public class T2p implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tpid;
	private Tweets tweets;
	private Pics pics;

	// Constructors

	/** default constructor */
	public T2p() {
	}

	/** full constructor */
	public T2p(Tweets tweets, Pics pics) {
		this.tweets = tweets;
		this.pics = pics;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "tpid", unique = true, nullable = false)
	public Integer getTpid() {
		return this.tpid;
	}

	public void setTpid(Integer tpid) {
		this.tpid = tpid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tid", nullable = false)
	public Tweets getTweets() {
		return this.tweets;
	}

	public void setTweets(Tweets tweets) {
		this.tweets = tweets;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid", nullable = false)
	public Pics getPics() {
		return this.pics;
	}

	public void setPics(Pics pics) {
		this.pics = pics;
	}

}