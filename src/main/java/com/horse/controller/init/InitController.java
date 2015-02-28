package com.horse.controller.init;

import com.horse.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Breku
 * Date: 2014-09-21
 */
@RequestMapping(value = "/add")
@Controller
public class InitController extends AbstractController {


    @RequestMapping("/countTypes")
    @Override
    public ModelAndView initView(Model model) {

        return new ModelAndView("redirect:/admin/settings");
    }
}
