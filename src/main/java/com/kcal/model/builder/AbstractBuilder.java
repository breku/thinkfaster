package com.kcal.model.builder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(AbstractBuilder.class.getName());


    public AbstractBuilder(T obj) {
        this.obj = obj;
    }

    public AbstractBuilder() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        try {
            this.obj = ((Class<T>) paramType.getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Error creating new builder", e);
        }
    }

    protected T obj;


    public T build() {
        return obj;
    }
}
