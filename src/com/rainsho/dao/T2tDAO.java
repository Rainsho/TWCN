package com.rainsho.dao;

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

import com.rainsho.entity.T2t;
import com.rainsho.entity.Topics;
import com.rainsho.entity.Tweets;

/**
 * A data access object (DAO) providing persistence and search support for T2t
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rainsho.entity.T2t
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class T2tDAO {
	private static final Logger log = LoggerFactory.getLogger(T2tDAO.class);
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

	public void save(T2t transientInstance) {
		log.debug("saving T2t instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(T2t persistentInstance) {
		log.debug("deleting T2t instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public T2t findById(java.lang.Integer id) {
		log.debug("getting T2t instance with id: " + id);
		try {
			T2t instance = (T2t) getCurrentSession().get(
					"com.rainsho.entity.T2t", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<T2t> findByExample(T2t instance) {
		log.debug("finding T2t instance by example");
		try {
			List<T2t> results = (List<T2t>) getCurrentSession()
					.createCriteria("com.rainsho.entity.T2t")
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
		log.debug("finding T2t instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from T2t as model where model."
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
		log.debug("finding all T2t instances");
		try {
			String queryString = "from T2t";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public T2t merge(T2t detachedInstance) {
		log.debug("merging T2t instance");
		try {
			T2t result = (T2t) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(T2t instance) {
		log.debug("attaching dirty T2t instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(T2t instance) {
		log.debug("attaching clean T2t instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static T2tDAO getFromApplicationContext(ApplicationContext ctx) {
		return (T2tDAO) ctx.getBean("T2tDAO");
	}

	// other function
	public List<Tweets> findByTopic(Topics topic) {
		String hql = "select distinct t.tweets from T2t as t where t.topics=? and t.tweets.tstate>0";
		return getCurrentSession().createQuery(hql).setEntity(0, topic).list();
	}

	public List<Topics> upHotTopic() {
		String hql = "select t.topics from T2t as t group by t.topics order by count(*) desc";
		return getCurrentSession().createQuery(hql).setMaxResults(5).list();
	}

}