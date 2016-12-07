package com.example;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(public * com.example.Controller.*(..))")
    public void publicMethod(){}

    @Before("publicMethod()")
    public void addLog(final JoinPoint jP){
        System.out.println("*** Executing " + jP.getSignature());
        java.lang.Object[] arguments = jP.getArgs();
        for(java.lang.Object arg: arguments){
            System.out.println(arg.getClass().getSimpleName() + " = " + arg + "");
        }
    }

    @Around("publicMethod() && @annotation(Timed)")
    public java.lang.Object profile(final ProceedingJoinPoint jP) throws Throwable{
        final long start = System.currentTimeMillis();
        try{
            final java.lang.Object value = jP.proceed();
            return value;
        } catch(Throwable t){
            throw t;
        } finally {
            final long end = System.currentTimeMillis();
            System.out.println("+++ Execution of " + jP.getSignature().getName() + " took: " + (end-start) + "ms\n");
        }
    }
}
