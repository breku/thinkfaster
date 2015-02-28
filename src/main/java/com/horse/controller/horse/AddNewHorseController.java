package com.horse.controller.horse;

import com.horse.controller.AbstractController;
import com.horse.model.Horse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by brekol on 15.01.15.
 */
@Controller
public class AddNewHorseController extends AbstractController {


    @RequestMapping(value = "/user/horses/add",method = RequestMethod.GET)
    @Override
    public ModelAndView initView(Model model) {
        Horse horse = new Horse();
        model.addAttribute("horseModelAttribute", horse);
        return new ModelAndView("horse/add/addNewHorse",model.asMap());
    }



    @RequestMapping(value = "/user/horses/add",method = RequestMethod.POST)
    public ModelAndView addNewHorse(@ModelAttribute("horseModelAttribute") Horse horse){
        return new ModelAndView("redirect:/user/horses");

    }
}
