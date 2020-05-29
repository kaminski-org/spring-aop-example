package com.spring.aop.ascpect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.spring.aop.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.spring.aop.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.spring.aop.dao.*.set*(..))")
    public void setter() {}

    // combining pointcuts
    @Pointcut(("forDaoPackage() && !(getter() || setter())"))
    public void forDaoPackageNoGetterSetter() {}

}
