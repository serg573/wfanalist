package com.wfanalist.mvc.fsources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;

/**
 * Created by serg on 31.05.2016.
 */

@Repository("Accuweather")
public class Accuweather extends AbstractSourse implements ConnectedSource {

    //current
    private int temperature = -100;
    private int temperature_l = -100;
    private int humidity = -100;
    private int feelsLike = -100;
    private String wind = "-100";

    //forecast
    private int today = -100;
    private int one_day = -100;
    private int two_days = -100;
    private int three_days = -100;
    private int four_days = -100;
    private int today_l = -100;
    private int one_day_l = -100;
    private int two_days_l = -100;
    private int three_days_l = -100;
    private int four_days_l = -100;

    private String fileName1today = "";
    private String fileName2today = "";
    private String fileFullName1today = "";
    private String fileFullName2today = "";
    private IconWfName iconName = IconWfName.ICONS_QUESTION;

    //
    private boolean parsingOkay = false;
    private Date date = new Date();
    private String htmlTable;

    public Accuweather() {
    }

    public boolean parseReference(String url) {

        //String url = "http://www.accuweather.com/en/ua/kyiv/324505/daily-weather-forecast/324505?lang=en-us"; //  - e.g. - Kiev

        final boolean rezParsingTrue = true;
        final boolean rezParsingFalse = false;

        Document document;
        try {
            document = Jsoup.connect(url).userAgent("Chrome").get();
        } catch (IOException e) {
            e.printStackTrace();
            return rezParsingFalse;
        }

        Element elFeedTabs = document.getElementById("feed-tabs");
        Elements elStrongTemp = elFeedTabs.select("strong.temp");

        //Forecast
        today = temperatureToInt(elStrongTemp.get(0).text());
        one_day = temperatureToInt(elStrongTemp.get(1).text());
        two_days = temperatureToInt(elStrongTemp.get(2).text());
        three_days = temperatureToInt(elStrongTemp.get(3).text());
        four_days = temperatureToInt(elStrongTemp.get(4).text());

        temperature = today;

        //temperature_l
        temperature_l = temperatureToInt(elFeedTabs.select("span.low").first().text().replace("Lo", ""));

        //Current
        Element elDayNight = document.getElementById("detail-day-night");

        int today_temp = temperatureToInt(elDayNight.select("span.temp").first().text());

        Elements elSpans = elDayNight.select("span.realfeel");

        feelsLike = temperatureToInt(elSpans.get(0).text());
        humidity = temperatureToInt(elSpans.get(1).text());

        wind = document.select("div.rt").get(0).select("strong").get(1).text();


        fileFullName1today = elFeedTabs.select("div.icon").first().attr("class");
        fileName1today = fileFullName1today.replace("icon", "").replace(" ", "");

        fillTheIconName(fileName1today);

        parsingOkay = true;

        return rezParsingTrue;

    }

    private void fillTheIconName(String fileName1today) {

        if (fileName1today.equals("i-17-s")) {
            iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
        } else if (fileName1today.equals("icon i-3-s") || fileName1today.equals("icon i-3-s") || fileName1today.equals("icon i-4-s")) {
            iconName = IconWfName.ICONS_SUN_CLOUD;
        } else if (fileName1today.equals("i-35-s")) {
            iconName = IconWfName.ICONS_MOON_CLOUD;
        } else {
            iconName = IconWfName.ICONS_QUESTION;
        }

    }

    @Override
    public int getToday() {
        return today;
    }
    @Override
    public int getOne_day() {
        return one_day;
    }
    @Override
    public int getTwo_days() {
        return two_days;
    }
    @Override
    public int getThree_days() {
        return three_days;
    }
    @Override
    public int getFour_days() {
        return four_days;
    }

    @Override
    public int getToday_l() {
        return today_l;
    }
    @Override
    public int getOne_day_l() {
        return one_day_l;
    }
    @Override
    public int getTwo_days_l() {
        return two_days_l;
    }
    @Override
    public int getThree_days_l() {
        return three_days_l;
    }
    @Override
    public int getFour_days_l() {
        return four_days_l;
    }

    @Override
    public IconWfName getIconName() {
        return iconName;
    }

    @Override
    public int getTemperature() {
        return temperature;
    }
    @Override
    public int getTemperature_l() {
        return temperature_l;
    }
    @Override
    public int getHumidity() {
        return humidity;
    }
    @Override
    public int getFeelsLike() {
        return feelsLike;
    }
    @Override
    public String getWind() {
        return wind;
    }

}
