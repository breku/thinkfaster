package com.horse.configuration.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * User: Breku
 * Date: 2014-09-21
 */
@Configuration
public class MyMessageSource extends ReloadableResourceBundleMessageSource {

    private static final String BASENAME = "WEB-INF/internationalization/messages";
    private static final String DEFAULT_ENCODING = "UTF-8";

    public MyMessageSource() {
        super();
        setBasename(BASENAME);
        setDefaultEncoding(DEFAULT_ENCODING);
    }

    @Bean
    public MessageSource messageSource() {
        return this;
    }


}
