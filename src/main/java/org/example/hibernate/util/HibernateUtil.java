package org.example.hibernate.util;

import org.example.hibernate.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_transaction?useSSL=false");
                properties.setProperty(Environment.USER, "root");
                properties.setProperty(Environment.PASS, "vina1998");
                properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.setProperty(Environment.SHOW_SQL, "true");
                properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);

                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
