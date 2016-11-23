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

import com.rainsho.entity.Relationships;
import com.rainsho.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Relationships entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rainsho.entity.Relationships
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RelationshipsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RelationshipsDAO.class);
	// property constants
	public static final String RSSTATE = "rsstate";

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

	public void save(Relationships transientInstance) {
		log.debug("saving Relationships instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Relationships persistentInstance) {
		log.debug("deleting Relationships instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Relationships findById(java.lang.Integer id) {
		log.debug("getting Relationships instance with id: " + id);
		try {
			Relationships instance = (Relationships) getCurrentSession().get(
					"com.rainsho.entity.Relationships", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Relationships> findByExample(Relationships instance) {
		log.debug("finding Relationships instance by example");
		try {
			List<Relationships> results = (List<Relationships>) getCurrentSession()
					.createCriteria("com.rainsho.entity.Relationships")
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
		log.debug("finding Relationships instance with property: "
				+ propertyName + ", value: " + value);
		try {
			// 逻辑删除
			String queryString = "from Relationships as model where model."
					+ propertyName + "= ? and model.rsstate = 1";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Relationships> findByRsstate(Object rsstate) {
		return findByProperty(RSSTATE, rsstate);
	}

	public List findAll() {
		log.debug("finding all Relationships instances");
		try {
			// 逻辑删除
			String queryString = "from Relationships as rs where rs.rsstate = 1";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Relationships merge(Relationships detachedInstance) {
		log.debug("merging Relationships instance");
		try {
			Relationships result = (Relationships) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Relationships instance) {
		log.debug("attaching dirty Relationships instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Relationships instance) {
		log.debug("attaching clean Relationships instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RelationshipsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RelationshipsDAO) ctx.getBean("RelationshipsDAO");
	}

	// advanced function
	public List<Users> findFollow(Users user) {
		String hql = "select r.usersBySuid from Relationships as r where r.usersByHuid=? and r.rsstate = 1";
		return getCurrentSession().createQuery(hql).setEntity(0, user).list();
	}

	public Relationships findByBoth(Users huser, Users suser) {
		String hql = "from Relationships as r where r.usersByHuid=? and r.usersBySuid=?";
		List list = getCurrentSession().createQuery(hql).setEntity(0, huser)
				.setEntity(1, suser).list();
		if (list.size() != 0) {
			return (Relationships) list.get(0);
		} else {
			return null;
		}
	}

}