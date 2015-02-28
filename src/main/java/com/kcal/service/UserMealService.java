package com.kcal.service;

import com.kcal.model.Food;
import com.kcal.model.UserMeal;
import com.kcal.model.json.FoodRequestJson;
import com.kcal.model.json.FoodStatus;
import com.kcal.model.json.MyFoodRequestJson;
import com.kcal.model.utils.MealNumber;

import java.util.Map;

/**
 * User: Breku
 * Date: 2014-09-24
 */
public interface UserMealService {

    UserMeal getDefaultMeal();

    FoodStatus addFoodToUserMeal(UserMeal defaultMeal, FoodRequestJson foodRequestJson);

    FoodStatus removeFoodFromUserMeal(UserMeal defaultMeal, FoodRequestJson foodRequestJson);

    boolean updateMealNumber(MyFoodRequestJson json);

    void updateUserMealIds();

    Map<Long, Food> getFoodMapFromDefaultMeal(MealNumber mealNumber);


}


