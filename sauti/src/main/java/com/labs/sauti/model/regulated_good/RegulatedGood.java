package com.labs.sauti.model.regulated_good;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regulatedGoods")
public class RegulatedGood {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    private String language;
    private String country;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Prohibited> prohibiteds = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Sensitive> sensitives = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Restricted> restricteds = new ArrayList<>();

    public RegulatedGood() {
    }

    public RegulatedGood(String language, String country, List<Prohibited> prohibiteds, List<Sensitive> sensitives, List<Restricted> restricteds) {
        this.language = language;
        this.country = country;
        this.prohibiteds = prohibiteds;
        this.sensitives = sensitives;
        this.restricteds = restricteds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Prohibited> getProhibiteds() {
        return prohibiteds;
    }

    public void setProhibiteds(List<Prohibited> prohibiteds) {
        this.prohibiteds = prohibiteds;
    }

    public List<Sensitive> getSensitives() {
        return sensitives;
    }

    public void setSensitives(List<Sensitive> sensitives) {
        this.sensitives = sensitives;
    }

    public List<Restricted> getRestricteds() {
        return restricteds;
    }

    public void setRestricteds(List<Restricted> restricteds) {
        this.restricteds = restricteds;
    }
}
