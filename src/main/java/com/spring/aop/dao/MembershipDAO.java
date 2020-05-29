package com.spring.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public boolean addAccount() {
        System.out.println("\n" + getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT FOR MEMBERSHIP");

        return true;
    }
}
