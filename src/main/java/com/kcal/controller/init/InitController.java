package com.kcal.controller.init;

import com.kcal.controller.AbstractController;
import com.kcal.service.database.CountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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


    private CountTypeService countTypeService;

    @Autowired
    public InitController(CountTypeService countTypeService) {
        this.countTypeService = countTypeService;
    }

    @RequestMapping("/countTypes")
    @Override
    public ModelAndView initView(Model model) {

        return new ModelAndView("redirect:/admin/settings");
    }
}
