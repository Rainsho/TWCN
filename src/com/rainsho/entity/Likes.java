package com.rainsho.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rainsho.util.StringUtil;

/**
 * Likes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "likes", catalog = "twcn")
public class Likes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer lid;
	private Tweets tweets;
	private Users users;
	private Timestamp liketime;

	// Constructors

	/** default constructor */
	public Likes() {
	}

	/** full constructor */
	public Likes(Tweets tweets, Users users, Timestamp liketime) {
		this.tweets = tweets;
		this.users = users;
		this.liketime = liketime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "lid", unique = true, nullable = false)
	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
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

	@Column(name = "liketime", nullable = false, length = 19)
	public Timestamp getLiketime() {
		return this.liketime;
	}

	public void setLiketime(Timestamp liketime) {
		this.liketime = liketime;
	}

	// other function
	public String fmtTime() {
		return StringUtil.fmtTime(this.liketime);
	}

}