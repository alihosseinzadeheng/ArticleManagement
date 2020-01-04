package ir.mctab.java32.services.articlemanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;


public class ShowAllArticlesByUser {

    public static void show(){
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Article> query=session.createQuery("select id,title from Article ");
        List<Article> articles=query.list();
        session.getTransaction().commit();
        articles.forEach(System.out::println);
    }

}
