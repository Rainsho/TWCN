package com.rainsho.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.rainsho.entity.Forwards;
import com.rainsho.entity.Likes;
import com.rainsho.entity.Relationships;
import com.rainsho.entity.Replays;
import com.rainsho.entity.Topics;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;
import com.rainsho.service.UserService;

public class UserAction {
	private UserService service;
	private Users user;
	private String u;
	private List<Tweets> list;
	private Set<Integer> rs_set_suid;
	private String keyword;
	private List<Users> search_list;
	private List<Replays> receive_list;
	private List<Likes> like_list;
	private List<Users> recommend_user;
	private List<Forwards> forward_list;
	// topic=1 search=0
	private int type;
	private List<Topics> hot_topic;
	private Topics key_topic;
	// ajax page
	private int page = 1;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Topics getKey_topic() {
		return key_topic;
	}

	public void setKey_topic(Topics key_topic) {
		this.key_topic = key_topic;
	}

	public List<Topics> getHot_topic() {
		return hot_topic;
	}

	public void setHot_topic(List<Topics> hot_topic) {
		this.hot_topic = hot_topic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Forwards> getForward_list() {
		return forward_list;
	}

	public void setForward_list(List<Forwards> forward_list) {
		this.forward_list = forward_list;
	}

	public List<Users> getRecommend_user() {
		return recommend_user;
	}

	public void setRecommend_user(List<Users> recommend_user) {
		this.recommend_user = recommend_user;
	}

	public List<Likes> getLike_list() {
		return like_list;
	}

	public void setLike_list(List<Likes> like_list) {
		this.like_list = like_list;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Tweets> getList() {
		return list;
	}

	public void setList(List<Tweets> list) {
		this.list = list;
	}

	public Set<Integer> getRs_set_suid() {
		return rs_set_suid;
	}

	public void setRs_set_suid(Set<Integer> rs_set_suid) {
		this.rs_set_suid = rs_set_suid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Users> getSearch_list() {
		return search_list;
	}

	public void setSearch_list(List<Users> search_list) {
		this.search_list = search_list;
	}

	public List<Replays> getReceive_list() {
		return receive_list;
	}

	public void setReceive_list(List<Replays> receive_list) {
		this.receive_list = receive_list;
	}

	// action name = user*
	public String page() {
		user = service.findUserByUsername(u);
		if (user == null) {
			return "no_such_user";
		}
		list = service.findTweetByUser(user);
		List<Users> users = new ArrayList<Users>();
		users.add(user);
		forward_list = service.findForwards(users);
		// 合并tweet_list and forward_list
		forward_list = service.merge(forward_list, list);
		// 分页forward_lest
		list_section();
		recommend_user = service.findRecommendUsers();
		resetRs();
		return "user_page";
	}

	public String index() {
		// cookie 验证，只有uid数据
		service.upLOGINUSER();
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		List<Users> users = service.findFollow(user);
		list = service.findIndexTweets(users);
		forward_list = service.findForwards(users);
		// 合并tweet_list and forward_list
		forward_list = service.merge(forward_list, list);
		// 分页forward_lest
		list_section();
		recommend_user = service.findRecommendUsers();
		// login的时候upHot
		upHot();
		resetRs();
		// last read
		if (page == 1) {
			// ajax加载，不刷新last_read
			Timestamp ts = new Timestamp(new Date().getTime());
			ServletActionContext.getRequest().getSession()
					.setAttribute("LAST_READ", ts);
		}
		return "user_page";
	}

	public String logout() {
		user = null;
		ServletActionContext.getRequest().getSession()
				.removeAttribute("LOGIN_USER");
		// 清除cookie
		for (Cookie x : ServletActionContext.getRequest().getCookies()) {
			if (x.getName().equals("TWCN_USER")) {
				String CODE = x.getValue();
				x.setMaxAge(0);
				@SuppressWarnings("unchecked")
				Map<String, Integer> REMEMBERED = (Map<String, Integer>) ServletActionContext
						.getServletContext().getAttribute("REMEMBERED");
				if (REMEMBERED != null) {
					REMEMBERED.remove(CODE);
					ServletActionContext.getServletContext().setAttribute(
							"REMEMBERED", REMEMBERED);
				}
				break;
			}
		}
		return "logout";
	}

	public String update() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		resetRs();
		return "setting";
	}

	public String following() {
		user = service.findUserByUsername(u);
		if (user == null) {
			return "no_such_user";
		}
		resetRs();
		return "following_page";
	}

	public String search() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		search_list = new ArrayList<Users>();
		list = new ArrayList<Tweets>();
		if (keyword.equals("")) {
			keyword = "未输入关键字";
			return "search_page";
		}
		search_list = service.searchUser(keyword);
		list = service.searchTweet(keyword);
		resetRs();
		type = 0;
		return "search_page";
	}

	public void resetRs() {
		rs_set_suid = new HashSet<Integer>();
		for (Relationships x : ((Users) ServletActionContext.getRequest()
				.getSession().getAttribute("LOGIN_USER"))
				.getRelationshipsesForHuid()) {
			rs_set_suid.add(x.getUsersBySuid().getUid());
		}
	}

	public String flushlogin() {
		// update session
		service.upLOGINUSER();
		return "none";
	}

	public String ntf() {
		// 收到的评论来自回复和评论本人推特
		receive_list = service.findRRp();
		// 收到的点赞
		like_list = service.findRLike();
		// 发出的评论取自LOGIN_USER
		service.upLOGINUSER();
		return "ntf_page";
	}

	public String topic() {
		user = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("LOGIN_USER");
		list = new ArrayList<Tweets>();
		list = service.topicTweets(keyword);
		if (list.size() > 0) {
			key_topic = service.key_topic(keyword);
		}
		resetRs();
		type = 1;
		return "search_page";
	}

	public void upHot() {
		hot_topic = service.upHotTopic();
	}

	public void list_section() {
		// 30+20*x
		int max_index = forward_list.size();
		if (page == 1) {
			forward_list = forward_list.subList(0, Math.min(30, max_index));
		} else {
			if (page * 20 - 10 >= max_index) {
				forward_list = new ArrayList<Forwards>(0);
			} else {
				forward_list = forward_list.subList(page * 20 - 10,
						Math.min(page * 20 + 10, max_index));
			}
		}
		page = 1;
	}

}