package com.example.cristian.shopy11.Template;

/**
 * Created by Cristian on 6/20/2017.
 */

public class CurrentAccount {

    private static CurrentAccount currentAccount = null;

    public CurrentAccount() {

    }

    public String email, password;
    int id;

    public static CurrentAccount getInstance(){
        return currentAccount;
    }

    public static void create(){
        currentAccount = new CurrentAccount();
    }

    public void logout(){
        email = null;
        password = null;
        currentAccount = null;
    }
}
