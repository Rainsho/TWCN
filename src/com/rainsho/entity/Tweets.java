package com.rainsho.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.rainsho.util.StringUtil;

/**
 * Tweets entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tweets", catalog = "twcn")
public class Tweets implements java.io.Serializable, Comparable<Tweets> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tid;
	private Users users;
	private Videos videos;
	private String tcontent;
	private Timestamp tweettime;
	private Short tstate;
	private Set<Replays> replayses = new HashSet<Replays>(0);
	private Set<Likes> likeses = new HashSet<Likes>(0);
	private Set<T2p> t2ps = new HashSet<T2p>(0);
	private Set<Mentions> mentionses = new HashSet<Mentions>(0);
	private Set<T2t> t2ts = new HashSet<T2t>(0);
	private Set<Forwards> forwardses = new HashSet<Forwards>(0);

	// Constructors

	/** default constructor */
	public Tweets() {
	}

	/** minimal constructor */
	public Tweets(Users users, String tcontent, Timestamp tweettime,
			Short tstate) {
		this.users = users;
		this.tcontent = tcontent;
		this.tweettime = tweettime;
		this.tstate = tstate;
	}

	/** full constructor */
	public Tweets(Users users, Videos videos, String tcontent,
			Timestamp tweettime, Short tstate, Set<Replays> replayses,
			Set<Likes> likeses, Set<T2p> t2ps, Set<Mentions> mentionses,
			Set<T2t> t2ts, Set<Forwards> forwardses) {
		this.users = users;
		this.videos = videos;
		this.tcontent = tcontent;
		this.tweettime = tweettime;
		this.tstate = tstate;
		this.replayses = replayses;
		this.likeses = likeses;
		this.t2ps = t2ps;
		this.mentionses = mentionses;
		this.t2ts = t2ts;
		this.forwardses = forwardses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "tid", unique = true, nullable = false)
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vid")
	public Videos getVideos() {
		return this.videos;
	}

	public void setVideos(Videos videos) {
		this.videos = videos;
	}

	@Column(name = "tcontent", nullable = false, length = 200)
	public String getTcontent() {
		return this.tcontent;
	}

	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}

	@Column(name = "tweettime", nullable = false, length = 19)
	public Timestamp getTweettime() {
		return this.tweettime;
	}

	public void setTweettime(Timestamp tweettime) {
		this.tweettime = tweettime;
	}

	@Column(name = "tstate", nullable = false)
	public Short getTstate() {
		return this.tstate;
	}

	public void setTstate(Short tstate) {
		this.tstate = tstate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tweets")
	// limit many!!!
	@Where(clause = "rstate > 0")
	public Set<Replays> getReplayses() {
		return this.replayses;
	}

	public void setReplayses(Set<Replays> replayses) {
		this.replayses = replayses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tweets")
	public Set<Likes> getLikeses() {
		return this.likeses;
	}

	public void setLikeses(Set<Likes> likeses) {
		this.likeses = likeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tweets")
	public Set<T2p> getT2ps() {
		return this.t2ps;
	}

	public void setT2ps(Set<T2p> t2ps) {
		this.t2ps = t2ps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tweets")
	public Set<Mentions> getMentionses() {
		return this.mentionses;
	}

	public void setMentionses(Set<Mentions> mentionses) {
		this.mentionses = mentionses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tweets")
	public Set<T2t> getT2ts() {
		return this.t2ts;
	}

	public void setT2ts(Set<T2t> t2ts) {
		this.t2ts = t2ts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tweets")
	@Where(clause = "fstate > 0")
	public Set<Forwards> getForwardses() {
		return this.forwardses;
	}

	public void setForwardses(Set<Forwards> forwardses) {
		this.forwardses = forwardses;
	}

	// other function
	public String fmtTime() {
		return StringUtil.fmtTime(this.tweettime);
	}

	public boolean isLike(Users user) {
		for (Likes x : likeses) {
			if (x.getUsers().getUid() == user.getUid()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Tweets o) {
		return this.getTweettime().after(o.getTweettime()) ? -1 : 1;
	}

	public List<Replays> repN2O() {
		List<Replays> list = new ArrayList<Replays>();
		list.addAll(this.getReplayses());
		Collections.sort(list);
		return list;
	}

	public List<Replays> repO2N() {
		List<Replays> list = new ArrayList<Replays>();
		list.addAll(this.getReplayses());
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

	public List<Forwards> fwdN2O() {
		List<Forwards> list = new ArrayList<Forwards>();
		list.addAll(this.getForwardses());
		Collections.sort(list);
		return list;
	}

	public List<Forwards> fwdO2N() {
		List<Forwards> list = new ArrayList<Forwards>();
		list.addAll(this.getForwardses());
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

}