package ir.mctab.java32.services.articlemanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.Scanner;

public class EditArticleByUser {
    static Scanner scanner=new Scanner(System.in);

    public static void edit() {
        System.out.println("So you want to edit an article. ");
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        System.out.println("Which article do you want to edit? (Enter its id)");
        Long id= scanner.nextLong();
        Article article=session.load(Article.class,id);
        System.out.println("This is your wanted article: ");
        System.out.println(article);
        System.out.println("Enter new Title: ");
        article.setTitle(scanner.nextLine());
        System.out.println("Enter new brief: ");
        article.setBrief(scanner.nextLine());
        System.out.println("Enter new content: ");
        article.setContent(scanner.nextLine());
        System.out.println("Is published? ");
        if (scanner.nextLine().equalsIgnoreCase("yes")){
            article.setIsPublished(true);
        } else {
            article.setIsPublished(false);
        }

        if(article.getIsPublished()){
            article.setPublishDate(new Date());
            article.setLastUpdateDate(new Date());
        }
        session.update(article);
        session.getTransaction().commit();
        System.out.println("\nYou have successfully edited your article, ");
        System.out.println("This is your edited article: ");
        System.out.println(article);
        System.out.println("NOW, Go On...\n");
    }
}
