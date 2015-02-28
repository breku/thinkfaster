package com.horse.controller.i18n;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * User: Breku
 * Date: 2014-09-21
 */
@Controller
public class JavaScriptController {

    /**
     * Normally messages are located under WEB-INF/internationalization foler
     * Messages have to be copied to classes folder by war plugin, to be in classpath.
     */
    private static final String MESSAGE_BUNDLE_PATH = "internationalization/messages";

    @RequestMapping(value = "strings.js")
    public ModelAndView strings(HttpServletRequest request, HttpServletResponse response) {
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_BUNDLE_PATH, locale);
        ModelAndView modelAndView = new ModelAndView("/i18n/stringsForJS", "keys", bundle.getKeys());
        response.setContentType("application/javascript");
        return modelAndView;
    }
}
