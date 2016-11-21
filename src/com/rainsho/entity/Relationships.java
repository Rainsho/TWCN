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
 * Relationships entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "relationships", catalog = "twcn")
public class Relationships implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rsid;
	private Users usersBySuid;
	private Users usersByHuid;
	private Timestamp followtime;
	private Short rsstate;

	// Constructors

	/** default constructor */
	public Relationships() {
	}

	/** full constructor */
	public Relationships(Users usersBySuid, Users usersByHuid,
			Timestamp followtime, Short rsstate) {
		this.usersBySuid = usersBySuid;
		this.usersByHuid = usersByHuid;
		this.followtime = followtime;
		this.rsstate = rsstate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rsid", unique = true, nullable = false)
	public Integer getRsid() {
		return this.rsid;
	}

	public void setRsid(Integer rsid) {
		this.rsid = rsid;
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

	@Column(name = "followtime", nullable = false, length = 19)
	public Timestamp getFollowtime() {
		return this.followtime;
	}

	public void setFollowtime(Timestamp followtime) {
		this.followtime = followtime;
	}

	@Column(name = "rsstate", nullable = false)
	public Short getRsstate() {
		return this.rsstate;
	}

	public void setRsstate(Short rsstate) {
		this.rsstate = rsstate;
	}

}