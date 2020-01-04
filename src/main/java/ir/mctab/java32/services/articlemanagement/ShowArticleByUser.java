package ir.mctab.java32.services.articlemanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.Article;
import ir.mctab.java32.shared.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ShowArticleByUser {

    public static void show() {
        System.out.println("So you wanna see your articles. ");
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Article> query=session.createQuery("from Article where user_id=:id");
        query.setParameter("id", AuthenticationService.getInstance().getLoginUser().getId());
        System.out.println("These are your articles: \n");
        List<Article> articles=query.list();
        session.getTransaction().commit();
        articles.forEach(System.out::println);
    }
}
