package com.kcal.service;

import com.kcal.model.UserMeal;
import com.kcal.model.UserMealSum;

/**
 * User: Breku
 * Date: 2014-10-05
 */
public interface UserMealSumService {

    UserMealSum getUserMealSum(UserMeal userMeal);

    UserMealSum getUserMealSum();

}
