package com.kcal.controller;

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

    @RequestMapping({"/index", "/"})
    @Override
    public ModelAndView initView(Model model) {
        return new ModelAndView("index/index");
    }


}
