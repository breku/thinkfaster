package com.kcal.dao;

import com.kcal.model.Food;

/**
 * Created by brekol on 28.02.15.
 */
public interface FoodDao  extends RootDao<Food>{


    public Food get(String name);
}
