package com.syk.sessionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
	private static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	public static SessionFactory getSessionFactory() {
		return factory;
	}
}
