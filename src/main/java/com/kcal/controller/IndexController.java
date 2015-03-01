package com.kcal.controller;

import com.kcal.model.User;
import com.kcal.service.database.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Controller
public class IndexController extends AbstractController {


    UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/index", "/"})
    @Override
    public ModelAndView initView(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute(userList);
        return new ModelAndView("index/index",model.asMap());
    }


}
