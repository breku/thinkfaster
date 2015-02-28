package com.kcal.controller.food;

import com.kcal.controller.AbstractController;
import com.kcal.model.Food;
import com.kcal.model.UserMeal;
import com.kcal.model.UserMealSum;
import com.kcal.model.UserProfile;
import com.kcal.model.json.FoodRequestJson;
import com.kcal.model.json.FoodResponseJson;
import com.kcal.model.json.FoodStatus;
import com.kcal.service.UserMealService;
import com.kcal.service.UserMealSumService;
import com.kcal.service.database.FoodService;
import com.kcal.service.database.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Breku
 * Date: 2014-09-13
 */
@Controller
public class FoodController extends AbstractController {

    private FoodService foodService;


    private UserMealSumService userMealSumService;

    private UserMealService userMealService;

    private UserService userService;

    @Autowired
    public FoodController(FoodService foodService, UserMealSumService userMealSumService, UserMealService userMealService, UserService userService) {
        this.foodService = foodService;
        this.userMealSumService = userMealSumService;
        this.userMealService = userMealService;
        this.userService = userService;
    }

    @RequestMapping("/food")
    @Override
    public ModelAndView initView(Model model) {
        UserMealSum userMealSum = userMealSumService.getUserMealSum();
        UserProfile userProfile = userService.getUserProfile();
        List<Food> foodList = foodService.getAll();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("foodList", foodList);
        model.addAttribute("userMealSum", userMealSum);
        return new ModelAndView("food/food", model.asMap());
    }


    @RequestMapping("/json/food")
    @ResponseBody
    public List<Food> foodList(Model model) {
        List<Food> allFoodList = foodService.getAll();
        Map<Long, Food> foodMap = userMealService.getDefaultMeal().getFoodMap();
        List<Food> userFood = new ArrayList<>(foodMap.values());
        userFood.addAll(allFoodList);
        return userFood;
    }


    @RequestMapping(value = "/food/remove", method = RequestMethod.POST)
    @ResponseBody
    public FoodResponseJson remove(@RequestBody FoodRequestJson foodRequestJson) {
        UserMeal defaultMeal = userMealService.getDefaultMeal();
        FoodStatus foodStatus = userMealService.removeFoodFromUserMeal(defaultMeal, foodRequestJson);
        UserMealSum userMealSum = userMealSumService.getUserMealSum();
        UserProfile userProfile = userService.getUserProfile();
        return FoodResponseJson.FoodResponseBuilder.get()
                .food(defaultMeal.getFoodMap().get(foodRequestJson.getFoodId()))
                .responseStatus(foodStatus.getMessage())
                .userMealSum(userMealSum)
                .userProfile(userProfile)
                .build();
    }


    @RequestMapping(value = "/food/add", method = RequestMethod.POST)
    @ResponseBody
    public FoodResponseJson add(@RequestBody FoodRequestJson foodRequestJson) {
        UserMeal defaultMeal = userMealService.getDefaultMeal();
        FoodStatus foodStatus = userMealService.addFoodToUserMeal(defaultMeal, foodRequestJson);
        UserMealSum userMealSum = userMealSumService.getUserMealSum();
        UserProfile userProfile = userService.getUserProfile();
        return FoodResponseJson.FoodResponseBuilder.get()
                .food(defaultMeal.getFoodMap().get(foodRequestJson.getFoodId()))
                .responseStatus(foodStatus.getMessage())
                .userMealSum(userMealSum)
                .userProfile(userProfile)
                .build();
    }


}
