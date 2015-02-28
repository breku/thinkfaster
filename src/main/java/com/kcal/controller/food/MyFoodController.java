package com.kcal.controller.food;

import com.kcal.controller.AbstractController;
import com.kcal.model.UserProfile;
import com.kcal.model.json.MyFoodRequestJson;
import com.kcal.model.utils.MealNumber;
import com.kcal.service.UserMealService;
import com.kcal.service.database.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Controller
public class MyFoodController extends AbstractController {

    private UserService userService;

    private UserMealService userMealService;


    @Autowired
    public MyFoodController(UserService userService, UserMealService userMealService) {
        this.userService = userService;
        this.userMealService = userMealService;
    }

    @RequestMapping("/user/food/my")
    @Override
    public ModelAndView initView(Model model) {

        UserProfile userProfile = userService.getUserProfile();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("foodEntrySetDefault", userMealService.getFoodMapFromDefaultMeal(MealNumber.DEFAULT).entrySet());

        for(int i =0; i<userProfile.getMealsPerDay();i++){
            model.addAttribute("foodEntrySetMeal" + i, userMealService.getFoodMapFromDefaultMeal(MealNumber.values()[i]).entrySet());
        }
        return new ModelAndView("/food/user/myFood", model.asMap());
    }

    @RequestMapping(value = "/user/food/my/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity add(@RequestBody MyFoodRequestJson json) {
        boolean result = userMealService.updateMealNumber(json);
        if(result){
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }


}
