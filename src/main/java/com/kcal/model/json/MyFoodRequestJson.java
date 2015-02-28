package com.kcal.model.json;

import com.kcal.model.utils.MealNumber;

/**
 * User: Breku
 * Date: 2014-10-27
 */
public class MyFoodRequestJson {

    private long foodId;

    private MealNumber target;

    private MealNumber sender;

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public MealNumber getTarget() {
        return target;
    }

    public void setTarget(MealNumber target) {
        this.target = target;
    }

    public MealNumber getSender() {
        return sender;
    }

    public void setSender(MealNumber sender) {
        this.sender = sender;
    }
}
