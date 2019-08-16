package com.labs.sauti.model.exchange_rate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favoriteExchangeRateConversions")
public class FavoriteExchangeRateConversion {

    @Id
    @GeneratedValue
    private long favoriteExchangeRateConversionId;

    private String fromCurrency;
    private String toCurrency;
    private double value;

    public FavoriteExchangeRateConversion() {
    }

    public FavoriteExchangeRateConversion(String fromCurrency, String toCurrency, double value) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.value = value;
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
}
