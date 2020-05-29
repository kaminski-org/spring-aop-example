package com.spring.aop.dao;
import com.spring.aop.domain.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class AccountDAO {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    private String name;
    private String serviceCode;

    public void addAccount(Account account, boolean isFlagged) {

        myLogger.info("\n" + getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public List<Account> findAccounts(boolean flag) {

        if (flag) {
            throw new RuntimeException("No accounts found");
        }

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("John", "Silver"));
        accounts.add(new Account("Luke", "Platinium"));

        return accounts;
    }

    public boolean doWork() {
        myLogger.info(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        myLogger.info(getClass() + ": inGetName()");

        return name;
    }

    public void setName(String name) {
        myLogger.info(getClass() + ": inSetName()");
        this.name = name;
    }

    public String getServiceCode() {
        myLogger.info(getClass() + ": inGetServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        myLogger.info(getClass() + ": inSetServiceCode");
        this.serviceCode = serviceCode;
    }
}
