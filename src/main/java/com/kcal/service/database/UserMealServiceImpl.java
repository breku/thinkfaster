package com.kcal.service.database;

import com.kcal.dao.UserMealDao;
import com.kcal.dao.UserMealDaoImpl;
import com.kcal.model.Food;
import com.kcal.model.User;
import com.kcal.model.UserMeal;
import com.kcal.model.builder.FoodBuilder;
import com.kcal.model.json.FoodRequestJson;
import com.kcal.model.json.FoodStatus;
import com.kcal.model.json.MyFoodRequestJson;
import com.kcal.model.utils.MealNumber;
import com.kcal.service.UserMealService;
import com.kcal.service.database.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Service
public class UserMealServiceImpl extends AbstractRootService<UserMeal, UserMealDao> implements UserMealService {

    private static final String DEFAULT_MEAL_NAME = "defaultMeal";

    private UserService userService;

    private FoodService foodService;

    @Autowired
    public UserMealServiceImpl(UserMealDao dao, UserService userService, FoodService foodService) {
        super(dao);
        this.userService = userService;
        this.foodService = foodService;
    }

    public UserMeal getDefaultMeal() {
        User user = userService.getCurrentUser();
        UserMeal defaultMeal = dao.findMealByNameAndUser(DEFAULT_MEAL_NAME, user);
        if (defaultMeal == null) {
            defaultMeal = createDefaultMeal(user);
        }
        return defaultMeal;
    }

    @Override
    public FoodStatus addFoodToUserMeal(UserMeal defaultMeal, FoodRequestJson foodRequestJson) {

        Food responseFood = foodService.get(foodRequestJson.getFoodId());
        responseFood.setUsed(true);

        if (defaultMeal.getFoodMap().containsKey(foodRequestJson.getFoodId())) {
            updateExistingFood(defaultMeal.getFoodMap().get(foodRequestJson.getFoodId()), foodRequestJson);
            update(defaultMeal, "foodMap", defaultMeal.getFoodMap());
            return FoodStatus.ADD_UPDATE;
        }

        Food newFoodItem = createNewFoodItem(responseFood, foodRequestJson.getWeightFactor());
        defaultMeal.getFoodMap().put(newFoodItem.getId(), newFoodItem);
        update(defaultMeal, "foodMap", defaultMeal.getFoodMap());
        return FoodStatus.ADD_ELEMENT;


    }

    @Override
    public FoodStatus removeFoodFromUserMeal(UserMeal defaultMeal, FoodRequestJson foodRequestJson) {
        Food orginalFood = foodService.get(foodRequestJson.getFoodId());
        Food foodToRemoved = defaultMeal.getFoodMap().get(foodRequestJson.getFoodId());
        if (foodToRemoved.getWeight() <= foodRequestJson.getWeightFactor() * 100) {
            defaultMeal.getFoodMap().remove(foodRequestJson.getFoodId());
            update(defaultMeal, "foodMap", defaultMeal.getFoodMap());
            return FoodStatus.REMOVE_ELEMENT;
        }
        decreaseFoodParameters(foodToRemoved, orginalFood, foodRequestJson.getWeightFactor());
        update(defaultMeal, "foodMap", defaultMeal.getFoodMap());
        return FoodStatus.REMOVE_UPDATE;
    }

    @Override
    public Map<Long, Food> getFoodMapFromDefaultMeal(MealNumber mealNumber) {
        UserMeal defaultMeal = getDefaultMeal();
        Map<Long, Food> resultMap = new HashMap<>();
        Map<Long, Food> foodMap = defaultMeal.getFoodMap();
        for (Map.Entry<Long, Food> entry : foodMap.entrySet()) {
            if(entry.getValue().getMealNumber().equals(mealNumber)){
                resultMap.put(entry.getKey(),entry.getValue());
            }
        }
        return resultMap;
    }

    private void decreaseFoodParameters(Food food, Food orginalFood, double factor) {
        new FoodBuilder(food)
                .carbohydrate(food.getCarbohydrate() - orginalFood.getCarbohydrate() * factor)
                .fat(food.getFat() - orginalFood.getFat() * factor)
                .kcal(food.getKcal() - orginalFood.getKcal() * factor)
                .protein(food.getProtein() - orginalFood.getProtein() * factor)
                .weight(food.getWeight() - 100 * factor);
    }

    private UserMeal createDefaultMeal(User user) {
        UserMeal userMeal = new UserMeal();
        userMeal.setName(DEFAULT_MEAL_NAME);
        userMeal.setUser(user);
        userMeal.setFoodMap(new HashMap<Long, Food>());
        save(userMeal);
        return userMeal;
    }

    private void updateExistingFood(Food existingFood, FoodRequestJson foodRequestJson) {
        new FoodBuilder(existingFood)
                .carbohydrate(existingFood.getCarbohydrate() + foodRequestJson.getCarbohydrate() * foodRequestJson.getWeightFactor())
                .fat(existingFood.getFat() + foodRequestJson.getFat() * foodRequestJson.getWeightFactor())
                .kcal(existingFood.getKcal() + foodRequestJson.getKcal() * foodRequestJson.getWeightFactor())
                .protein(existingFood.getProtein() + foodRequestJson.getProtein() * foodRequestJson.getWeightFactor())
                .weight(existingFood.getWeight() + foodRequestJson.getWeightFactor() * 100);
    }

    private Food createNewFoodItem(Food f, double factor) {
        return new FoodBuilder(f)
                .carbohydrate(f.getCarbohydrate() * factor)
                .fat(f.getFat() * factor)
                .kcal(f.getKcal() * factor)
                .protein(f.getProtein() * factor)
                .weight(f.getWeight() * factor)
                .build();
    }

    public void updateUserMealIds() {
        List<UserMeal> userMealList = getAll();
        for (UserMeal userMeal : userMealList) {
            Map<Long, Food> newFoodMap = new HashMap<>();
            for (Map.Entry<Long, Food> entry : userMeal.getFoodMap().entrySet()) {
                Food food = foodService.findByName(entry.getValue().getName());
                food.setCarbohydrate(entry.getValue().getCarbohydrate());
                food.setKcal(entry.getValue().getKcal());
                food.setWeight(entry.getValue().getWeight());
                food.setProtein(entry.getValue().getProtein());
                food.setFat(entry.getValue().getFat());
                food.setUsed(entry.getValue().isUsed());
                food.setMealNumber(entry.getValue().getMealNumber());
                newFoodMap.put(food.getId(), food);
            }
            userMeal.setFoodMap(newFoodMap);
            update(userMeal, "foodMap", userMeal.getFoodMap());
        }
    }

    @Override
    public boolean updateMealNumber(MyFoodRequestJson json) {
        UserMeal userMeal = getDefaultMeal();
        Food food = userMeal.getFoodMap().get(json.getFoodId());
        if(food!=null){
            food.setMealNumber(json.getTarget());
            update(userMeal,"foodMap",userMeal.getFoodMap());
            return true;
        }
        return false;
    }
}
