package com.kcal.controller.security;

import com.kcal.annotation.Loggable;
import com.kcal.controller.AbstractController;
import com.kcal.model.Registration;
import com.kcal.service.database.security.UserService;
import com.kcal.validator.RegisterUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * User: Breku
 * Date: 2014-09-17
 */
@Controller
public class RegistrationController extends AbstractController {


    private UserService userService;

    private RegisterUserValidator validator;

    @Autowired
    public RegistrationController(UserService userService, RegisterUserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @Override
    public ModelAndView initView(Model model) {
        Registration registration = new Registration();
        model.addAttribute("registrationModelAttribute", registration);
        return new ModelAndView("security/registerForm", model.asMap());
    }

    @Loggable
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("registrationModelAttribute") @Valid Registration registration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("security/registerForm");
        }

        userService.registerNewUser(registration);
        return new ModelAndView("redirect:/index");
    }
}
