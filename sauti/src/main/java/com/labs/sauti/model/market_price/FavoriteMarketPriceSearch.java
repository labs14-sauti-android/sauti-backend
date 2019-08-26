package com.labs.sauti.model.market_price;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.sauti.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "favoriteMarketPriceSearches")
public class FavoriteMarketPriceSearch {

    @Id
    @GeneratedValue
    private long favoriteMarketPriceSearchId;

    private String country;
    private String market;
    private String category;
    private String product;
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public FavoriteMarketPriceSearch() {
    }

    public FavoriteMarketPriceSearch(String country, String market, String category, String product, long timestamp, User user) {
        this.country = country;
        this.market = market;
        this.category = category;
        this.product = product;
        this.timestamp = timestamp;
        this.user = user;
    }

    public long getFavoriteMarketPriceSearchId() {
        return favoriteMarketPriceSearchId;
    }

    public void setFavoriteMarketPriceSearchId(long favoriteMarketPriceSearchId) {
        this.favoriteMarketPriceSearchId = favoriteMarketPriceSearchId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
