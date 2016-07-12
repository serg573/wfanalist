package com.wfanalist.mvc.fsources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;

/**
 * Created by serg on 25.05.2016.
 */

@Repository("Sinoptik")
public class Sinoptik extends AbstractSourse implements ConnectedSource {

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

    public Sinoptik() {
    }

    public boolean parseReference(String url) {

        //String url = "https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%BA%D0%B8%D0%B5%D0%B2"; //  - e.g. - Kiev

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
        String htmlTable = elTables.first().html();

        Elements elementsTr = elTables.first().getElementsByTag("tr");

        Element trTemperature = elementsTr.get(3);
        Element trFilsLike = elementsTr.get(4);
        Element trHumidity = elementsTr.get(6);
        Element trWind = elementsTr.get(7);

        int count = 0, numColumn = -1;

        Elements tdTemperaturies = trTemperature.select("td");
        for (Element tdTemp: tdTemperaturies){

            if (tdTemp.hasClass("cur") == true) {
                numColumn = count;
                temperature = temperatureToInt(tdTemp.text());
                break;
            }
            count++;
        }

        if (numColumn == -1) {
            return rezParsingFalse;
        }

        //Current FeelsLike
        feelsLike = temperatureToInt(trFilsLike.select("td").get(numColumn).text());

        //Current Wind
        wind = trWind.select("td").get(numColumn).text();

        //Current Humidity
        humidity = temperatureToInt(trHumidity.select("td").get(numColumn).text());


        //Temperature for history
        Elements divTemperaturies = document.select("div.temperature");


        today = temperatureToInt(divTemperaturies.get(0).select("div.max").text());
        one_day = temperatureToInt(divTemperaturies.get(1).select("div.max").text());
        two_days = temperatureToInt(divTemperaturies.get(2).select("div.max").text());
        three_days = temperatureToInt(divTemperaturies.get(3).select("div.max").text());
        four_days = temperatureToInt(divTemperaturies.get(4).select("div.max").text());

        today_l = temperatureToInt(divTemperaturies.get(0).select("div.min").text());
        one_day_l = temperatureToInt(divTemperaturies.get(1).select("div.min").text());
        two_days_l = temperatureToInt(divTemperaturies.get(2).select("div.min").text());
        three_days_l = temperatureToInt(divTemperaturies.get(3).select("div.min").text());
        four_days_l = temperatureToInt(divTemperaturies.get(4).select("div.min").text());

        temperature = today;
        temperature_l = today_l;

        fileFullName1today = document.select("div.weatherIco").get(0).select("img").get(0).attr("src");

        String[] ss = fileFullName1today.split("/");
        fileName1today = ss[ss.length-1];

        fillTheIconName(fileName1today);

        return rezParsingTrue;
    }

    private void fillTheIconName(String fileName1today) {

        if (fileName1today.equals("d000.gif")) {
            //солнце
            iconName = IconWfName.ICONS_SUN;
        } else if (fileName1today.equals("d500.gif")) {
            //солнце за очень легкими облаками
            iconName = IconWfName.ICONS_SUN;
        } else if (fileName1today.equals("d100.gif")) {
            //солнце за облачком
            iconName = IconWfName.ICONS_SUN_CLOUD;
        } else if (fileName1today.equals("d200.gif")) {
            iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
        } else if (fileName1today.equals("d300.gif")) {
            iconName = IconWfName.ICONS_SUN_CLOUD_BIG_RAIN;
        } else if (fileName1today.equals("d400.gif")) {
            iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
        } else if (fileName1today.equals("d600.gif")) {
            iconName = IconWfName.ICONS_THUNDERSTORM_SMAL_RAIN;
        } else if (fileName1today.equals("d240.gif")) {
            iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
        } else {
            iconName = IconWfName.ICONS_QUESTION;
        }

        //https://sinst.fwdcdn.com/img/weatherImg/m/d000.gif + солнце
        //https://sinst.fwdcdn.com/img/weatherImg/m/d100.gif + солнце за облачком
        //https://sinst.fwdcdn.com/img/weatherImg/m/d200.gif + солнце в облаках
        //https://sinst.fwdcdn.com/img/weatherImg/m/d300.gif + солнце за серыми облаками
        //https://sinst.fwdcdn.com/img/weatherImg/m/d400.gif + серые облака

        //https://sinst.fwdcdn.com/img/weatherImg/m/d500.gif + солнце за очень легкими облаками
        //https://sinst.fwdcdn.com/img/weatherImg/m/d600.gif - тучи серые, но не слишком
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
