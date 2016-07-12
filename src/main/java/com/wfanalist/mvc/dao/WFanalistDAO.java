package com.wfanalist.mvc.dao;

import com.wfanalist.mvc.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 12.05.2016.
 */
public interface WFanalistDAO {

    //Current_indicators
    public List<Current_indicators> currentIndicatorsList();
    public List<Current_indicators> getcurrentIndicatorsListByCity(Cities city);
    public Current_indicators save(Current_indicators cIndicator);


    //Forecasts_history
    public List<Forecasts_history> historyList();
    public List<Forecasts_history> historyListOnDate(Date date);
    public List<Forecasts_history> historyListDateCitySourse(Date date, Cities city, Sources source);
    public Forecasts_history save(Forecasts_history f_history);


    //References
    public List<References> referencesList();


    //Cities
    public Cities findCityById(long id);
    public Cities findCityByName(String name_ru);
    public Cities save(Cities sity);

    //Sources
    public List<Sources> sourcesList();
    public Sources findSourceByName(String name);
    public Sources save(Sources source);

    //Task_errors
    public Task_errors save(Task_errors error);

}
