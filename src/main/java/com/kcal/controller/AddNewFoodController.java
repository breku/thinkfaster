package com.kcal.controller;

import com.kcal.model.Food;
import com.kcal.service.database.CountTypeService;
import com.kcal.service.database.FoodService;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-13
 */
@Controller
@RequestMapping(value = "/add")
public class AddNewFoodController extends AbstractController {

    @Autowired
    public AddNewFoodController(FoodService foodService, CountTypeService countTypeService) {
        this.foodService = foodService;
        this.countTypeService = countTypeService;
    }

    private FoodService foodService;

    private CountTypeService countTypeService;

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    @Override
    public ModelAndView initView(Model model) {
        model.addAttribute("foodModelAttribute", new Food());
        List<String> countTypeList = (List<String>) CollectionUtils.collect(countTypeService.getAll(), new BeanToPropertyValueTransformer("name"));
        model.addAttribute("countTypes", countTypeList);
        return new ModelAndView("addFood/addFood", model.asMap());
    }


    @RequestMapping(value = "/food", method = RequestMethod.POST)
    public ModelAndView addNewFood(@ModelAttribute("foodModelAttribute") Food food) {
        foodService.save(food);
        return new ModelAndView("redirect:/add/food");
    }


}
