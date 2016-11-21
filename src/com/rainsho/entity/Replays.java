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
 * Replays entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "replays", catalog = "twcn")
public class Replays implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rid;
	private Tweets tweets;
	private Users usersBySuid;
	private Users usersByHuid;
	private String rcontent;
	private Timestamp replaytime;
	private Short rstate;

	// Constructors

	/** default constructor */
	public Replays() {
	}

	/** full constructor */
	public Replays(Tweets tweets, Users usersBySuid, Users usersByHuid,
			String rcontent, Timestamp replaytime, Short rstate) {
		this.tweets = tweets;
		this.usersBySuid = usersBySuid;
		this.usersByHuid = usersByHuid;
		this.rcontent = rcontent;
		this.replaytime = replaytime;
		this.rstate = rstate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rid", unique = true, nullable = false)
	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
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
	@JoinColumn(name = "suid", nullable = false)
	public Users getUsersBySuid() {
		return this.usersBySuid;
	}

	public void setUsersBySuid(Users usersBySuid) {
		this.usersBySuid = usersBySuid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "huid", nullable = false)
	public Users getUsersByHuid() {
		return this.usersByHuid;
	}

	public void setUsersByHuid(Users usersByHuid) {
		this.usersByHuid = usersByHuid;
	}

	@Column(name = "rcontent", nullable = false, length = 1000)
	public String getRcontent() {
		return this.rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	@Column(name = "replaytime", nullable = false, length = 19)
	public Timestamp getReplaytime() {
		return this.replaytime;
	}

	public void setReplaytime(Timestamp replaytime) {
		this.replaytime = replaytime;
	}

	@Column(name = "rstate", nullable = false)
	public Short getRstate() {
		return this.rstate;
	}

	public void setRstate(Short rstate) {
		this.rstate = rstate;
	}

}