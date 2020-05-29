package com.spring.aop.ascpect;

import com.spring.aop.domain.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    // add related advices for logging

    // () - matches a method with no arguments
    // (*) - matches a method with one argument of any type
    // (..) - matches a method with one ore more arguments of any type

    // @Before("execution(public void com.spring.aop.dao.AccountDAO.addAccount())")

    @Before("com.spring.aop.ascpect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        myLogger.info("\n=====> Executing @Before advice on method");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // display method signature
        myLogger.info("\nMethod: " + methodSignature);

        // display method arguments
        Object[] args = joinPoint.getArgs();

        for (Object tempArgs : args) {
            System.out.println(tempArgs);
        }
    }


    @After("execution(* com.spring.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFindAccounts(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n=====> Executing @After on method: " + method
                + " regardless of the outcome");
    }

    @Around("execution(* com.spring.aop.dao.CustomerDAO.getCustomers(..))")
    public Object beforeAndAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====> Executing @Aaround on method: " + method
                + " regardless of the outcome");

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            myLogger.warning("We have a problem at @Around: " + e.getMessage());
            // result = "Nothing exciting here. Move along!";
            // rethrow the exception
            throw e;
        }

        return result;
    }

    @AfterReturning(
            pointcut = "execution(* com.spring.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccounts(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n=====> Executing @AfterReturning on method: " + method);
        myLogger.info("\n=====> result is: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.spring.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccounts(JoinPoint joinPoint, Throwable exc) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n=====> Executing @AfterThrowing on method: " + method);

        // log in exception
        myLogger.info("\n=====> The exception is: " + exc);
    }
}
