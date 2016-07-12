package com.wfanalist.mvc.fsources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;

/**
 * Created by serg on 24.05.2016.
 */

@Repository("Gismeteo")
public class Gismeteo extends AbstractSourse implements ConnectedSource {

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

    public Gismeteo() {

    }

    public boolean parseReference(String url) {

        //String url = "https://www.gismeteo.ua/weather-kyiv-4944/busy/";  - e.g.

        final boolean rezParsingTrue = true;
        final boolean rezParsingFalse = false;

        Document document;
        try {
            document = Jsoup.connect(url).userAgent("Chrome").get();
        } catch (IOException e) {
            e.printStackTrace();
            return rezParsingFalse;
        }

        Elements elTables = document.getElementsByTag("table");

        //For history
        htmlTable = elTables.first().html();

        Elements elementsTr = elTables.first().getElementsByTag("tr");

        Element trImg1 = elementsTr.get(1);
        Element trImg2 = elementsTr.get(2);
        Element trTemperature = elementsTr.get(3);
        Element trTemperature_l = elementsTr.get(4);
        Element trHumidity = elementsTr.get(6);
        Element trWind = elementsTr.get(7);
        Element trFilsLike = elementsTr.get(8);

        //Sort out with image name. Here we have 2 images. We have to analyze them
        Elements elementsImg1 = trImg1.select("img");
        Elements elementsImg2 = trImg2.select("img");

        fileFullName1today = elementsImg1.get(0).attr("src");
        fileFullName2today = elementsImg2.get(0).attr("src");

        String[] ss = fileFullName1today.split("/");
        fileName1today = ss[ss.length-1];

        ss = fileFullName2today.split("/");
        fileName2today = ss[ss.length-1];

        fillTheIconName(fileName1today, fileName2today);

        Elements elementsTemperature = trTemperature.select("span.c");
        Elements elementsTemperature_l = trTemperature_l.select("span.c");

        //Temperature for history
        today = temperatureToInt(elementsTemperature.get(1).text());
        one_day = temperatureToInt(elementsTemperature.get(2).text());
        two_days = temperatureToInt(elementsTemperature.get(3).text());
        three_days = temperatureToInt(elementsTemperature.get(4).text());
        four_days = temperatureToInt(elementsTemperature.get(5).text());

        today_l = temperatureToInt(elementsTemperature_l.get(1).text());
        one_day_l = temperatureToInt(elementsTemperature_l.get(2).text());
        two_days_l = temperatureToInt(elementsTemperature_l.get(3).text());
        three_days_l = temperatureToInt(elementsTemperature_l.get(4).text());
        four_days_l = temperatureToInt(elementsTemperature_l.get(5).text());

        //Current temperature
        temperature = today; //
        temperature_l = today_l;

        //Current FeelsLike
        Elements elementsFeelsLike = trFilsLike.select("span.c");
        feelsLike = temperatureToInt(elementsFeelsLike.get(1).text());

        //Current Wind
        Elements elementsWind = trWind.select("span.ms");
        wind = elementsWind.get(1).text();

        //Current Humidity
        Elements elementsHumidity = trHumidity.select("td");
        humidity = temperatureToInt(elementsHumidity.get(0).text());

        parsingOkay = true;

        return rezParsingTrue;

    }

    private void fillTheIconName(String fileName1today, String fileName2today) {

        if (fileName1today.equals("d.c0.gif")) {
            //Sun
            if (fileName2today.equals("d.r0.gif")) {
                iconName = IconWfName.ICONS_SUN;
            } else if (fileName2today.equals("d.r1.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
            } else if (fileName2today.equals("d.r2.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_BIG_RAIN;
            } else if (fileName2today.equals("d.r3.gif") || fileName2today.equals("d.r4.gif")) {
                iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
            } else {
                iconName = IconWfName.ICONS_QUESTION;
            }

        } else if (fileName1today.equals("d.c1.gif")) {
            //Partly cloudy
            if (fileName2today.equals("d.r0.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD;
            } else if (fileName2today.equals("d.r1.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
            } else if (fileName2today.equals("d.r2.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_BIG_RAIN;
            } else if (fileName2today.equals("d.r3.gif") || fileName2today.equals("d.r4.gif")) {
                iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
            } else {
                iconName = IconWfName.ICONS_QUESTION;
            }

        } else if (fileName1today.equals("d.c2.gif")) {
            //Partly cloudy
            if (fileName2today.equals("d.r0.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD;
            } else if (fileName2today.equals("d.r1.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
            } else if (fileName2today.equals("d.r2.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_BIG_RAIN;
            } else if (fileName2today.equals("d.r3.gif") || fileName2today.equals("d.r4.gif")) {
                iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
            } else {
                iconName = IconWfName.ICONS_QUESTION;
            }

        } else if (fileName1today.equals("d.c3.gif") || (fileName1today.equals("d.c4.gif"))) {
            //Cloudy
            if (fileName2today.equals("d.r0.gif")) {
                iconName = IconWfName.ICONS_CLOUD;
            } else if (fileName2today.equals("d.r1.gif")) {
                iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
            } else if (fileName2today.equals("d.r2.gif")) {
                iconName = IconWfName.ICONS_BIG_RAIN;
            } else if (fileName2today.equals("d.r3.gif") || fileName2today.equals("d.r4.gif")) {
                iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
            } else {
                iconName = IconWfName.ICONS_QUESTION;
            }
        } else {
            iconName = IconWfName.ICONS_QUESTION;
        }

        //First image:
        //https://s1.gismeteo.ua/static/images/icons/old/d.c0.gif - sun
        //https://s2.gismeteo.ua/static/images/icons/old/d.c1.gif - Partly cloudy
        //https://s2.gismeteo.ua/static/images/icons/old/d.c2.gif - Cloudy
        //https://s2.gismeteo.ua/static/images/icons/old/d.c3.gif - too cloudy
        //https://s2.gismeteo.ua/static/images/icons/old/d.c4.gif - too cloudy

        //Second image:
        //https://s2.gismeteo.ua/static/images/icons/old/d.r0.gif - sun
        //https://s2.gismeteo.ua/static/images/icons/old/d.r1.gif - small rain
        //https://s2.gismeteo.ua/static/images/icons/old/d.r2.gif - rain
        //https://s2.gismeteo.ua/static/images/icons/old/d.r3.gif - thunderstorm
        //https://s2.gismeteo.ua/static/images/icons/old/d.r4.gif - thunderstorm

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
