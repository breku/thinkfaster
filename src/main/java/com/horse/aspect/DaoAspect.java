package com.horse.aspect;


import com.horse.model.RootEntity;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Calendar;

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
    @Pointcut("execution(* com.horse.dao..save(..)) && args(obj)")
    private void saveEntity(RootEntity obj) {
        // only pointcut
    }

    @Before("saveEntity(obj)")
    public void addEntityId(RootEntity obj) {
//        obj.setId(counterService.getNextSequence(template.getCollectionName(obj.getClass())));
    }

    @Before("saveEntity(obj)")
    public void addCreationDate(RootEntity obj) {
        obj.setCreationDate(Calendar.getInstance().getTime());
    }

}
