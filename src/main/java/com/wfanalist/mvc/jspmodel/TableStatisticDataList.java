package com.wfanalist.mvc.jspmodel;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.model.Cities;
import com.wfanalist.mvc.model.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serg on 7/14/16.
 */

@Repository("TableStatisticDataList")
public class TableStatisticDataList {

    private List<TableStatisticData> tData;
    private Cities city;

    @Autowired
    WFanalistDAO wfanalistDAO;

    @Autowired
    private ApplicationContext appContext;

    public TableStatisticDataList() {
        this.tData = new ArrayList<TableStatisticData>();
    }

    public void fillListOfTables(Cities city){

        this.city = city;

        TableStatisticData curTable;
        String sActive = " class=\"active\"";

        tData.clear();

        List<Sources> sourcesList = wfanalistDAO.sourcesList();
        for (Sources cSource: sourcesList) {

            curTable = (TableStatisticData) appContext.getBean("TableStatisticData");

            if (curTable.fillTheData(city, cSource, sActive) == true) {
                tData.add(curTable);
            }

            sActive = "";

        }

    }

    public List<TableStatisticData> gettData() {
        return tData;
    }
}
