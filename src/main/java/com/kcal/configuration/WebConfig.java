package com.kcal.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: Breku
 * Date: 24.08.14
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

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


//    @Bean
//    public CommonsMultipartResolver getMultipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(500000);
//        return multipartResolver;
//    }
}
