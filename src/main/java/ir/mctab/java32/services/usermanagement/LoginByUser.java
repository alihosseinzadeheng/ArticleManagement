package ir.mctab.java32.services.usermanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.User;
import ir.mctab.java32.shared.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.Scanner;

public class LoginByUser {

    public static void login(){
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scanner=new Scanner(System.in);
        System.out.println("You are loging in! ");
        System.out.println("Enter your username:");
        String username=scanner.next();
        System.out.println("Enter your Password:");
        String password=scanner.next();

        Query<User> query = session.createQuery("from User where username=:username and password=:password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = query.uniqueResult();
        session.getTransaction().commit();

        if (user != null) {
            System.out.println("\nSo you are \" " + user.getUserName() + " \" , WelcomE");
            AuthenticationService.getInstance().setLoginUser(user);
        } else {
            System.out.println("\nYour are a wrong User, ExcuseUs.");
        }

    }
}
