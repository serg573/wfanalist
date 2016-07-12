package com.wfanalist.mvc.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by serg on 25.05.2016.
 */

@NamedQueries({
        @NamedQuery(
                name = "selectWhereCityId",
                query = "from Current_indicators c where c.city = :city"
        )
})

@Entity
@Table(name="current_indicators")
public class Current_indicators {

    private long id;
    private Date time;
    private int humidity;
    private int feelsLike;
    private String wind;

    private int temperature_l;
    private int one_day_l;
    private int two_days_l;
    private int three_days_l;
    private int four_days_l;

    private int temperature_h;
    private int one_day_h;
    private int two_days_h;
    private int three_days_h;
    private int four_days_h;

    private String one_day_str;
    private String two_days_str;
    private String three_days_str;
    private String four_days_str;

    private String img_name;
    private String ref;

    private Cities city;
    private Sources source;

    public Current_indicators() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));

        calendar.add(Calendar.DATE, 1);
        one_day_str = dateFormat.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        two_days_str = dateFormat.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        three_days_str = dateFormat.format(calendar.getTime());

        calendar.add(Calendar.DATE, 1);
        four_days_str = dateFormat.format(calendar.getTime());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name="time")
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name="humidity")
    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Column(name="feelsLike")
    public int getFeelsLike() {
        return feelsLike;
    }
    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    @Column(name="wind")
    public String getWind() {
        return wind;
    }
    public void setWind(String wind) {
        this.wind = wind;
    }

    @Column(name="temperature_l")
    public int getTemperature_l() {
        return temperature_l;
    }
    public void setTemperature_l(int temperature_l) {
        this.temperature_l = temperature_l;
    }

    @Column(name="one_day_l")
    public int getOne_day_l() {
        return one_day_l;
    }
    public void setOne_day_l(int one_day_l) {
        this.one_day_l = one_day_l;
    }

    @Column(name="two_days_l")
    public int getTwo_days_l() {
        return two_days_l;
    }
    public void setTwo_days_l(int two_days_l) {
        this.two_days_l = two_days_l;
    }

    @Column(name="three_days_l")
    public int getThree_days_l() {
        return three_days_l;
    }
    public void setThree_days_l(int three_days_l) {
        this.three_days_l = three_days_l;
    }

    @Column(name="four_days_l")
    public int getFour_days_l() {
        return four_days_l;
    }
    public void setFour_days_l(int four_days_l) {
        this.four_days_l = four_days_l;
    }

    @Column(name="temperature_h")
    public int getTemperature_h() {
        return temperature_h;
    }
    public void setTemperature_h(int temperature_h) {
        this.temperature_h = temperature_h;
    }

    @Column(name="one_day_h")
    public int getOne_day_h() {
        return one_day_h;
    }
    public void setOne_day_h(int one_day_h) {
        this.one_day_h = one_day_h;
    }

    @Column(name="two_days_h")
    public int getTwo_days_h() {
        return two_days_h;
    }
    public void setTwo_days_h(int two_days_h) {
        this.two_days_h = two_days_h;
    }

    @Column(name="three_days_h")
    public int getThree_days_h() {
        return three_days_h;
    }
    public void setThree_days_h(int three_days_h) {
        this.three_days_h = three_days_h;
    }

    @Column(name="four_days_h")
    public int getFour_days_h() {
        return four_days_h;
    }
    public void setFour_days_h(int four_days_h) {
        this.four_days_h = four_days_h;
    }

    @Column(name="img_name")
    public String getImg_name() {
        return img_name;
    }
    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    @Column(name="ref")
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
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

    @Transient
    public String getOne_day_str() {
        return one_day_str;
    }
    @Transient
    public String getTwo_days_str() {
        return two_days_str;
    }
    @Transient
    public String getThree_days_str() {
        return three_days_str;
    }
    @Transient
    public String getFour_days_str() {
        return four_days_str;
    }
}
