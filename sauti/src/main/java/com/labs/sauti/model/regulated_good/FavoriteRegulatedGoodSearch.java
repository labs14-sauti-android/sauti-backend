package com.labs.sauti.model.regulated_good;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.sauti.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "favoriteRegulatedGoodSearches")
public class FavoriteRegulatedGoodSearch {

    @Id
    @GeneratedValue
    private long favoriteRegulatedGoodSearchId;

    private String country;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public FavoriteRegulatedGoodSearch() {
    }

    public FavoriteRegulatedGoodSearch(String country, User user) {
        this.country = country;
        this.user = user;
    }

    public long getFavoriteRegulatedGoodSearchId() {
        return favoriteRegulatedGoodSearchId;
    }

    public void setFavoriteRegulatedGoodSearchId(long favoriteRegulatedGoodSearchId) {
        this.favoriteRegulatedGoodSearchId = favoriteRegulatedGoodSearchId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
