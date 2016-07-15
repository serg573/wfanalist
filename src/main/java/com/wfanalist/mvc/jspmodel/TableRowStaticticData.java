package com.wfanalist.mvc.jspmodel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by serg on 7/14/16.
 */

public class TableRowStaticticData {

    private String sDate;
    private String realTemp;
    private String temp1day;
    private String temp2days;
    private String temp3days;
    private String temp4days;

    public TableRowStaticticData(Date date, int realTemp, int temp1day, int temp2days, int temp3days, int temp4days) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        this.sDate = dateFormat.format(date);
        this.realTemp = realTemp > 0 ? "+"+realTemp : ""+realTemp;
        this.temp1day = temp1day > 0 ? "+"+temp1day : ""+temp1day;
        this.temp2days = temp2days > 0 ? "+"+temp2days : ""+temp2days;
        this.temp3days = temp3days > 0 ? "+"+temp3days : ""+temp3days;
        this.temp4days = temp4days > 0 ? "+"+temp4days : ""+temp4days;

    }

    public String getSDate() {
        return sDate;
    }

    public String getRealTemp() {
        return realTemp;
    }

    public String getTemp1day() {
        return temp1day;
    }

    public String getTemp2days() {
        return temp2days;
    }

    public String getTemp3days() {
        return temp3days;
    }

    public String getTemp4days() {
        return temp4days;
    }
}
