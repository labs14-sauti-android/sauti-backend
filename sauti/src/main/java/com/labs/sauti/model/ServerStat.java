package com.labs.sauti.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "serverStats")
public class ServerStat {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String name;

    private Double value;

    public ServerStat() {
    }

    public ServerStat(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
