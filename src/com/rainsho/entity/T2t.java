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
 * T2t entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t2t", catalog = "twcn")
public class T2t implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ttid;
	private Tweets tweets;
	private Topics topics;

	// Constructors

	/** default constructor */
	public T2t() {
	}

	/** full constructor */
	public T2t(Tweets tweets, Topics topics) {
		this.tweets = tweets;
		this.topics = topics;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ttid", unique = true, nullable = false)
	public Integer getTtid() {
		return this.ttid;
	}

	public void setTtid(Integer ttid) {
		this.ttid = ttid;
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
	@JoinColumn(name = "tpid", nullable = false)
	public Topics getTopics() {
		return this.topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

}