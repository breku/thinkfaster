package com.kcal.controller.security;

import com.kcal.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Breku
 * Date: 2014-09-23
 */
@Controller
public class AdminSettingsController extends AbstractController {
    private static final String DEFAULT_LOGGER_NAME = "com.kcal";

    private static final Logger DEFAULT_LOGGER = Logger.getLogger(AdminSettingsController.class.getName());

    @RequestMapping("/admin/settings")
    @Override
    public ModelAndView initView(Model model) {
        String currentLoggerLevel = DEFAULT_LOGGER.getParent().getLevel().toString();
        model.addAttribute("currentLoggerLevel", currentLoggerLevel);
        return new ModelAndView("/security/settings/settings", model.asMap());
    }

    @RequestMapping(value = "/admin/settings/logger/{level}", method = RequestMethod.POST)
    public ModelAndView changeRootLoggerLevel(@PathVariable("level") String level, Model model) {
        Level newLevel = Level.parse(level);
        DEFAULT_LOGGER.getParent().setLevel(newLevel);
        return new ModelAndView("redirect:/admin/settings", model.asMap());
    }


}
