package com.labs.sauti.model.exchange_rate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.sauti.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "favoriteExchangeRateConversions")
public class FavoriteExchangeRateConversion {

    @Id
    @GeneratedValue
    private long favoriteExchangeRateConversionId;

    private String fromCurrency;
    private String toCurrency;
    private double value;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public FavoriteExchangeRateConversion() {
    }

    public FavoriteExchangeRateConversion(String fromCurrency, String toCurrency, double value, User user) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.value = value;
        this.user = user;
    }

    public long getFavoriteExchangeRateConversionId() {
        return favoriteExchangeRateConversionId;
    }

    public void setFavoriteExchangeRateConversionId(long favoriteExchangeRateConversionId) {
        this.favoriteExchangeRateConversionId = favoriteExchangeRateConversionId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return user.getUserId();
    }
}
