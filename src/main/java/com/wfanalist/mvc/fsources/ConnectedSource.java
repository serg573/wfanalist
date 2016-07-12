package com.wfanalist.mvc.fsources;

/**
 * Created by serg on 24.05.2016.
 */
public interface ConnectedSource {

    boolean parseReference(String url);

    //for history
    int getToday();
    int getOne_day();
    int getTwo_days();
    int getThree_days();
    int getFour_days();

    //current indicators
    int getTemperature();
    int getTemperature_l();
    int getHumidity();
    int getFeelsLike();
    String getWind();

    int getToday_l();
    int getOne_day_l();
    int getTwo_days_l();
    int getThree_days_l();
    int getFour_days_l();

    AbstractSourse.IconWfName getIconName();
}
