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

import com.rainsho.entity.T2p;

/**
 * A data access object (DAO) providing persistence and search support for T2p
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rainsho.entity.T2p
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class T2pDAO {
	private static final Logger log = LoggerFactory.getLogger(T2pDAO.class);
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

	public void save(T2p transientInstance) {
		log.debug("saving T2p instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(T2p persistentInstance) {
		log.debug("deleting T2p instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public T2p findById(java.lang.Integer id) {
		log.debug("getting T2p instance with id: " + id);
		try {
			T2p instance = (T2p) getCurrentSession().get("com.rainsho.entity.T2p",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<T2p> findByExample(T2p instance) {
		log.debug("finding T2p instance by example");
		try {
			List<T2p> results = (List<T2p>) getCurrentSession()
					.createCriteria("com.rainsho.entity.T2p")
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
		log.debug("finding T2p instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from T2p as model where model."
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
		log.debug("finding all T2p instances");
		try {
			String queryString = "from T2p";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public T2p merge(T2p detachedInstance) {
		log.debug("merging T2p instance");
		try {
			T2p result = (T2p) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(T2p instance) {
		log.debug("attaching dirty T2p instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(T2p instance) {
		log.debug("attaching clean T2p instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static T2pDAO getFromApplicationContext(ApplicationContext ctx) {
		return (T2pDAO) ctx.getBean("T2pDAO");
	}
}