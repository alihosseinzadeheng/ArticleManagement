package ir.mctab.java32.services.articlemanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PublishArticleByUser {


    public static void publish() {

        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Scanner scanner=new Scanner(System.in);
        Query<Article> query=session.createQuery("from Article");
        List<Article> articles=query.list();
        System.out.println("Your articles are here: ");
        articles.forEach(System.out::println);
        System.out.println("Enter the id of your wanted article to publish: ");
        Long id=scanner.nextLong();
        Article article=session.load(Article.class, id);
        article.setIsPublished(true);
        article.setPublishDate(new Date());
        article.setLastUpdateDate(new Date());
        session.update(article);
        session.getTransaction().commit();
        System.out.println("Your chosen article PublisheD. Be Happy and search for a job!!! ");

    }


}
