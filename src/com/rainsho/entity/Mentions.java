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
 * Mentions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mentions", catalog = "twcn")
public class Mentions implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mid;
	private Tweets tweets;
	private Users users;

	// Constructors

	/** default constructor */
	public Mentions() {
	}

	/** full constructor */
	public Mentions(Tweets tweets, Users users) {
		this.tweets = tweets;
		this.users = users;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "mid", unique = true, nullable = false)
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
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
	@JoinColumn(name = "uid", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}