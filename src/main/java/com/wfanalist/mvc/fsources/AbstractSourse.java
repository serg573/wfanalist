package com.wfanalist.mvc.fsources;

/**
 * Created by serg on 25.05.2016.
 */
abstract class AbstractSourse {

    public enum IconWfName {
        ICONS_CLOUD, ICONS_SMAL_RAIN, ICONS_BIG_RAIN, ICONS_THUNDERSTORM, ICONS_THUNDERSTORM_SMAL_RAIN, ICONS_THUNDERSTORM_BIG_RAIN,
        ICONS_SUN, ICONS_SUN_CLOUD, ICONS_SUN_CLOUD_SMAL_RAIN, ICONS_SUN_CLOUD_BIG_RAIN,
        ICONS_MOON, ICONS_MOON_CLOUD,
        ICONS_QUESTION
    }

    public static int temperatureToInt(String strTemperature) {

        String temp = strTemperature.replaceAll("°", "");

        temp = temp.replaceAll("%", "");
        temp = temp.replaceAll("C", "");
        temp = temp.replaceAll("макс. ", "");
        temp = temp.replaceAll("мин.", "");

        temp = temp.replaceAll("RealFeel®", "");
        temp = temp.replaceAll("Precipitation", "");

        temp = temp.replaceAll(" ", "");

        return Integer.parseInt(temp);
    }

}
