package com.ems.config;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.ems.entity.Admin;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.entity.Manager;
import com.ems.entity.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null) {
			try {
				Configuration config = new Configuration();
				
				//Hibernate setting is equivalent to hibernate.cfg.xml properties
				Properties property = new Properties();
				
				property.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				property.put(Environment.URL, "jdbc:mysql://localhost:3306/ems");
				property.put(Environment.USER, "root");
				property.put(Environment.PASS, "root");
				property.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				
				property.put(Environment.SHOW_SQL, "true");
				property.put(Environment.HBM2DDL_AUTO, "update");
				property.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				
				property.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
				
				config.setProperties(property);
				
				config.addAnnotatedClass(Employee.class);
				config.addAnnotatedClass(Manager.class);
				config.addAnnotatedClass(Department.class);
				config.addAnnotatedClass(Admin.class);
				config.addAnnotatedClass(User.class);
				
				ServiceRegistry register = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				System.out.println("Hibernate Java Config Service Registry Created...");
				sessionFactory=config.buildSessionFactory(register);
				
				return sessionFactory;
			}
			catch(HibernateException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return sessionFactory;
	}
	
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
}
