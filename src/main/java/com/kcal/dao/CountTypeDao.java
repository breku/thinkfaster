package com.kcal.dao;

import com.kcal.model.CountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * User: Breku
 * Date: 2014-09-14
 */
@Repository
public class CountTypeDao extends AbstractRootDao<CountType> {

    @Autowired
    public CountTypeDao(MongoTemplate template) {
        super(template, CountType.class);
    }
}
