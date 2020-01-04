package ir.mctab.java32.services.usermanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.User;
import ir.mctab.java32.shared.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class ChangePasswordByUser {

    public static void change() {

        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Scanner scanner=new Scanner(System.in);

        System.out.println("You want to change your password! ");
        System.out.println("Enter your new one: ");
        String password=scanner.nextLine();
        Long userId= AuthenticationService.getInstance().getLoginUser().getId();
        User user=session.load(User.class,userId);
        user.setPassword(password);
        System.out.println("your new password has been set. a little Smile! ");
        session.update(user);
        session.getTransaction().commit();

    }
}
