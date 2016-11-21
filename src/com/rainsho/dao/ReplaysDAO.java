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

import com.rainsho.entity.Replays;

/**
 * A data access object (DAO) providing persistence and search support for
 * Replays entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rainsho.entity.Replays
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ReplaysDAO {
	private static final Logger log = LoggerFactory.getLogger(ReplaysDAO.class);
	// property constants
	public static final String RCONTENT = "rcontent";
	public static final String RSTATE = "rstate";

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

	public void save(Replays transientInstance) {
		log.debug("saving Replays instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Replays persistentInstance) {
		log.debug("deleting Replays instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Replays findById(java.lang.Integer id) {
		log.debug("getting Replays instance with id: " + id);
		try {
			Replays instance = (Replays) getCurrentSession().get(
					"com.rainsho.entity.Replays", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Replays> findByExample(Replays instance) {
		log.debug("finding Replays instance by example");
		try {
			List<Replays> results = (List<Replays>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Replays")
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
		log.debug("finding Replays instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Replays as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Replays> findByRcontent(Object rcontent) {
		return findByProperty(RCONTENT, rcontent);
	}

	public List<Replays> findByRstate(Object rstate) {
		return findByProperty(RSTATE, rstate);
	}

	public List findAll() {
		log.debug("finding all Replays instances");
		try {
			String queryString = "from Replays";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Replays merge(Replays detachedInstance) {
		log.debug("merging Replays instance");
		try {
			Replays result = (Replays) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Replays instance) {
		log.debug("attaching dirty Replays instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Replays instance) {
		log.debug("attaching clean Replays instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReplaysDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReplaysDAO) ctx.getBean("ReplaysDAO");
	}
}