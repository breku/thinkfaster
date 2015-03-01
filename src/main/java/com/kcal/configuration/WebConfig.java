package com.kcal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.logging.Logger;

/**
 * User: Breku
 * Date: 24.08.14
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = Logger.getLogger(WebConfig.class.getName());

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        LOGGER.info(">> WebConfig called");
        configurer.ignoreAcceptHeader(true).
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON).
                mediaType("x-www-form-urlencoded", MediaType.APPLICATION_FORM_URLENCODED);
        LOGGER.info("<< WebConfig finished");
    }


}
