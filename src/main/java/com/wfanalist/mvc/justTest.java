package com.wfanalist.mvc;

import com.wfanalist.mvc.fsources.Gismeteo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.*;
//import org.openqa.selenium.htmlunit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by serg on 24.05.2016.
 */
public class justTest {

    //current
    public static int temperature = -100;
    public static int humidity = -100;
    public static int feelsLike = -100;
    public static String wind = "-100";

    //forecast
    public static int today = -100;
    public static int one_day = -100;
    public static int two_days = -100;
    public static int three_days = -100;
    public static int four_days = -100;

    //
    public Date date = new Date();
    public String htmlTable;

    //private PhantomJSDriver driver;

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
        calendar.add(Calendar.DATE, -30);

        Date dateMonthAgo = calendar.getTime();

        int df = 1;

        //TimeZone.getTimeZone("Europe/Kiev")

        /*
        String url = "http://www.accuweather.com/en/ua/kyiv/324505/daily-weather-forecast/324505?lang=en-us"; //  - e.g. - Kiev

        Document document;
        try {
            document = Jsoup.connect(url).userAgent("Chrome").get();
        } catch (IOException e) {
            e.printStackTrace();
            return;
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

        //Current
        Element elDayNight = document.getElementById("detail-day-night");

        int today_temp = temperatureToInt(elDayNight.select("span.temp").first().text());

        Elements elSpans = elDayNight.select("span.realfeel");

        feelsLike = temperatureToInt(elSpans.get(0).text());
        humidity = temperatureToInt(elSpans.get(1).text());

        wind = document.select("div.rt").get(0).select("strong").get(1).text();



        int df=0;
        */

        /* // working code
        System.setProperty("webdriver.chrome.driver", "E:\\Tools\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        */



        //document.getElementById("main-container")
        //document.getElementById("main-content")
//        document.getElementById("weather-info")


    }

    private static int temperatureToInt(String strTemperature) {

        /*
        String temp = strTemperature.replaceAll("°", "");

        temp = temp.replaceAll("%", "");
        temp = temp.replaceAll("C", "");
        temp = temp.replaceAll("макс. ", "");

        temp = temp.replaceAll("RealFeel®", "");
        temp = temp.replaceAll("Precipitation", "");

        temp = temp.replaceAll(" ", "");

        return Integer.parseInt(temp);
        */
        return 0;
    }


}
