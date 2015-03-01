package com.kcal.dao;

import com.kcal.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User: Breku
 * Date: 2014-09-13
 */
@Repository
public class FoodDaoImpl extends AbstractRootDao<Food> implements FoodDao {


    public FoodDaoImpl() {
        super(Food.class);
    }

    public void saveFood(Food food) {
        save(food);

    }

    public Food get(String name) {
        return null;
    }

}
