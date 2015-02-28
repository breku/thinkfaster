package com.horse.controller.horse;

import com.horse.controller.AbstractController;
import com.horse.dao.HorseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by brekol on 15.01.15.
 */
@Controller
public class UserHorsesController  extends AbstractController {

    private HorseDao horseDao;

    @Autowired
    public UserHorsesController(HorseDao horseDao) {
        this.horseDao = horseDao;
    }

    @RequestMapping("/user/horses")
    @Override
    public ModelAndView initView(Model model) {
        return new ModelAndView("horse/user/userHorses");
    }
}
