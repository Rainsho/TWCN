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
 * Directmsgs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "directmsgs", catalog = "twcn")
public class Directmsgs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer did;
	private Users usersBySuid;
	private Users usersByHuid;
	private String dcontent;
	private Timestamp msgtime;
	private Short dstate;

	// Constructors

	/** default constructor */
	public Directmsgs() {
	}

	/** full constructor */
	public Directmsgs(Users usersBySuid, Users usersByHuid, String dcontent,
			Timestamp msgtime, Short dstate) {
		this.usersBySuid = usersBySuid;
		this.usersByHuid = usersByHuid;
		this.dcontent = dcontent;
		this.msgtime = msgtime;
		this.dstate = dstate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "did", unique = true, nullable = false)
	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
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

	@Column(name = "dcontent", nullable = false, length = 1000)
	public String getDcontent() {
		return this.dcontent;
	}

	public void setDcontent(String dcontent) {
		this.dcontent = dcontent;
	}

	@Column(name = "msgtime", nullable = false, length = 19)
	public Timestamp getMsgtime() {
		return this.msgtime;
	}

	public void setMsgtime(Timestamp msgtime) {
		this.msgtime = msgtime;
	}

	@Column(name = "dstate", nullable = false)
	public Short getDstate() {
		return this.dstate;
	}

	public void setDstate(Short dstate) {
		this.dstate = dstate;
	}

}