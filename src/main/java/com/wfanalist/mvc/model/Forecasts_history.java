package com.wfanalist.mvc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by serg on 23.05.2016.
 */

@NamedQueries({
    @NamedQuery(
        name = "selectHistoryOnDate",
        query = "from Forecasts_history f where f.day = :day"
    ),

    @NamedQuery(
        name = "selectHistoryDateCitySource",
        query = "from Forecasts_history f where f.day >= :day and f.city.id = :cityid and f.source.id = :sourceid"
    )
})

@Entity
@Table(name="forecasts_history")
public class Forecasts_history {

    private long id;
    private Date day;
    private Date date;
    private int today;
    private int one_day;
    private int two_days;
    private int three_days;
    private int four_days;

    private Cities city;
    private Sources source;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="day")
    public Date getDay() {
        return day;
    }
    public void setDay(Date day) {
        this.day = day;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "today")
    public int getToday() {
        return today;
    }
    public void setToday(int today) {
        this.today = today;
    }

    @Column(name="one_day")
    public int getOne_day() {
        return one_day;
    }
    public void setOne_day(int one_day) {
        this.one_day = one_day;
    }

    @Column(name="two_days")
    public int getTwo_days() {
        return two_days;
    }
    public void setTwo_days(int two_days) {
        this.two_days = two_days;
    }

    @Column(name="three_days")
    public int getThree_days() {
        return three_days;
    }
    public void setThree_days(int three_days) {
        this.three_days = three_days;
    }

    @Column(name="four_days")
    public int getFour_days() {
        return four_days;
    }
    public void setFour_days(int four_days) {
        this.four_days = four_days;
    }

    @ManyToOne
    @JoinColumn(name = "id_city")
    public Cities getCity() {
        return city;
    }
    public void setCity(Cities city) {
        this.city = city;
    }

    @ManyToOne
    @JoinColumn(name = "id_source")
    public Sources getSource() {
        return source;
    }
    public void setSource(Sources source) {
        this.source = source;
    }
}
