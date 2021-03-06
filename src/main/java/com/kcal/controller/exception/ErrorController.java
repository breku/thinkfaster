package com.kcal.controller.exception;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * User: Breku
 * Date: 2014-09-22
 */
@Controller
public class ErrorController extends AbstractExceptionController {

    private static final Logger LOGGER = Logger.getLogger(ErrorController.class.getName());


    @RequestMapping("/error/{statusCode}")
    public static final ModelAndView error(@PathVariable("statusCode") int statusCode, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        LOGGER.warning(String.format(">> statusCode=%s User=%s ", statusCode, auth.getPrincipal()));
        response.setStatus(statusCode);
        ModelAndView mav = new ModelAndView();
        mav.addObject("statusCode", statusCode);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
