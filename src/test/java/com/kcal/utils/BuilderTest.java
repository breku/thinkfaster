package com.kcal.utils;

import com.google.common.collect.Sets;
import com.kcal.model.*;
import com.kcal.model.builder.FoodBuilder;
import com.kcal.model.json.FoodRequestJson;
import com.kcal.model.json.MyFoodRequestJson;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import com.kcal.model.utils.MealNumber;
import com.kcal.utils.security.RoleName;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Breku
 * Date: 2014-10-28
 */
public class BuilderTest {

    public static FoodRequestJson createFoodRequestJson(long foodId, double weigthFactor) {
        FoodRequestJson json = new FoodRequestJson();
        json.setFoodId(foodId);
        json.setCarbohydrate(10);
        json.setProtein(10);
        json.setFat(10);
        json.setWeightFactor(weigthFactor);
        json.setKcal(10);
        return json;
    }

    public static UserProfileSliderJson createUserProfileSliderJson(double proteinPercent, double carbohydratePercent, double fatPercent) {
        UserProfileSliderJson json = new UserProfileSliderJson(proteinPercent, carbohydratePercent, fatPercent);
        return json;
    }

    public static UserProfile createProfile(double proteinPercent, double carbohydratePercent, double fatPercent) {
        UserProfile profile = new UserProfile();
        profile.setFatPercent(fatPercent);
        profile.setCarbohydratePercent(carbohydratePercent);
        profile.setProteinPercent(proteinPercent);
        return profile;
    }


    public static XEditableForm createXEditableForm(String name, String value) {
        XEditableForm form = new XEditableForm();
        form.setName(name);
        form.setValue(value);
        return form;
    }

    public static User createUser(String name) {
        User user = new User(name, "aaa@aaa.pl", "zaq1@WSX", true, true, true, true, Sets.<UserAuthority>newHashSet(new UserAuthority(RoleName.ROLE_ADMIN.name())));
        user.setUserProfile(new UserProfile());
        return user;
    }


    public static User createUser(long id, String name) {
        User user = new User(name, "aaa@aaa.pl", "zaq1@WSX", true, true, true, true, Sets.<UserAuthority>newHashSet(new UserAuthority(RoleName.ROLE_ADMIN.name())));
        user.setId(id);
        user.setUserProfile(new UserProfile());
        return user;
    }

    public static UserMeal createUserMeal(long id, Food... foods) {
        UserMeal userMeal = new UserMeal();
        Map<Long, Food> map = new HashMap<>();

        for (Food food : foods) {
            map.put(food.getId(), food);
        }
        userMeal.setId(id);
        userMeal.setFoodMap(map);
        return userMeal;
    }

    public static Food createFood(long id, String name) {
        return createFood(id, name, MealNumber.DEFAULT);
    }

    public static Food createFood(long id, String name, MealNumber mealNumber) {
        Food food = new Food();
        food.setName(name);
        food.setId(id);
        new FoodBuilder(food);

        return new FoodBuilder(food).carbohydrate(10).fat(10).protein(10).kcal(10).weight(10).mealNumber(mealNumber).build();
    }

    public static MyFoodRequestJson createMyFoodRequestJson(long id, MealNumber sender, MealNumber target) {
        MyFoodRequestJson json = new MyFoodRequestJson();
        json.setFoodId(id);
        json.setTarget(target);
        json.setSender(sender);
        return json;
    }
}
