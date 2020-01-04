package ir.mctab.java32;

import ir.mctab.java32.config.Display;
import ir.mctab.java32.services.articlemanagement.*;
import ir.mctab.java32.services.usermanagement.ChangePasswordByUser;
import ir.mctab.java32.services.usermanagement.LoginByUser;
import ir.mctab.java32.services.usermanagement.RegisterByUser;
import ir.mctab.java32.shared.AuthenticationService;


import java.util.Scanner;

public class App {
    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);

        Display.entryOrders();

        System.out.println("Enter your order:");

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("exit")) {

            if(AuthenticationService.getInstance().getLoginUser()==null) {
                switch (input.toLowerCase()) {
                    case "register": {
                        RegisterByUser.register();
                        System.out.println("\nSo if you want to continue, make a login or write \"exit\" to quit.\n");
                        LoginByUser.login();
                        break;
                    }
                    case "login": {
                        LoginByUser.login();
                        break;
                    }
                    case "show articles": {
                        ShowAllArticlesByUser.show();
                        break;
                    }
                }
            }else if(AuthenticationService.getInstance().getLoginUser()!=null){
                Display.ArticleOrders();
               input=scanner.nextLine();
                switch (input.toLowerCase()){
                    case "show my articles" :{
                        ShowArticleByUser.show();
                        break;
                    }
                    case "create an article" :{
                        CreateArticleByUser.create();
                        break;
                    }
                    case "edit an article" :{
                        EditArticleByUser.edit();
                        break;
                    }
                    case "publish an article" :{
                        PublishArticleByUser.publish();
                        break;
                    }
                    case "search an article" :{
                        SearchArticleByUser.search();
                        break;
                    }
                    case "change password" :{
                        ChangePasswordByUser.change();
                        break;
                    }

                    case "logout":{
                        AuthenticationService.getInstance().setLoginUser(null);
                        break;
                    }

                }

            }
        } //while exit end



    } //main end
}
