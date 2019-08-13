package com.labs.sauti.model.exchange_rate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchangeRates")
public class ExchangeRate {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    private String currency;
    private double rate;

    public ExchangeRate() {
    }

    public ExchangeRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
