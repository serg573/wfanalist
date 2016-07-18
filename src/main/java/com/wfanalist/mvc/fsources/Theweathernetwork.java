package com.wfanalist.mvc.fsources;

import com.wfanalist.mvc.jspmodel.ChartsDataList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by serg on 27.05.2016.
 */

@Repository("Theweathernetwork")
public class Theweathernetwork extends AbstractSourse implements ConnectedSource {

    //curernt
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

    private WebDriver driver;

    //@Autowired
    //private ApplicationContext appContext;

    public Theweathernetwork() {

        //PropertyPlaceholderConfigurer properties = (PropertyPlaceholderConfigurer) appContext.getBean("propertyConfigurer");

        if (System.getProperty("os.name").equals("Linux")) {
            System.setProperty("phantomjs.binary.path", "/home/serg/Downloads/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
        } else {
            System.setProperty("phantomjs.binary.path", "E:\\Tools\\phantomjs.exe");
        }

        driver = new PhantomJSDriver();

    }

    public boolean parseReference(String url) {

        final boolean rezParsingTrue = true;
        final boolean rezParsingFalse = false;

        //String url = "http://www.theweathernetwork.com/ua/weather/kyiv-kiev-city/kiev?wx_auto_reload=forecasts:%20city%20page:%20auto%20refresh"; //  - e.g. - Kiev

        driver.get(url);

        String str = driver.getPageSource();

        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        Document document = Jsoup.parse(str);

        //Current
        Element fElement = document.select("div.temperature-area.clearfix").first();

        temperature = temperatureToInt(fElement.select("p.temperature").first().text());
        today = temperature;
        //we don't have temperature and temperature_l here. Only current temperature

        feelsLike = temperatureToInt(fElement.select("p.mcity-feels-like").first().select("span").first().text());

        Element detailedMetrics = document.getElementById("detailed-metrics");

        humidity = temperatureToInt(detailedMetrics.select("div.humidity").first().select("span").first().text());
        wind = detailedMetrics.select("div.wind.first").first().select("span").first().text();


        //Forecast
        Element divForWeather = document.getElementById("seven-days");
        one_day = temperatureToInt(divForWeather.select("div.day_1").first().select("div.chart-daily-temp.seven_days_metric_c").first().text());
        two_days = temperatureToInt(divForWeather.select("div.day_2").first().select("div.chart-daily-temp.seven_days_metric_c").first().text());
        three_days = temperatureToInt(divForWeather.select("div.day_3").first().select("div.chart-daily-temp.seven_days_metric_c").first().text());
        four_days = temperatureToInt(divForWeather.select("div.day_4").first().select("div.chart-daily-temp.seven_days_metric_c").first().text());

        one_day_l = temperatureToInt(divForWeather.select("div.day_1").first().select("div.chart-daily-temp-low.seven_days_metric_c").first().text());
        two_days_l = temperatureToInt(divForWeather.select("div.day_2").first().select("div.chart-daily-temp-low.seven_days_metric_c").first().text());
        three_days_l = temperatureToInt(divForWeather.select("div.day_3").first().select("div.chart-daily-temp-low.seven_days_metric_c").first().text());
        four_days_l = temperatureToInt(divForWeather.select("div.day_4").first().select("div.chart-daily-temp-low.seven_days_metric_c").first().text());

        fileFullName1today = fElement.select("div.weather-icon").first().select("img").first().attr("src");

        String[] ss = fileFullName1today.split("/");
        fileName1today = ss[ss.length-1];

        fillTheIconName(fileName1today);

        parsingOkay = true;

        return rezParsingTrue;

    }

    private void fillTheIconName(String fileName1today) {

        if (fileName1today.equals("1.png")) {
            iconName = IconWfName.ICONS_SUN;
        }else if (fileName1today.equals("2.png") || fileName1today.equals("3.png") || fileName1today.equals("4.png")){
            iconName = IconWfName.ICONS_SUN_CLOUD;
        }else if (fileName1today.equals("5.png")){
            iconName = IconWfName.ICONS_SMAL_RAIN;
        }else if (fileName1today.equals("6.png")){
            iconName = IconWfName.ICONS_SUN_CLOUD_SMAL_RAIN;
        }else if (fileName1today.equals("7.png")){
            iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
        }else if (fileName1today.equals("8.png")){
            iconName = IconWfName.ICONS_CLOUD;
        }else if (fileName1today.equals("9.png")){
            iconName = IconWfName.ICONS_SMAL_RAIN;
        }else if (fileName1today.equals("10.png")){
            iconName = IconWfName.ICONS_BIG_RAIN;
        }else if (fileName1today.equals("11.png")){
            iconName = IconWfName.ICONS_THUNDERSTORM_BIG_RAIN;
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
