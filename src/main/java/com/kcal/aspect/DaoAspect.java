package com.kcal.aspect;


import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.kcal.model.RootEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * User: Breku
 * Date: 07.07.14
 */
@Configurable
@Component
@Aspect
public class DaoAspect {


    /**
     * Any method in dao package named "save"
     */
    @Pointcut("execution(* com.kcal.dao..save(..)) && args(obj)")
    private void saveEntity(RootEntity obj) {
        // only pointcut
    }

    @Pointcut("execution(* com.kcal.dao..get(..)) && args(id)")
    private void getEntity(long id) {
        // only pointcut
    }

    @Around("saveEntity(obj)")
    public void transactionSave(ProceedingJoinPoint joinPoint, RootEntity obj) throws Throwable {
        Closeable closeable = ObjectifyService.begin();
        joinPoint.proceed();
        closeable.close();
    }


    @Around("getEntity(id)")
    public <T extends RootEntity> T transactionGet(ProceedingJoinPoint joinPoint, long id) throws Throwable {
        Closeable closeable = ObjectifyService.begin();
        return (T) joinPoint.proceed();
//        closeable.close();
    }
//
//    @After("saveEntity(obj)")
//    public void endTransaction(RootEntity obj) {
//    }

}
