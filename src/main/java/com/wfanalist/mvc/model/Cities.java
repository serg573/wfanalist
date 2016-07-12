package com.wfanalist.mvc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by serg on 23.05.2016.
 */

@NamedQueries({
    @NamedQuery(
        name = "findCityByName",
        query = "from Cities c where c.name_en = :name_en"
    ),

    @NamedQuery(
        name = "findCityById",
        query = "from Cities c where c.id = :id"
    )
})

@Entity
@Table(name="cities")
public class Cities {

    private long id;
    private String name_ru;
    private String name_uk;
    private String name_en;

    private Set<Forecasts_history> forecasts_histories = new HashSet<Forecasts_history>();
    private Set<References> references = new HashSet<References>();
    private Set<Current_indicators> current_indicatorses = new HashSet<Current_indicators>();

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name="name_ru")
    public String getName_ru() {
        return name_ru;
    }
    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    @Column(name="name_uk")
    public String getName_uk() {
        return name_uk;
    }
    public void setName_uk(String name_uk) {
        this.name_uk = name_uk;
    }

    @Column(name="name_en")
    public String getName_en() {
        return name_en;
    }
    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    public Set<Forecasts_history> getForecasts_histories() {
        return forecasts_histories;
    }
    public void setForecasts_histories(Set<Forecasts_history> forecasts_histories) {
        this.forecasts_histories = forecasts_histories;
    }
    public void addForecast_history(Forecasts_history newHistory){
        newHistory.setCity(this);
        getForecasts_histories().add(newHistory);
    }
    public void removeForecast_history(Forecasts_history delHistory){
        getForecasts_histories().remove(delHistory);
    }

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    public Set<References> getReferences() {
        return references;
    }
    public void setReferences(Set<References> references) {
        this.references = references;
    }
    public void addReferences(References newReference){
        newReference.setCity(this);
        getReferences().add(newReference);
    }
    public void removeReferences(References delReference){
        getReferences().remove(delReference);
    }

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    public Set<Current_indicators> getCurrent_indicatorses() {
        return current_indicatorses;
    }
    public void setCurrent_indicatorses(Set<Current_indicators> current_indicatorses) {
        this.current_indicatorses = current_indicatorses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cities cities = (Cities) o;

        return id == cities.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id=" + id +
                ", name_ru='" + name_ru + '\'' +
                ", name_uk='" + name_uk + '\'' +
                ", name_en='" + name_en + '\'' +
                '}';
    }
}
