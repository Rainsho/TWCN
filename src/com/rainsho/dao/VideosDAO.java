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

import com.rainsho.entity.Videos;

/**
 * A data access object (DAO) providing persistence and search support for
 * Videos entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rainsho.entity.Videos
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class VideosDAO {
	private static final Logger log = LoggerFactory.getLogger(VideosDAO.class);
	// property constants
	public static final String VNAME = "vname";
	public static final String VPATH = "vpath";

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

	public void save(Videos transientInstance) {
		log.debug("saving Videos instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Videos persistentInstance) {
		log.debug("deleting Videos instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Videos findById(java.lang.Integer id) {
		log.debug("getting Videos instance with id: " + id);
		try {
			Videos instance = (Videos) getCurrentSession().get(
					"com.rainsho.entity.Videos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Videos> findByExample(Videos instance) {
		log.debug("finding Videos instance by example");
		try {
			List<Videos> results = (List<Videos>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Videos")
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
		log.debug("finding Videos instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Videos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Videos> findByVname(Object vname) {
		return findByProperty(VNAME, vname);
	}

	public List<Videos> findByVpath(Object vpath) {
		return findByProperty(VPATH, vpath);
	}

	public List findAll() {
		log.debug("finding all Videos instances");
		try {
			String queryString = "from Videos";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Videos merge(Videos detachedInstance) {
		log.debug("merging Videos instance");
		try {
			Videos result = (Videos) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Videos instance) {
		log.debug("attaching dirty Videos instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Videos instance) {
		log.debug("attaching clean Videos instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VideosDAO getFromApplicationContext(ApplicationContext ctx) {
		return (VideosDAO) ctx.getBean("VideosDAO");
	}
}