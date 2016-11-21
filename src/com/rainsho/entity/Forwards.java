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

/**
 * Forwards entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "forwards", catalog = "twcn")
public class Forwards implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fid;
	private Tweets tweets;
	private Users users;
	private String fcontent;
	private Timestamp forwardtime;
	private Short fstate;

	// Constructors

	/** default constructor */
	public Forwards() {
	}

	/** full constructor */
	public Forwards(Tweets tweets, Users users, String fcontent,
			Timestamp forwardtime, Short fstate) {
		this.tweets = tweets;
		this.users = users;
		this.fcontent = fcontent;
		this.forwardtime = forwardtime;
		this.fstate = fstate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "fid", unique = true, nullable = false)
	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
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

	@Column(name = "fcontent", nullable = false, length = 200)
	public String getFcontent() {
		return this.fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	@Column(name = "forwardtime", nullable = false, length = 19)
	public Timestamp getForwardtime() {
		return this.forwardtime;
	}

	public void setForwardtime(Timestamp forwardtime) {
		this.forwardtime = forwardtime;
	}

	@Column(name = "fstate", nullable = false)
	public Short getFstate() {
		return this.fstate;
	}

	public void setFstate(Short fstate) {
		this.fstate = fstate;
	}

}