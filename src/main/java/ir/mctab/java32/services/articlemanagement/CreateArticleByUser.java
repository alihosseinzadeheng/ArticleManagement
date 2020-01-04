package ir.mctab.java32.services.articlemanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.Article;
import ir.mctab.java32.entities.Category;
import ir.mctab.java32.shared.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CreateArticleByUser {

    static Scanner scanner=new Scanner(System.in);

    public static void create() {

        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();

        System.out.println("So you want to add an article. ");

        long idNum=0L;

        do {
            showCategories(session);
            idNum=existanceChecker(session);
        } while(idNum==0L);

        creatingProcess(session, idNum);

    }

    private static void creatingProcess(Session session, long idNum) {
        session.beginTransaction();
        Category category=session.load(Category.class,idNum);
        System.out.println(" So you have chosen your category, " +
                "let's write other details.");
        System.out.println("Enter title: ");
        String title=scanner.nextLine();
        System.out.println("Enter brief: ");
        String brief=scanner.nextLine();
        System.out.println("Enter content: ");
        String content=scanner.nextLine();
        System.out.println("Is published? (yes or no)");
        boolean ispublished=false;
        Date publishDate=null;
        if (scanner.next().equalsIgnoreCase("yes")){
            ispublished=true;
            publishDate=  new Date();
        }

        session.save(
                Article.builder()
                        .title(title)
                        .brief(brief)
                        .content(content)
                        .createDate(new Date())
                        .lastUpdateDate(new Date())
                        .publishDate(publishDate)
                        .isPublished(ispublished)
                        .category(category)
                        .user(AuthenticationService.getInstance().getLoginUser())
                        .build()
        );
        session.getTransaction().commit();
    }

    private static Long existanceChecker(Session session) {
        System.out.println("Is there your desirable category? (yes or no)");
        Long id=0L;
        String yesOrNo= scanner.next();


        if (yesOrNo.equalsIgnoreCase("yes")){
            System.out.println("Choose your category number ");
            id=scanner.nextLong();
        } else if( yesOrNo.equalsIgnoreCase("no")){
            System.out.println("Write your category title ");
            String title=scanner.next();
            System.out.println("Write a description for it ");
            String description=scanner.next();
            session.beginTransaction();
            session.save(new Category(title,description));
            session.beginTransaction().commit();
        }

        return id;
    }

    private static void showCategories(Session session) {
        System.out.println("See the list of available categories to choose: ");
        Query<Category> query=session.createQuery("from category");
        List<Category> categories=query.list();
        session.getTransaction().commit();
        categories.forEach(System.out::println);
    }
}
