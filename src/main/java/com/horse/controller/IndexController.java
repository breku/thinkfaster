package com.horse.controller;

import com.horse.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Controller
public class IndexController extends AbstractController {

    private UserDao userDao;


    @Autowired
    public IndexController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping({"/index", "/"})
    @Override
    public ModelAndView initView(Model model) {
        return new ModelAndView("index/index");
    }


}
