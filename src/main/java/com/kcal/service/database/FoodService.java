package com.kcal.service.database;

import com.kcal.model.Food;

/**
 * User: Breku
 * Date: 2014-09-23
 */
public interface FoodService extends RootService<Food> {

    Food findByName(String name);
}
