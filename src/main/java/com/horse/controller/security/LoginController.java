package com.horse.controller.security;

import com.horse.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView initView(Model model, HttpServletRequest request) {
        model.addAttribute("userModelAttribute", new User());
        ModelAndView modelAndView = new ModelAndView("security/loginForm", model.asMap());
        return modelAndView;
    }
}
