package com.wfanalist.mvc.jspmodel;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.model.Cities;
import com.wfanalist.mvc.model.Forecasts_history;
import com.wfanalist.mvc.model.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by serg on 6/13/16.
 */

@Repository("ChartsData")
@Scope("prototype")
public class ChartsData extends AbstractDataClass {

    private String rows = "";
    private Sources source;
    private Cities city;
    private boolean hasData = false;

    private String dataName = "";
    private String chartName = "";
    private String chartDivName = "";

    @Autowired
    WFanalistDAO wfanalistDAO;

    public ChartsData() {
    }

    public boolean fillTheData(Cities city, Sources source) {

        this.city = city;
        this.source = source;

        Date dateMonthAgo = getDateMonthAgo();

        List<Forecasts_history> forecasts_historyList = wfanalistDAO.historyListDateCitySourse(dateMonthAgo, this.city, this.source);

        Calendar calendar = Calendar.getInstance();
        Forecasts_history crHist, crHist1, crHist2, crHist3, crHist4;
        String sDay, sToday, sOneDay, sTwoDays, sThreeDays, sFourDays;
        int nextNumRow;

        for (int i=0; i<forecasts_historyList.size()-4; i++) {

            if (rows.equals("") == false) {rows = rows+", ";}

            nextNumRow = i+1;

            crHist = forecasts_historyList.get(i);

            calendar.setTime(crHist.getDay());
            sDay = "new Date("+calendar.get(calendar.YEAR)+", "+calendar.get(calendar.MONTH)+", "+calendar.get(calendar.DAY_OF_MONTH)+")";

            sToday = ""+crHist.getToday();

            //sometimes dates can be omitted, so:

            //one day before
            calendar.add(Calendar.DATE, -1);
            crHist1 = forecasts_historyList.get(nextNumRow);
            if (calendar.getTime().equals(crHist1.getDay())) {
                sOneDay = ""+crHist1.getOne_day();
                nextNumRow++;
            } else {
                sOneDay = "null";
            }

            //two days before
            calendar.add(Calendar.DATE, -1);
            crHist2 = forecasts_historyList.get(nextNumRow);
            if (calendar.getTime().equals(crHist2.getDay())) {
                sTwoDays = ""+crHist2.getTwo_days();
                nextNumRow++;
            } else {
                sTwoDays = "null";
            }

            //three days before
            calendar.add(Calendar.DATE, -1);
            crHist3 = forecasts_historyList.get(nextNumRow);
            if (calendar.getTime().equals(crHist3.getDay())) {
                sThreeDays = ""+crHist3.getThree_days();
                nextNumRow++;
            } else {
                sThreeDays = "null";
            }

            //four days before
            calendar.add(Calendar.DATE, -1);
            crHist4 = forecasts_historyList.get(nextNumRow);
            if (calendar.getTime().equals(crHist4.getDay())) {
                sFourDays = ""+crHist4.getFour_days();
            } else {
                sFourDays = "null";
            }

            rows = rows + "["+sDay+", "+sToday+", "+sOneDay+", "+sTwoDays+", "+sThreeDays+", "+sFourDays+"]";

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
