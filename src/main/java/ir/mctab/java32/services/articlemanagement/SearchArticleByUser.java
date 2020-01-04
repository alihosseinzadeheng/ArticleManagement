package ir.mctab.java32.services.articlemanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.Scanner;

public class SearchArticleByUser {

    public static void search() {

        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Scanner scanner=new Scanner(System.in);

        System.out.println("You want to search in articles." +
                " It is possible to search by their titles. ");
        System.out.println("Enter your wanted title: ");

        Query<Article> query =session.createQuery(
                "from Article where title=: titleSearch");

        query.setParameter("titleSearch",scanner.nextLine());
        Article article;
        article=query.uniqueResult();

        session.getTransaction().commit();
        if (article!=null){
            System.out.println("I could find it! ");
        }
        System.out.println(article);
    }
}
