package com.kcal.model.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * User: Breku
 * Date: 2014-10-04
 */
public abstract class AbstractBuilder<T> {

    protected static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = new DecimalFormatSymbols(Locale.ENGLISH);
    protected static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.##", DECIMAL_FORMAT_SYMBOLS);

    protected double parseDouble(double d) {
        return Double.parseDouble(DEFAULT_DECIMAL_FORMAT.format(d));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBuilder.class);


    public AbstractBuilder(T obj){
        this.obj = obj;
    }

    public AbstractBuilder(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        try {
            this.obj =  ((Class<T>) paramType.getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("Error creating new builder",e);
        } catch (IllegalAccessException e) {
            LOGGER.error("Error creating new builder",e);
        }
    }

    protected T obj;


    public T build() {
        return obj;
    }
}
