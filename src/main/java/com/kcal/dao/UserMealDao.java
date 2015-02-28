package com.kcal.dao;

import com.kcal.model.User;
import com.kcal.model.UserMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Repository
public class UserMealDao extends AbstractRootDao<UserMeal> {

    @Autowired
    public UserMealDao(MongoTemplate template) {
        super(template, UserMeal.class);
    }

    public UserMeal findMealByNameAndUser(String name, User user) {
        Query query = new Query(Criteria.where("name").is(name).and("user").is(user));
        return template.findOne(query, UserMeal.class);
    }

}
