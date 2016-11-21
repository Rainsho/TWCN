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

import com.rainsho.entity.Directmsgs;

/**
 * A data access object (DAO) providing persistence and search support for
 * Directmsgs entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rainsho.entity.Directmsgs
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class DirectmsgsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DirectmsgsDAO.class);
	// property constants
	public static final String DCONTENT = "dcontent";
	public static final String DSTATE = "dstate";

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

	public void save(Directmsgs transientInstance) {
		log.debug("saving Directmsgs instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Directmsgs persistentInstance) {
		log.debug("deleting Directmsgs instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Directmsgs findById(java.lang.Integer id) {
		log.debug("getting Directmsgs instance with id: " + id);
		try {
			Directmsgs instance = (Directmsgs) getCurrentSession().get(
					"com.rainsho.entity.Directmsgs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Directmsgs> findByExample(Directmsgs instance) {
		log.debug("finding Directmsgs instance by example");
		try {
			List<Directmsgs> results = (List<Directmsgs>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Directmsgs")
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
		log.debug("finding Directmsgs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Directmsgs as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Directmsgs> findByDcontent(Object dcontent) {
		return findByProperty(DCONTENT, dcontent);
	}

	public List<Directmsgs> findByDstate(Object dstate) {
		return findByProperty(DSTATE, dstate);
	}

	public List findAll() {
		log.debug("finding all Directmsgs instances");
		try {
			String queryString = "from Directmsgs";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Directmsgs merge(Directmsgs detachedInstance) {
		log.debug("merging Directmsgs instance");
		try {
			Directmsgs result = (Directmsgs) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Directmsgs instance) {
		log.debug("attaching dirty Directmsgs instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Directmsgs instance) {
		log.debug("attaching clean Directmsgs instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DirectmsgsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DirectmsgsDAO) ctx.getBean("DirectmsgsDAO");
	}
}