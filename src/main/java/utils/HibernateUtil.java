package utils;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Configuration configuration;

    static {
        try {
            configuration = new Configuration().addAnnotatedClass(Animal.class)
                                               .addAnnotatedClass(AnimalPersonnel.class)
                                               .addAnnotatedClass(Feed.class)
                                               .addAnnotatedClass(Home.class)
                                               .addAnnotatedClass(Personnel.class)
                                               .configure("views/hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}