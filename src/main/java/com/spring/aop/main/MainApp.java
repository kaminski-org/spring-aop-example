package com.spring.aop.main;

import com.spring.aop.config.Config;
import com.spring.aop.dao.AccountDAO;
import com.spring.aop.dao.CustomerDAO;
import com.spring.aop.domain.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class MainApp {

    private static Logger myLogger = Logger.getLogger(MainApp.class.getName());


    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        CustomerDAO customerDAO = context.getBean("customerDAO", CustomerDAO.class);

        // call the business method
        accountDAO.addAccount(new Account("Dazz's account", "silver"), true);

        // call accountDAO getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        accountDAO.getName();
        accountDAO.getServiceCode();

        List<Account> accounts = null;

        // call method to find account
        try {
            accounts = accountDAO.findAccounts(false);
            myLogger.info("\n");
        } catch (Exception e) {
            myLogger.info("\n\nMain Program ... caught exception: " + e);
        }

        // call method to get customers
        customerDAO.getCustomers(true);

        // close the context
        context.close();
    }
}
