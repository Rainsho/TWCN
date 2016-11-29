package com.rainsho.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

import com.rainsho.dao.ForwardsDAO;
import com.rainsho.dao.LikesDAO;
import com.rainsho.dao.RelationshipsDAO;
import com.rainsho.dao.ReplaysDAO;
import com.rainsho.dao.T2tDAO;
import com.rainsho.dao.TopicsDAO;
import com.rainsho.dao.TweetsDAO;
import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Forwards;
import com.rainsho.entity.Likes;
import com.rainsho.entity.Replays;
import com.rainsho.entity.T2t;
import com.rainsho.entity.Topics;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

public class UserService {
	private UsersDAO dao;
	private TweetsDAO tdao;
	private RelationshipsDAO rdao;
	private ReplaysDAO rpdao;
	private LikesDAO ldao;
	private ForwardsDAO fdao;
	private TopicsDAO tpdao;
	private T2tDAO t2tdao;

	public T2tDAO getT2tdao() {
		return t2tdao;
	}

	public void setT2tdao(T2tDAO t2tdao) {
		this.t2tdao = t2tdao;
	}

	public TopicsDAO getTpdao() {
		return tpdao;
	}

	public void setTpdao(TopicsDAO tpdao) {
		this.tpdao = tpdao;
	}

	public ForwardsDAO getFdao() {
		return fdao;
	}

	public void setFdao(ForwardsDAO fdao) {
		this.fdao = fdao;
	}

	public LikesDAO getLdao() {
		return ldao;
	}

	public void setLdao(LikesDAO ldao) {
		this.ldao = ldao;
	}

	public ReplaysDAO getRpdao() {
		return rpdao;
	}

	public void setRpdao(ReplaysDAO rpdao) {
		this.rpdao = rpdao;
	}

	public UsersDAO getDao() {
		return dao;
	}

	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}

	public TweetsDAO getTdao() {
		return tdao;
	}

	public void setTdao(TweetsDAO tdao) {
		this.tdao = tdao;
	}

	public RelationshipsDAO getRdao() {
		return rdao;
	}

	public void setRdao(RelationshipsDAO rdao) {
		this.rdao = rdao;
	}

	public Users findUserByUsername(String username) {
		return dao.findByUsername(username).size() == 0 ? null : dao
				.findByUsername(username).get(0);
	}

	public Users findUserByEmail(String email) {
		return dao.findByEmail(email).size() == 0 ? null : dao.findByEmail(
				email).get(0);
	}

	public List<Tweets> findTweetByUser(Users user) {
		return tdao.findByUser(user);
	}

	public List<Tweets> findIndexTweets(List<Users> list) {
		return tdao.indexTweets(list);
	}

	public List<Users> findFollow(Users user) {
		return rdao.findFollow(user);
	}

	public void updateUser(Users user) {
		dao.attachDirty(user);
	}

	public Users findById(int uid) {
		return dao.findById(uid);
	}

	public List<Users> searchUser(String keyword) {
		return dao.search(keyword);
	}

	public List<Tweets> searchTweet(String keyword) {
		return tdao.search(keyword);
	}

	public void upLOGINUSER() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		user = dao.findById(user.getUid());
		// MEGER 加载属性
		Hibernate.initialize(user.getTweetses());
		Hibernate.initialize(user.getLikeses());
		Hibernate.initialize(user.getForwardses());
		Hibernate.initialize(user.getMentionses());
		Hibernate.initialize(user.getDirectmsgsesForHuid());
		Hibernate.initialize(user.getDirectmsgsesForSuid());
		Hibernate.initialize(user.getReplaysesForHuid());
		Hibernate.initialize(user.getReplaysesForSuid());
		Hibernate.initialize(user.getRelationshipsesForHuid());
		Hibernate.initialize(user.getRelationshipsesForSuid());
		ServletActionContext.getRequest().getSession()
				.setAttribute("LOGIN_USER", user);
	}

	public List<Replays> findRRp() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		return rpdao.findReceivedReplays(user);
	}

	public List<Likes> findRLike() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		return ldao.findReceivedLikes(user);
	}

	public List<Users> findRecommendUsers() {
		Users user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Users> l = rdao.findFollow(user);
		List<Users> list = dao.findRecommendUsers(l);
		if (list.size() > 3) {
			List<Users> r_list = new ArrayList<Users>();
			Random random = new Random();
			if (list.get(0).getUid() == 1) {
				r_list.add(list.get(0));
				list.remove(0);
				for (int i = 0; i < 2; i++) {
					int r = random.nextInt(list.size());
					r_list.add(list.get(r));
					list.remove(r);
				}
			} else {
				for (int i = 0; i < 3; i++) {
					int r = random.nextInt(list.size());
					r_list.add(list.get(r));
					list.remove(r);
				}
			}
			return r_list;
		} else {
			return list;
		}
	}

	public List<Forwards> findForwards(List<Users> list) {
		return fdao.findForwards(list);
	}

	public List<Forwards> merge(List<Forwards> forward_list, List<Tweets> list) {
		for (Tweets x : list) {
			Forwards f = new Forwards();
			f.setTweets(x);
			f.setForwardtime(x.getTweettime());
			forward_list.add(f);
		}
		Collections.sort(forward_list);
		return forward_list;
	}

	public void addTopic(Tweets tweet) {
		String str = tweet.getTcontent();
		Pattern p = Pattern.compile("#([^#]\\w+)");
		Matcher m = p.matcher(str);
		List<String> words = new ArrayList<String>();
		while (m.find()) {
			m.start();
			m.end();
			words.add(m.group(1));
		}
		for (String x : words) {
			Topics topic = tpdao.findTopic(x);
			T2t t2t = new T2t(tweet, topic);
			t2tdao.save(t2t);
			tweet.getT2ts().add(t2t);
			topic.getT2ts().add(t2t);
		}
	}

	public List<Tweets> topicTweets(String keyword) {
		List<Topics> list = tpdao.findByTpcontent(keyword);
		if (list.size() == 0) {
			return new ArrayList<Tweets>(0);
		}
		List<Tweets> list2 = t2tdao.findByTopic(list.get(0));
		Collections.sort(list2);
		return list2;
	}

	public List<Topics> upHotTopic() {
		return t2tdao.upHotTopic();
	}

	public Topics key_topic(String keyword) {
		return tpdao.findTopic(keyword);
	}

}