package com.imcs.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory buildSessionFactory() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory sessionFactory = config.buildSessionFactory(builder.build());
		return sessionFactory;
	}
}
