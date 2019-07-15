package com.syk.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.syk.ormentity.UserInfo;
import com.syk.ormentity.UserInfo1;
import com.syk.sessionFactory.GetSessionFactory;

public class UserInfoDao {
	SessionFactory sessionFactory;
	public UserInfoDao() {
		sessionFactory = GetSessionFactory.getSessionFactory();
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public List<UserInfo> getUserList() {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		List<UserInfo> userList = new ArrayList<UserInfo>();
		try {
			tr = session.beginTransaction();
			userList = session.createQuery("FROM UserInfo").list();
			tr.commit();
		}
		catch(Exception e) {
			if(tr!=null) {
				tr.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
			return userList;
		}
	}
	
	@SuppressWarnings("finally")
	public Integer addUser(String name, String password) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		Integer id = null;
		try {
			tr = session.beginTransaction();
			UserInfo newUser = new UserInfo(name, password);
			id = (Integer) session.save(newUser);
			tr.commit();
		}
		catch(HibernateException he){
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return id;
		}
	}
	
	public void deleteOneUser(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			UserInfo userInfo = session.get(UserInfo.class, id);
			session.delete(userInfo);
			tr.commit();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public void updateOneUser(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			UserInfo userInfo = session.get(UserInfo.class, id);
			userInfo.setName("newName");
			userInfo.setPassword("newPassword");
			session.update(userInfo);
			tr.commit();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "finally" })
	public List queryByNameAndPassword(String name, String password) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		List result = new ArrayList();
		try {
			tr = session.beginTransaction();
			String hql = "select new com.syk.ormentity.UserInfo(name, password) from UserInfo where name = :name and password = :password";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("password", password);
			result = query.list();
			tr.commit();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return result;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "finally" })
	public Integer updateUserByHQL(String oldname, String newname) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		Integer num = null;
		try {
			tr = session.beginTransaction();
			String hql = "update UserInfo set name=:newname where name=:oldname";
			Query query = session.createQuery(hql);
			query.setParameter("newname", newname);
			query.setParameter("oldname", oldname);
			num = query.executeUpdate();
			tr.commit();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return num;
		}
	}
	
	@SuppressWarnings("finally")
	public Integer deleteUserByHQL(String name) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		Integer num = null;
		try {
			tr = session.beginTransaction();
			String hql = "delete from UserInfo where name=:name";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			num = query.executeUpdate();
			tr.commit();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return num;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	public List getUserBySQL(String name) {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		List list = new ArrayList();
		try {
			tr = session.beginTransaction();
			String sql = "select t_name,password,id from users where t_name=:name";
			NativeQuery query = session.createSQLQuery(sql);
			query.setParameter("name", name);
			query.addEntity(UserInfo.class);
			list = query.list();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return list;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "finally" })
	public List<UserInfo1> queryBothTableByHql() {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		List<UserInfo1> list = new ArrayList<UserInfo1>();
		try {
			tr = session.beginTransaction();
			String hql = "select new com.syk.ormentity.UserInfo1(a.name, b.password,b.ts) from UserInfo a,UserInfo1 b where a.name=b.name";
			Query query = session.createQuery(hql);
			list = query.list();
			tr.commit();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return list;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public Object queryBothTableBySQL() {
		Session session = sessionFactory.openSession();
		Transaction tr = null;
		Object list = null;
		try {
			tr = session.beginTransaction();
			String sql = "select a.* from users a join users1 b where a.t_name=b.name";
			NativeQuery query = session.createSQLQuery(sql);
			query.addEntity("a", UserInfo.class);
//			query.addEntity("b", UserInfo1.class);
			list = query.uniqueResult();
		}
		catch(HibernateException he) {
			if(tr!=null) {
				tr.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
			return list;
		}
	}
}
