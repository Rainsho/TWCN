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
 * Topics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topics", catalog = "twcn")
public class Topics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tpid;
	private String tpcontent;
	private Set<T2t> t2ts = new HashSet<T2t>(0);

	// Constructors

	/** default constructor */
	public Topics() {
	}

	/** minimal constructor */
	public Topics(String tpcontent) {
		this.tpcontent = tpcontent;
	}

	/** full constructor */
	public Topics(String tpcontent, Set<T2t> t2ts) {
		this.tpcontent = tpcontent;
		this.t2ts = t2ts;
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

	@Column(name = "tpcontent", nullable = false, length = 200)
	public String getTpcontent() {
		return this.tpcontent;
	}

	public void setTpcontent(String tpcontent) {
		this.tpcontent = tpcontent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topics")
	public Set<T2t> getT2ts() {
		return this.t2ts;
	}

	public void setT2ts(Set<T2t> t2ts) {
		this.t2ts = t2ts;
	}

}