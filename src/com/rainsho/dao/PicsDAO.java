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

import com.rainsho.entity.Pics;

/**
 * A data access object (DAO) providing persistence and search support for Pics
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rainsho.entity.Pics
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PicsDAO {
	private static final Logger log = LoggerFactory.getLogger(PicsDAO.class);
	// property constants
	public static final String PNAME = "pname";
	public static final String PPATH = "ppath";

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

	public void save(Pics transientInstance) {
		log.debug("saving Pics instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Pics persistentInstance) {
		log.debug("deleting Pics instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pics findById(java.lang.Integer id) {
		log.debug("getting Pics instance with id: " + id);
		try {
			Pics instance = (Pics) getCurrentSession().get(
					"com.rainsho.entity.Pics", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Pics> findByExample(Pics instance) {
		log.debug("finding Pics instance by example");
		try {
			List<Pics> results = (List<Pics>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Pics")
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
		log.debug("finding Pics instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Pics as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Pics> findByPname(Object pname) {
		return findByProperty(PNAME, pname);
	}

	public List<Pics> findByPpath(Object ppath) {
		return findByProperty(PPATH, ppath);
	}

	public List findAll() {
		log.debug("finding all Pics instances");
		try {
			String queryString = "from Pics";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Pics merge(Pics detachedInstance) {
		log.debug("merging Pics instance");
		try {
			Pics result = (Pics) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Pics instance) {
		log.debug("attaching dirty Pics instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pics instance) {
		log.debug("attaching clean Pics instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PicsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PicsDAO) ctx.getBean("PicsDAO");
	}
}