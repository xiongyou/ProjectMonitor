package com.productMonitor.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateXMLUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			//从 hibernate.cfg.xml 中加载配置
			sessionFactory = new Configuration().configure(
					"hibernate_xml.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) {
		
	}

}