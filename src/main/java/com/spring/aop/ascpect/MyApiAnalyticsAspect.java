package com.spring.aop.ascpect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyApiAnalyticsAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Before("com.spring.aop.ascpect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {

        myLogger.info("\n=====> Performing API analytics");
    }

}
