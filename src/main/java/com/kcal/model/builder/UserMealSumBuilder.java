package com.kcal.model.builder;

import com.kcal.model.UserMealSum;

/**
 * User: Breku
 * Date: 2014-10-11
 */
public class UserMealSumBuilder extends AbstractBuilder<UserMealSum> {

    public UserMealSumBuilder() {
        super();
    }


    public UserMealSumBuilder calcPercent() {

        double denominator = obj.getFat() + obj.getCarbohydrate() + obj.getProtein();
        if (denominator != 0) {
            obj.setProteinPercent(parseDouble((obj.getProtein() / denominator) * 100));
            obj.setCarbohydratePercent(parseDouble((obj.getCarbohydrate() / denominator) * 100));
            obj.setFatPercent(parseDouble((obj.getFat() / denominator) * 100));
        } else {
            obj.setProteinPercent(0);
            obj.setCarbohydratePercent(0);
            obj.setFatPercent(0);
        }

        return this;
    }

    public UserMealSumBuilder addWeight(double weight) {
        obj.setWeight(parseDouble(weight + obj.getWeight()));
        return this;
    }

    public UserMealSumBuilder addFat(double fat) {
        obj.setFat(parseDouble(fat + obj.getFat()));
        return this;
    }

    public UserMealSumBuilder addKcal(double kcal) {
        obj.setKcal(parseDouble(kcal + obj.getKcal()));
        return this;
    }

    public UserMealSumBuilder addCarbohydrate(double carbohydrate) {
        obj.setCarbohydrate(parseDouble(carbohydrate + obj.getCarbohydrate()));
        return this;
    }

    public UserMealSumBuilder addProtein(double protein) {
        obj.setProtein(parseDouble(protein + obj.getProtein()));
        return this;
    }
}
