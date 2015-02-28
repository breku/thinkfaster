package com.kcal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Breku
 * Date: 2014-09-22
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loggable {

    /**
     * level of the logged message
     *
     * @return
     */
    public String level() default "debug";

    /**
     * If set to false the arguments of the method and the return object won't be logged
     *
     * @return
     */
    public boolean arguments() default true;

    /**
     * If set to true execution time of the method will be logged
     *
     * @return
     */
    public boolean executionTime() default false;
}
