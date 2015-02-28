package com.horse.controller.security;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.horse.controller.AbstractController;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Breku
 * Date: 2014-09-23
 */
@Controller
public class AdminSettingsController extends AbstractController {
    private static final String DEFAULT_LOGGER_NAME = "com.horse";

    private static final Logger DEFAULT_LOGGER = (Logger) LoggerFactory.getLogger(DEFAULT_LOGGER_NAME);

    @RequestMapping("/admin/settings")
    @Override
    public ModelAndView initView(Model model) {
        String currentLoggerLevel = DEFAULT_LOGGER.getLevel().toString();
        model.addAttribute("currentLoggerLevel", currentLoggerLevel);
        return new ModelAndView("/security/settings/settings", model.asMap());
    }

//    @Loggable(level = "info")
    @RequestMapping(value = "/admin/settings/logger/{level}", method = RequestMethod.POST)
    public ModelAndView changeRootLoggerLevel(@PathVariable("level") String level, Model model) {
        Level newLevel = Level.toLevel(level);
        DEFAULT_LOGGER.setLevel(newLevel);
        return new ModelAndView("redirect:/admin/settings", model.asMap());
    }


}
