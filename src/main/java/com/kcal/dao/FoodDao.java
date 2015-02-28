package com.kcal.dao;

import com.kcal.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * User: Breku
 * Date: 2014-09-13
 */
@Repository
public class FoodDao extends AbstractRootDao<Food> {

    @Autowired
    public FoodDao(MongoTemplate template) {
        super(template, Food.class);
    }

    public void saveFood(Food food) {
        save(food);

    }

    public Food get(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return template.findOne(query, Food.class);
    }
}
