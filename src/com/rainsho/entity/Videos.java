package com.rainsho.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Videos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "videos", catalog = "twcn")
public class Videos implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer vid;
	private String vname;
	private String vpath;
	private Set<Tweets> tweetses = new HashSet<Tweets>(0);

	// Constructors

	/** default constructor */
	public Videos() {
	}

	/** minimal constructor */
	public Videos(String vname, String vpath) {
		this.vname = vname;
		this.vpath = vpath;
	}

	/** full constructor */
	public Videos(String vname, String vpath, Set<Tweets> tweetses) {
		this.vname = vname;
		this.vpath = vpath;
		this.tweetses = tweetses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "vid", unique = true, nullable = false)
	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	@Column(name = "vname", nullable = false, length = 200)
	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	@Column(name = "vpath", nullable = false, length = 200)
	public String getVpath() {
		return this.vpath;
	}

	public void setVpath(String vpath) {
		this.vpath = vpath;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "videos")
	public Set<Tweets> getTweetses() {
		return this.tweetses;
	}

	public void setTweetses(Set<Tweets> tweetses) {
		this.tweetses = tweetses;
	}

}