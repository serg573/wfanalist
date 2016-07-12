package com.wfanalist.mvc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by serg on 6/29/16.
 */

@Entity
@Table(name="wfanalist.task_errors")
public class Task_errors {

    private long id;
    private Date time;
    private String ref;

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

    @Column(name="time")
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
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
}
