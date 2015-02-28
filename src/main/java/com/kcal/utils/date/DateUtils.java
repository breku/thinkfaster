package com.kcal.utils.date;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Breku
 * Date: 07.07.14
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static Date getTodayDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime(); //the midnight, that's the first second of the day.
    }
}
