package com.kcal.service;

import com.kcal.model.Food;
import com.kcal.model.UserMeal;
import com.kcal.model.UserMealSum;
import com.kcal.model.builder.UserMealSumBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 2014-10-05
 */
@Service
public class UserMealSumServiceImpl implements UserMealSumService {


    private UserMealService userMealService;

    @Autowired
    public UserMealSumServiceImpl(UserMealService userMealService) {
        this.userMealService = userMealService;
    }

    @Override
    public UserMealSum getUserMealSum(UserMeal userMeal) {
        UserMealSumBuilder builder = new UserMealSumBuilder();

        for (Food food : userMeal.getFoodMap().values()) {
            builder.addCarbohydrate(food.getCarbohydrate())
                    .addWeight(food.getWeight())
                    .addFat(food.getFat())
                    .addProtein(food.getProtein())
                    .addKcal(food.getKcal());
        }

        return builder.calcPercent().build();
    }

    @Override
    public UserMealSum getUserMealSum() {
        UserMeal defaultMeal = userMealService.getDefaultMeal();
        return getUserMealSum(defaultMeal);
    }
}
