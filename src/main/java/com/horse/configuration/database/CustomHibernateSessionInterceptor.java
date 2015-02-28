package com.horse.configuration.database;

import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;

/**
 * Created by brekol on 23.11.14.
 */
public class CustomHibernateSessionInterceptor extends OpenSessionInViewInterceptor {

    public CustomHibernateSessionInterceptor() {
        setFlushMode(FLUSH_AUTO);
    }

}
