package com.rainsho.entity;

import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "twcn")
public class Users implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private String username;
	private String password;
	private String email;
	private String telphone;
	private String nickname;
	private Short gender;
	private Date birthday;
	private String city;
	private String avatar;
	private String bio;
	private Timestamp registtime;
	private Short ustate;
	private Set<Tweets> tweetses = new HashSet<Tweets>(0);
	private Set<Likes> likeses = new HashSet<Likes>(0);
	private Set<Directmsgs> directmsgsesForSuid = new HashSet<Directmsgs>(0);
	private Set<Forwards> forwardses = new HashSet<Forwards>(0);
	private Set<Directmsgs> directmsgsesForHuid = new HashSet<Directmsgs>(0);
	private Set<Replays> replaysesForSuid = new HashSet<Replays>(0);
	private Set<Replays> replaysesForHuid = new HashSet<Replays>(0);
	private Set<Relationships> relationshipsesForHuid = new HashSet<Relationships>(
			0);
	private Set<Mentions> mentionses = new HashSet<Mentions>(0);
	private Set<Relationships> relationshipsesForSuid = new HashSet<Relationships>(
			0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String username, String password, String email,
			String nickname, String avatar, Timestamp registtime, Short ustate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.avatar = avatar;
		this.registtime = registtime;
		this.ustate = ustate;
	}

	/** full constructor */
	public Users(String username, String password, String email,
			String telphone, String nickname, Short gender, Date birthday,
			String city, String avatar, String bio, Timestamp registtime,
			Short ustate, Set<Tweets> tweetses, Set<Likes> likeses,
			Set<Directmsgs> directmsgsesForSuid, Set<Forwards> forwardses,
			Set<Directmsgs> directmsgsesForHuid, Set<Replays> replaysesForSuid,
			Set<Replays> replaysesForHuid,
			Set<Relationships> relationshipsesForHuid,
			Set<Mentions> mentionses, Set<Relationships> relationshipsesForSuid) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.telphone = telphone;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.city = city;
		this.avatar = avatar;
		this.bio = bio;
		this.registtime = registtime;
		this.ustate = ustate;
		this.tweetses = tweetses;
		this.likeses = likeses;
		this.directmsgsesForSuid = directmsgsesForSuid;
		this.forwardses = forwardses;
		this.directmsgsesForHuid = directmsgsesForHuid;
		this.replaysesForSuid = replaysesForSuid;
		this.replaysesForHuid = replaysesForHuid;
		this.relationshipsesForHuid = relationshipsesForHuid;
		this.mentionses = mentionses;
		this.relationshipsesForSuid = relationshipsesForSuid;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "uid", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telphone", length = 50)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "nickname", nullable = false, length = 50)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "gender")
	public Short getGender() {
		return this.gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "city", length = 200)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "avatar", nullable = false, length = 200)
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "bio", length = 1000)
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Column(name = "registtime", nullable = false, length = 19)
	public Timestamp getRegisttime() {
		return this.registtime;
	}

	public void setRegisttime(Timestamp registtime) {
		this.registtime = registtime;
	}

	@Column(name = "ustate", nullable = false)
	public Short getUstate() {
		return this.ustate;
	}

	public void setUstate(Short ustate) {
		this.ustate = ustate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Tweets> getTweetses() {
		return this.tweetses;
	}

	public void setTweetses(Set<Tweets> tweetses) {
		this.tweetses = tweetses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Likes> getLikeses() {
		return this.likeses;
	}

	public void setLikeses(Set<Likes> likeses) {
		this.likeses = likeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersBySuid")
	public Set<Directmsgs> getDirectmsgsesForSuid() {
		return this.directmsgsesForSuid;
	}

	public void setDirectmsgsesForSuid(Set<Directmsgs> directmsgsesForSuid) {
		this.directmsgsesForSuid = directmsgsesForSuid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Forwards> getForwardses() {
		return this.forwardses;
	}

	public void setForwardses(Set<Forwards> forwardses) {
		this.forwardses = forwardses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByHuid")
	public Set<Directmsgs> getDirectmsgsesForHuid() {
		return this.directmsgsesForHuid;
	}

	public void setDirectmsgsesForHuid(Set<Directmsgs> directmsgsesForHuid) {
		this.directmsgsesForHuid = directmsgsesForHuid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersBySuid")
	public Set<Replays> getReplaysesForSuid() {
		return this.replaysesForSuid;
	}

	public void setReplaysesForSuid(Set<Replays> replaysesForSuid) {
		this.replaysesForSuid = replaysesForSuid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByHuid")
	// limit many!!!
	@Where(clause = "rstate > 0")
	public Set<Replays> getReplaysesForHuid() {
		return this.replaysesForHuid;
	}

	public void setReplaysesForHuid(Set<Replays> replaysesForHuid) {
		this.replaysesForHuid = replaysesForHuid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByHuid")
	// limit many!!!
	@Where(clause = "rsstate = 1")
	public Set<Relationships> getRelationshipsesForHuid() {
		return this.relationshipsesForHuid;
	}

	public void setRelationshipsesForHuid(
			Set<Relationships> relationshipsesForHuid) {
		this.relationshipsesForHuid = relationshipsesForHuid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Mentions> getMentionses() {
		return this.mentionses;
	}

	public void setMentionses(Set<Mentions> mentionses) {
		this.mentionses = mentionses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersBySuid")
	public Set<Relationships> getRelationshipsesForSuid() {
		return this.relationshipsesForSuid;
	}

	public void setRelationshipsesForSuid(
			Set<Relationships> relationshipsesForSuid) {
		this.relationshipsesForSuid = relationshipsesForSuid;
	}

	// advanced function
	public boolean hasRelation(Users request_user) {
		for (Relationships x : relationshipsesForHuid) {
			if (x.getUsersBySuid().getUid() == request_user.getUid()) {
				return true;
			}
		}
		return false;
	}

}