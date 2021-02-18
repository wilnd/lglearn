package lglearn.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(* lglearn.service.impl.*.*(..))")
    public void pb1() {

    }

    @Before("pb1()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("before start===");
    }

    @After("pb1()")
    public void afterMethod() {
        System.out.println("end not matter exception===");
    }

    @AfterReturning("pb1()")
    public void successEnd() {
        System.out.println("end success==");
    }

    @AfterThrowing("pb1()")
    public void exception() {
        System.out.println("exception==");
    }

//    @Around("pb1()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("eception");
            throwable.printStackTrace();
        } finally {
            System.out.println("after");
        }
        return null;
    }

}
