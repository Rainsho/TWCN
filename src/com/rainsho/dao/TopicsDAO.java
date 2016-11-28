package com.rainsho.dao;

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

import com.rainsho.entity.Topics;

/**
 * A data access object (DAO) providing persistence and search support for
 * Topics entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rainsho.entity.Topics
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TopicsDAO {
	private static final Logger log = LoggerFactory.getLogger(TopicsDAO.class);
	// property constants
	public static final String TPCONTENT = "tpcontent";

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

	public void save(Topics transientInstance) {
		log.debug("saving Topics instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Topics persistentInstance) {
		log.debug("deleting Topics instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Topics findById(java.lang.Integer id) {
		log.debug("getting Topics instance with id: " + id);
		try {
			Topics instance = (Topics) getCurrentSession().get(
					"com.rainsho.entity.Topics", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Topics> findByExample(Topics instance) {
		log.debug("finding Topics instance by example");
		try {
			List<Topics> results = (List<Topics>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Topics")
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
		log.debug("finding Topics instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Topics as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Topics> findByTpcontent(Object tpcontent) {
		return findByProperty(TPCONTENT, tpcontent);
	}

	public List findAll() {
		log.debug("finding all Topics instances");
		try {
			String queryString = "from Topics";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Topics merge(Topics detachedInstance) {
		log.debug("merging Topics instance");
		try {
			Topics result = (Topics) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Topics instance) {
		log.debug("attaching dirty Topics instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Topics instance) {
		log.debug("attaching clean Topics instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TopicsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TopicsDAO) ctx.getBean("TopicsDAO");
	}

	// other function
	public Topics findTopic(String tpcontent) {
		List<Topics> list = findByTpcontent(tpcontent);
		if (list.size() > 0) {
			return list.get(0);
		}
		Topics topic = new Topics(tpcontent);
		save(topic);
		return topic;
	}

}