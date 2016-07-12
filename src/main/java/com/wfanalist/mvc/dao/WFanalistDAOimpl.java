package com.wfanalist.mvc.dao;

import com.wfanalist.mvc.model.*;
import org.hibernate.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by serg on 12.05.2016.
 */

@Repository("ForecastDAO")
@Transactional
public class WFanalistDAOimpl implements WFanalistDAO {

    private SessionFactory sessionFactory;

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    //Current_indicators

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Current_indicators> currentIndicatorsList() {
        return sessionFactory.getCurrentSession().createQuery("from Current_indicators c").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Current_indicators> getcurrentIndicatorsListByCity(Cities city) {
        return sessionFactory.getCurrentSession().getNamedQuery("selectWhereCityId").setParameter("city", city).list();
    }

    @Override
    public Current_indicators save(Current_indicators cIndicator) {
        sessionFactory.getCurrentSession().saveOrUpdate(cIndicator);
        return cIndicator;
    }


    //Forecasts_history

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Forecasts_history> historyList() {
        return sessionFactory.getCurrentSession().createQuery("from Forecasts_history c").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Forecasts_history> historyListOnDate(Date date) {
        return sessionFactory.getCurrentSession().getNamedQuery("selectHistoryOnDate").setParameter("day", date).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Forecasts_history> historyListDateCitySourse(Date date, Cities city, Sources source) {

        Query query = sessionFactory.getCurrentSession().getNamedQuery("selectHistoryDateCitySource");
        query.setParameter("day", date);
        query.setParameter("cityid", city.getId());
        query.setParameter("sourceid", source.getId());

        return query.list();

    }

    @Override
    public Forecasts_history save(Forecasts_history f_history) {
        sessionFactory.getCurrentSession().saveOrUpdate(f_history);
        return f_history;
    }


    //References

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<References> referencesList() {
        return sessionFactory.getCurrentSession().createQuery("from References r").list();
    }


    //Cities

    @Override
    @Transactional(readOnly = true)
    public Cities findCityById(long id) {
        return (Cities) sessionFactory.getCurrentSession().getNamedQuery("findCityById").setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Cities findCityByName(String name_en) {
        return (Cities) sessionFactory.getCurrentSession().getNamedQuery("findCityByName").setParameter("name_en", name_en).uniqueResult();
    }

    @Override
    public Cities save(Cities sity) {
        sessionFactory.getCurrentSession().saveOrUpdate(sity);
        return sity;
    }


    //Sources


    @Override
    public List<Sources> sourcesList() {
        return sessionFactory.getCurrentSession().createQuery("from Sources s").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Sources findSourceByName(String name) {
        return (Sources) sessionFactory.getCurrentSession().getNamedQuery("findSourceByName").setParameter("name", name).uniqueResult();
    }

    @Override
    public Sources save(Sources source) {
        sessionFactory.getCurrentSession().saveOrUpdate(source);
        return source;
    }

    /*
    @Override
    public void save(Forecast p) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Forecast> list() {

        Session session = this.sessionFactory.openSession();

        //noinspection JpaQlInspection
        List<Forecast> forecastList = session.createQuery("from Forecast f").list();
        session.close();

        return forecastList;
    }*/


    //Task_errors

    @Override
    public Task_errors save(Task_errors error) {
        sessionFactory.getCurrentSession().saveOrUpdate(error);
        return error;
    }
}
