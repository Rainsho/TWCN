package com.rainsho.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tweets entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rainsho.entity.Tweets
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TweetsDAO {
	private static final Logger log = LoggerFactory.getLogger(TweetsDAO.class);
	// property constants
	public static final String TCONTENT = "tcontent";
	public static final String TSTATE = "tstate";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Tweets transientInstance) {
		log.debug("saving Tweets instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tweets persistentInstance) {
		log.debug("deleting Tweets instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tweets findById(java.lang.Integer id) {
		log.debug("getting Tweets instance with id: " + id);
		try {
			Tweets instance = (Tweets) getCurrentSession().get(
					"com.rainsho.entity.Tweets", id);
			// 逻辑删除
			if (instance.getTstate() == 0) {
				return null;
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tweets> findByExample(Tweets instance) {
		log.debug("finding Tweets instance by example");
		try {
			List<Tweets> results = (List<Tweets>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Tweets")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tweets instance with property: " + propertyName
				+ ", value: " + value);
		try {
			// 逻辑删除
			String queryString = "from Tweets as model where model."
					+ propertyName + "= ? and model.tstate = 1";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Tweets> findByTcontent(Object tcontent) {
		return findByProperty(TCONTENT, tcontent);
	}

	public List<Tweets> findByTstate(Object tstate) {
		return findByProperty(TSTATE, tstate);
	}

	public List findAll() {
		log.debug("finding all Tweets instances");
		try {
			// 逻辑删除
			String queryString = "from Tweets as model where model.tstate = 1";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tweets merge(Tweets detachedInstance) {
		log.debug("merging Tweets instance");
		try {
			Tweets result = (Tweets) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tweets instance) {
		log.debug("attaching dirty Tweets instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tweets instance) {
		log.debug("attaching clean Tweets instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TweetsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TweetsDAO) ctx.getBean("TweetsDAO");
	}

	// additional function
	public List<Tweets> findByUser(Users user) {
		String hql = "from Tweets as t where t.users=? and t.tstate = 1 order by t.tweettime desc";
		Query query = getCurrentSession().createQuery(hql);
		return query.setEntity(0, user).list();
	}

	public List<Tweets> indexTweets(List<Users> list) {
		String hql = "from Tweets as t where t.users in (:list) and t.tstate = 1 order by t.tweettime desc";
		Query query = getCurrentSession().createQuery(hql).setParameterList(
				"list", list);
		return query.list();
	}

	public List<Tweets> search(String keyword) {
		String hql = "from Tweets as t where t.tcontent like ? and t.tstate=1";
		return getCurrentSession().createQuery(hql)
				.setString(0, "%" + keyword + "%").list();
	}
	
}