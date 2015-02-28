package com.kcal.dao;

import com.kcal.model.User;
import com.kcal.model.UserMeal;

/**
 * Created by brekol on 28.02.15.
 */
public interface UserMealDao extends RootDao<UserMeal>{


    UserMeal findMealByNameAndUser(String name, User user);
}
