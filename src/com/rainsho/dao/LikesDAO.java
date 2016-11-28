package com.rainsho.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.rainsho.entity.Likes;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for Likes
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rainsho.entity.Likes
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class LikesDAO {
	private static final Logger log = LoggerFactory.getLogger(LikesDAO.class);
	// property constants

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

	public void save(Likes transientInstance) {
		log.debug("saving Likes instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Likes persistentInstance) {
		log.debug("deleting Likes instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Likes findById(java.lang.Integer id) {
		log.debug("getting Likes instance with id: " + id);
		try {
			Likes instance = (Likes) getCurrentSession().get(
					"com.rainsho.entity.Likes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Likes> findByExample(Likes instance) {
		log.debug("finding Likes instance by example");
		try {
			List<Likes> results = (List<Likes>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Likes")
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
		log.debug("finding Likes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Likes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Likes instances");
		try {
			String queryString = "from Likes";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Likes merge(Likes detachedInstance) {
		log.debug("merging Likes instance");
		try {
			Likes result = (Likes) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Likes instance) {
		log.debug("attaching dirty Likes instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Likes instance) {
		log.debug("attaching clean Likes instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LikesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LikesDAO) ctx.getBean("LikesDAO");
	}

	// other function
	public Likes findByBoth(Tweets tweet, Users user) {
		String hql = "from Likes as l where l.tweets=? and l.users=?";
		List list = getCurrentSession().createQuery(hql).setEntity(0, tweet)
				.setEntity(1, user).list();
		if (list.size() > 0) {
			return (Likes) list.get(0);
		} else {
			return null;
		}
	}

	public List<Likes> findReceivedLikes(Users user) {
		String hql = "from Likes as l where l.tweets.users=?";
		return getCurrentSession().createQuery(hql).setEntity(0, user).list();
	}

}