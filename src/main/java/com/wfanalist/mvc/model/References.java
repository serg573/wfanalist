package com.wfanalist.mvc.model;

import javax.persistence.*;

/**
 * Created by serg on 25.05.2016.
 */

@Entity
@Table(name="wfanalist.references")
public class References {

    private long id;
    private String ref;

    //private long id_city;
    //private long id_source;

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

    @Column(name="ref")
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }

    /*
    @Column(name="id_city")
    public long getId_city() {
        return id_city;
    }
    public void setId_city(long id_city) {
        this.id_city = id_city;
    }

    @Column(name="id_source")
    public long getId_source() {
        return id_source;
    }
    public void setId_source(long id_source) {
        this.id_source = id_source;
    }
    */

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
