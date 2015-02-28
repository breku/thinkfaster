package com.kcal.aspect;


import com.kcal.model.RootEntity;
import com.kcal.service.CounterService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    private CounterService counterService;

    private MongoTemplate template;

    @Autowired
    public DaoAspect(CounterService counterService, MongoTemplate template) {
        this.counterService = counterService;
        this.template = template;
    }

    /**
     * Any method in dao package named "save"
     */
    @Pointcut("execution(* com.kcal.dao..save(..)) && args(obj)")
    private void saveEntity(RootEntity obj) {
        // only pointcut
    }

    @Before("saveEntity(obj)")
    public void addEntityId(RootEntity obj) {
        obj.setId(counterService.getNextSequence(template.getCollectionName(obj.getClass())));
    }

    @Before("saveEntity(obj)")
    public void addCreationDate(RootEntity obj) {
        obj.setCreationDate(Calendar.getInstance().getTime());
    }

}
