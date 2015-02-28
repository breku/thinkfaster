package com.kcal.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Breku
 * Date: 05.07.14
 */
public abstract class AbstractController {

    public abstract ModelAndView initView(Model model);
}
