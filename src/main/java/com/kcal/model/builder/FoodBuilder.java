package com.kcal.model.builder;

import com.kcal.model.Food;
import com.kcal.model.utils.MealNumber;

/**
 * User: Breku
 * Date: 2014-10-04
 */
public class FoodBuilder extends AbstractBuilder<Food> {

    public FoodBuilder(){
        super();
    }

    public FoodBuilder(Food obj) {
        super(obj);
    }

    public FoodBuilder weight(double weight) {
        obj.setWeight(parseDouble(weight));
        return this;
    }

    public FoodBuilder fat(double fat) {
        obj.setFat(parseDouble(fat));
        return this;
    }

    public FoodBuilder kcal(double kcal) {
        obj.setKcal(parseDouble(kcal));
        return this;
    }

    public FoodBuilder carbohydrate(double carbohydrate) {
        obj.setCarbohydrate(parseDouble(carbohydrate));
        return this;
    }

    public FoodBuilder protein(double protein) {
        obj.setProtein(parseDouble(protein));
        return this;
    }

    public FoodBuilder mealNumber(MealNumber mealNumber){
        obj.setMealNumber(mealNumber);
        return this;
    }
}
