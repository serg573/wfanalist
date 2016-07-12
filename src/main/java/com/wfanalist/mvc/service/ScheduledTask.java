package com.wfanalist.mvc.service;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.fsources.ConnectedSource;
import com.wfanalist.mvc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by serg on 26.05.2016.
 */

@Repository("ScheduledTask")
public class ScheduledTask {

    @Autowired
    WFanalistDAO wfanalistDAO;

    @Autowired
    private ApplicationContext appContext;

    @Scheduled(cron = "0 0/10 * * * ?") // fires every 10 minutes
    public void run() {

        Calendar calendar = Calendar.getInstance();
        boolean itsTime = calendar.get(Calendar.HOUR_OF_DAY) >= 13; // data should be written at about 13h (every day)

        List<Current_indicators> current_indicators = wfanalistDAO.currentIndicatorsList();
        List<References> references = wfanalistDAO.referencesList();
        List<Forecasts_history> todayHistory = wfanalistDAO.historyListOnDate(getStartOfDay(new Date()));

        for (References ref: references) {

            ConnectedSource curSource = (ConnectedSource) appContext.getBean(ref.getSource().getBean_name());

            Boolean flag = false;
            try {
                flag = curSource.parseReference(ref.getRef());
            } catch (Exception e) {
                //We don't need anythind here. All we need are in 'flag'
            }

            if (flag == true) {

                //Forecast history - should be written only once per day
                if(itsTime == true && hashAlreadyHadCitySource(todayHistory, ref.getCity(), ref.getSource()) == false){
                    Forecasts_history n_history = new Forecasts_history();

                    n_history.setDay(new Date());
                    n_history.setDate(new Date());
                    n_history.setToday(curSource.getToday());
                    n_history.setOne_day(curSource.getOne_day());
                    n_history.setTwo_days(curSource.getTwo_days());
                    n_history.setThree_days(curSource.getThree_days());
                    n_history.setFour_days(curSource.getFour_days());

                    n_history.setCity(ref.getCity());
                    n_history.setSource(ref.getSource());

                    wfanalistDAO.save(n_history);
                }

                //Current_indicators
                Current_indicators newIndicator = findIndicatorInListByCityAndSource(current_indicators, ref.getCity(), ref.getSource());

                newIndicator.setTime(new Date());
                newIndicator.setHumidity(curSource.getHumidity());
                newIndicator.setFeelsLike(curSource.getFeelsLike());
                newIndicator.setWind(curSource.getWind());

                newIndicator.setTemperature_h(curSource.getToday());
                newIndicator.setOne_day_h(curSource.getOne_day());
                newIndicator.setTwo_days_h(curSource.getTwo_days());
                newIndicator.setThree_days_h(curSource.getThree_days());
                newIndicator.setFour_days_h(curSource.getFour_days());

                newIndicator.setTemperature_l(curSource.getToday_l());
                newIndicator.setOne_day_l(curSource.getOne_day_l());
                newIndicator.setTwo_days_l(curSource.getTwo_days_l());
                newIndicator.setThree_days_l(curSource.getThree_days_l());
                newIndicator.setFour_days_l(curSource.getFour_days_l());

                newIndicator.setImg_name(curSource.getIconName().toString());
                newIndicator.setRef(ref.getRef());

                wfanalistDAO.save(newIndicator);

            } else {

                //We have got an error. So we have to save an information about this
                Task_errors error = new Task_errors();
                error.setTime(new Date());
                error.setSource(ref.getSource());
                error.setCity(ref.getCity());
                error.setRef(ref.getRef());

                wfanalistDAO.save(error);
            }

        }
    }

    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private boolean hashAlreadyHadCitySource(List<Forecasts_history> fHistiry, Cities city, Sources source){

        for(Forecasts_history fh: fHistiry){
            if(fh.getCity().equals(city) && fh.getSource().equals(source)){
                return true;
            }
        }

        return false;

    }

    private Current_indicators findIndicatorInListByCityAndSource(List<Current_indicators> curIndicators, Cities city, Sources source){

        if (curIndicators != null) {
            for (Current_indicators cInd: curIndicators){
                if (cInd.getCity().equals(city) && cInd.getSource().equals(source)) {
                    return cInd;
                }
            }
        }

        Current_indicators newIndicator = new Current_indicators();
        newIndicator.setCity(city);
        newIndicator.setSource(source);

        return newIndicator;
    }

}
