package com.horse.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Breku
 * Date: 2014-09-22
 */
@ControllerAdvice
public class GlobalExceptionControllerAdvice extends AbstractExceptionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionControllerAdvice.class);


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        LOGGER.error(">> [url={} user={}]", req.getRequestURL(), auth.getName());
        LOGGER.error(">> ", e);
        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }


}
