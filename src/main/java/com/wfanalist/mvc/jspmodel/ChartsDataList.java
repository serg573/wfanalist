package com.wfanalist.mvc.jspmodel;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.model.Cities;
import com.wfanalist.mvc.model.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serg on 6/13/16.
 */

@Repository("ChartsDataList")
public class ChartsDataList {

    private List<ChartsData> lData;
    private Cities city;

    @Autowired
    WFanalistDAO wfanalistDAO;

    @Autowired
    private ApplicationContext appContext;

    public ChartsDataList() {
        this.lData = new ArrayList<ChartsData>();
    }

    public void fillListOfCharts(Cities city) {

        this.city = city;

        ChartsData crChData;

        lData.clear();

        List<Sources> sourcesList = wfanalistDAO.sourcesList();
        for (Sources cSource: sourcesList) {

            crChData = (ChartsData) appContext.getBean("ChartsData");

            if (crChData.fillTheData(city, cSource) == true) {
                lData.add(crChData);
            }
        }

    }

    public List<ChartsData> getlData() {
        return lData;
    }

    public Cities getCity() {
        return city;
    }
}
