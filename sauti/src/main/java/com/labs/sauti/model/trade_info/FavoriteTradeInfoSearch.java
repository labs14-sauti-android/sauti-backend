package com.labs.sauti.model.trade_info;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.sauti.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "favoriteTradeInfoSearches")
public class FavoriteTradeInfoSearch {

    public static final String TYPE_PROCEDURE = "procedure";
    public static final String TYPE_RELEVANT_AGENCY = "relevant_agency";
    public static final String TYPE_REQUIRED_DOCUMENT = "required_document";
    public static final String TYPE_TAX = "tax";

    @Id
    @GeneratedValue
    private long favoriteTradeInfoSearchId;

    private String type;
    private String productCat;
    private String product;
    private String origin;
    private String dest;
    private double value;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public FavoriteTradeInfoSearch() {
    }

    public FavoriteTradeInfoSearch(String type, String productCat, String product, String origin, String dest, double value, User user) {
        this.type = type;
        this.productCat = productCat;
        this.product = product;
        this.origin = origin;
        this.dest = dest;
        this.value = value;
        this.user = user;
    }

    public long getFavoriteTradeInfoSearchId() {
        return favoriteTradeInfoSearchId;
    }

    public void setFavoriteTradeInfoSearchId(long favoriteTradeInfoSearchId) {
        this.favoriteTradeInfoSearchId = favoriteTradeInfoSearchId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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
