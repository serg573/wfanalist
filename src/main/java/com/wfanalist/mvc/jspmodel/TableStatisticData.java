package com.wfanalist.mvc.jspmodel;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.model.Cities;
import com.wfanalist.mvc.model.Forecasts_history;
import com.wfanalist.mvc.model.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by serg on 7/14/16.
 */

@Repository("TableStatisticData")
@Scope("prototype")
public class TableStatisticData extends AbstractDataClass {

    private Sources source;
    private Cities city;
    private boolean hasData = false;
    private String tabName;
    private String strActive1;
    private String strActive2;

    private List<TableRowStaticticData> rowList;

    @Autowired
    WFanalistDAO wfanalistDAO;

    public TableStatisticData() {

        tabName = "";
        strActive1 = "";
        strActive2 = "";

        rowList = new ArrayList<TableRowStaticticData>();

    }

    public boolean fillTheData(Cities city, Sources source, String strActive) {

        this.city = city;
        this.source = source;
        this.tabName = "tab"+source.getId();
        this.strActive1 = strActive;
        this.strActive2 = strActive.equals("") ? "" : " active";

        Date dateMonthAgo = getDateMonthAgo();

        List<Forecasts_history> forecasts_historyList = wfanalistDAO.historyListDateCitySourse(dateMonthAgo, this.city, this.source);

        Calendar calendar = Calendar.getInstance();
        Forecasts_history crHist;

        for (int i=0; i<forecasts_historyList.size(); i++) {

            if (i<=3) {continue;} // minus days of forecast

            crHist = forecasts_historyList.get(i);

            rowList.add(new TableRowStaticticData(crHist.getDate(), crHist.getToday(), crHist.getOne_day(), crHist.getTwo_days(), crHist.getThree_days(), crHist.getFour_days()));

            hasData = true;

        }

        return hasData;
    }

    public List<TableRowStaticticData> getRowList() {
        return rowList;
    }

    public String getTabName() {
        return tabName;
    }

    public Sources getSource() {
        return source;
    }

    public String getStrActive1() {
        return strActive1;
    }

    public String getStrActive2() {
        return strActive2;
    }
}
