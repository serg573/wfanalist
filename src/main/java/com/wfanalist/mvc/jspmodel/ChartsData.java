package com.wfanalist.mvc.jspmodel;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.model.Cities;
import com.wfanalist.mvc.model.Forecasts_history;
import com.wfanalist.mvc.model.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by serg on 6/13/16.
 */

@Repository("ChartsData")
@Scope("prototype")
public class ChartsData {

    private String rows = "";
    private Sources source;
    private Cities city;
    private boolean hasData = false;

    private String dataName = "";
    private String chartName = "";
    private String chartDivName = "";

    private int numDays = 34;

    @Autowired
    WFanalistDAO wfanalistDAO;

    public ChartsData() {
    }

    public boolean fillTheDaya(Cities city, Sources source) {

        this.city = city;
        this.source = source;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
        calendar.add(Calendar.DATE, -numDays);

        Date dateMonthAgo = calendar.getTime();

        List<Forecasts_history> forecasts_historyList = wfanalistDAO.historyListDateCitySourse(dateMonthAgo, this.city, this.source);

        Forecasts_history crHist;
        String sDay;

        for (int i=0; i<forecasts_historyList.size(); i++) {

            if (i<=3) {continue;}

            if (rows.equals("") == false) {rows = rows+", ";}

            crHist = forecasts_historyList.get(i);

            calendar.setTime(crHist.getDay());
            sDay = "new Date("+calendar.get(calendar.YEAR)+", "+calendar.get(calendar.MONTH)+", "+calendar.get(calendar.DAY_OF_MONTH)+")";

            rows = rows + "["+sDay+", "+crHist.getToday()+", "+forecasts_historyList.get(i-1).getOne_day()+", "+forecasts_historyList.get(i-1).getTwo_days()+", "+forecasts_historyList.get(i-1).getThree_days()+", "+forecasts_historyList.get(i-1).getFour_days()+"]";

            hasData = true;

        }

        if (hasData == true) {

            dataName = "data"+source.getId();
            chartName = "chart"+source.getId();
            chartDivName = "chart_div"+source.getId();

        }


        return hasData;

    }

    public String getRows() {
        return rows;
    }

    public Sources getSource() {
        return source;
    }

    public Cities getCity() {
        return city;
    }

    public boolean getHasData() {
        return hasData;
    }

    public String getDataName() {
        return dataName;
    }

    public String getChartName() {
        return chartName;
    }

    public String getChartDivName() {
        return chartDivName;
    }
}
