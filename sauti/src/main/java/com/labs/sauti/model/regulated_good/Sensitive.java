package com.labs.sauti.model.regulated_good;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensitives")
public class Sensitive {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Sensitive() {
    }

    public Sensitive(String name) {
        this.name = name;
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
}
