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

import com.rainsho.entity.Forwards;
import com.rainsho.entity.Tweets;
import com.rainsho.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Forwards entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rainsho.entity.Forwards
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ForwardsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ForwardsDAO.class);
	// property constants
	public static final String FCONTENT = "fcontent";
	public static final String FSTATE = "fstate";

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

	public void save(Forwards transientInstance) {
		log.debug("saving Forwards instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Forwards persistentInstance) {
		log.debug("deleting Forwards instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Forwards findById(java.lang.Integer id) {
		log.debug("getting Forwards instance with id: " + id);
		try {
			Forwards instance = (Forwards) getCurrentSession().get(
					"com.rainsho.entity.Forwards", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Forwards> findByExample(Forwards instance) {
		log.debug("finding Forwards instance by example");
		try {
			List<Forwards> results = (List<Forwards>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Forwards")
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
		log.debug("finding Forwards instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Forwards as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Forwards> findByFcontent(Object fcontent) {
		return findByProperty(FCONTENT, fcontent);
	}

	public List<Forwards> findByFstate(Object fstate) {
		return findByProperty(FSTATE, fstate);
	}

	public List findAll() {
		log.debug("finding all Forwards instances");
		try {
			String queryString = "from Forwards";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Forwards merge(Forwards detachedInstance) {
		log.debug("merging Forwards instance");
		try {
			Forwards result = (Forwards) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Forwards instance) {
		log.debug("attaching dirty Forwards instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Forwards instance) {
		log.debug("attaching clean Forwards instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ForwardsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ForwardsDAO) ctx.getBean("ForwardsDAO");
	}

	// other function
	public List<Forwards> findForwards(List<Users> list) {
		String hql = "from Forwards as f where f.users in (:list) and f.fstate>0";
		return getCurrentSession().createQuery(hql)
				.setParameterList("list", list).list();
	}

}