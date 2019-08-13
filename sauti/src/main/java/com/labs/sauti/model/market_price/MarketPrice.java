package com.labs.sauti.model.market_price;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marketPrices")
public class MarketPrice {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    private String country;
    private String market;

    @JsonProperty("product_agg")
    private String productAgg;

    @JsonProperty("product_cat")
    private String productCat;

    private String product;
    private long wholesale;
    private long retail;
    private String currency;
    private String date;

    public MarketPrice() {
    }

    public MarketPrice(String country, String market, String productAgg, String productCat, String product, long wholesale, long retail, String currency, String date) {
        this.country = country;
        this.market = market;
        this.productAgg = productAgg;
        this.productCat = productCat;
        this.product = product;
        this.wholesale = wholesale;
        this.retail = retail;
        this.currency = currency;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getProductAgg() {
        return productAgg;
    }

    public void setProductAgg(String productAgg) {
        this.productAgg = productAgg;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public long getWholesale() {
        return wholesale;
    }

    public void setWholesale(long wholesale) {
        this.wholesale = wholesale;
    }

    public long getRetail() {
        return retail;
    }

    public void setRetail(long retail) {
        this.retail = retail;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
