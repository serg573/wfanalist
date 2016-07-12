package com.wfanalist.mvc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by serg on 23.05.2016.
 */

@NamedQueries({
    @NamedQuery(
        name = "findSourceByName",
        query = "from Sources s where s.name = :name"
    )
})

@Entity
@Table(name="sources")
public class Sources {

    private long id;
    private String name;
    private String ref;
    private String bean_name;

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

    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="ref")
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }

    @Column(name="bean_name")
    public String getBean_name() {
        return bean_name;
    }
    public void setBean_name(String bean_name) {
        this.bean_name = bean_name;
    }

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    public Set<Forecasts_history> getForecasts_histories() {
        return forecasts_histories;
    }
    public void setForecasts_histories(Set<Forecasts_history> forecasts_histories) {
        this.forecasts_histories = forecasts_histories;
    }

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    public Set<References> getReferences() {
        return references;
    }
    public void setReferences(Set<References> references) {
        this.references = references;
    }

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
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

        Sources sources = (Sources) o;

        return id == sources.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Sources{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
