package com.kcal.controller;

import com.kcal.annotation.Loggable;
import com.kcal.model.UserProfile;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import com.kcal.service.database.security.UserService;
import com.kcal.validator.UserProfileValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * User: Breku
 * Date: 2014-10-04
 */
@Controller
public class UserProfileController extends AbstractController {


    private UserService userService;

    private UserProfileValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public UserProfileController(UserService userService, UserProfileValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Loggable
    @RequestMapping("/user/profile")
    @Override
    public ModelAndView initView(Model model) {
        UserProfile userProfile = userService.getUserProfile();

        model.addAttribute("userProfile", userProfile);
        model.addAttribute("sliderModel", new UserProfileSliderJson(userProfile));
        return new ModelAndView("/profile/profile", model.asMap());
    }


    @RequestMapping(value = "/user/profile/edit/slider", method = RequestMethod.POST)
    public ResponseEntity editProfile(@RequestBody @Valid UserProfileSliderJson json, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        userService.updateProfile(json);
        String responseJson = getJson(userService.getUserProfile());
        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    private String getJson(UserProfile userProfile){
        return new JSONObject()
                .put("protein", (int)userProfile.getProtein())
                .put("carbohydrate", (int)userProfile.getCarbohydrate())
                .put("fat", (int)userProfile.getFat())
                .toString();
    }


    @RequestMapping(value = "/user/profile/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editProfile(@RequestBody @Valid XEditableForm xEditableForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        userService.updateProfile(xEditableForm);
        String responseJson = getJson(userService.getUserProfile());
        return new ResponseEntity<>(responseJson,HttpStatus.OK);
    }


}
