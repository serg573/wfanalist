package com.wfanalist.mvc.jspmodel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by serg on 7/14/16.
 */

public class AbstractDataClass {

    private int numDays = 34;

    protected Date getDateMonthAgo() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
        calendar.add(Calendar.DATE, -numDays);

        return calendar.getTime();

    }

}
