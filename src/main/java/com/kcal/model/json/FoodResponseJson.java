package com.kcal.model.json;

import com.kcal.model.Food;
import com.kcal.model.UserMealSum;
import com.kcal.model.UserProfile;

/**
 * User: Breku
 * Date: 2014-10-03
 */
public class FoodResponseJson {

    private Food food;

    private String message;

    private UserProfile userProfile;

    private UserMealSum userMealSum;

    public FoodResponseJson() {

    }

    public static class FoodResponseBuilder {

        private FoodResponseJson obj;

        private FoodResponseBuilder() {
            obj = new FoodResponseJson();
        }

        public static FoodResponseBuilder get() {
            return new FoodResponseBuilder();
        }

        public FoodResponseBuilder food(Food food) {
            obj.food = food;
            return this;
        }

        public FoodResponseBuilder userProfile(UserProfile userProfile) {
            obj.userProfile = userProfile;
            return this;
        }

        public FoodResponseBuilder userMealSum(UserMealSum userMealSum) {
            obj.userMealSum = userMealSum;
            return this;
        }

        public FoodResponseBuilder responseStatus(String message) {
            obj.message = message;
            return this;
        }

        public FoodResponseJson build() {
            return obj;
        }
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public UserMealSum getUserMealSum() {
        return userMealSum;
    }

    public String getMessage() {
        return message;
    }

    public Food getFood() {
        return food;
    }

}
