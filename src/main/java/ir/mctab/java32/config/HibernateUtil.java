package ir.mctab.java32.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session=sessionFactory.getCurrentSession();
    }

    public static Session getSession() {
        return session;
    }
}
