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
 * Pics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pics", catalog = "twcn")
public class Pics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pid;
	private String pname;
	private String ppath;
	private Set<T2p> t2ps = new HashSet<T2p>(0);

	// Constructors

	/** default constructor */
	public Pics() {
	}

	/** minimal constructor */
	public Pics(String pname, String ppath) {
		this.pname = pname;
		this.ppath = ppath;
	}

	/** full constructor */
	public Pics(String pname, String ppath, Set<T2p> t2ps) {
		this.pname = pname;
		this.ppath = ppath;
		this.t2ps = t2ps;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pid", unique = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "pname", nullable = false, length = 200)
	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(name = "ppath", nullable = false, length = 200)
	public String getPpath() {
		return this.ppath;
	}

	public void setPpath(String ppath) {
		this.ppath = ppath;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pics")
	public Set<T2p> getT2ps() {
		return this.t2ps;
	}

	public void setT2ps(Set<T2p> t2ps) {
		this.t2ps = t2ps;
	}

}