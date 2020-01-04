package ir.mctab.java32.services.usermanagement;

import ir.mctab.java32.config.HibernateUtil;
import ir.mctab.java32.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class RegisterByUser {
    static Scanner input=new Scanner(System.in);
    public static void register() {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Your default password is your username.");
        String password=username;
        System.out.println("Enter National code: ");
        String nationalCode=input.next();
        System.out.println("Enter birthday (By this format: yyyy.mm.dd) : ");
        String birthday=input.next();
        DateFormat dateFormat=new SimpleDateFormat("yyyy.mm.dd", Locale.ENGLISH);
        Date birthDate=null;
        try {
            birthDate= dateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user=new User(username,password,nationalCode,birthDate);
        session.save(user);
        session.getTransaction().commit();

    }
}
