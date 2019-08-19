package com.labs.sauti.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.labs.sauti.model.exchange_rate.FavoriteExchangeRateConversion;
import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;
import com.labs.sauti.model.regulated_good.FavoriteRegulatedGoodSearch;
import com.labs.sauti.model.trade_info.FavoriteTradeInfoSearch;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonIgnoreProperties("user")
    private List<UserRole> userRoles = new ArrayList<>();

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String location;
    private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteMarketPriceSearch> favoriteMarketPriceSearches = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteTradeInfoSearch> favoriteTradeInfoSearches = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteRegulatedGoodSearch> favoriteRegulatedGoodSearches = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteExchangeRateConversion> favoriteExchangeRateConversions = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String phoneNumber, String firstName, String lastName, String location, String gender) {
        this.username = username;
        setPasswordEncrypt(password);
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.gender = gender;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<FavoriteMarketPriceSearch> getFavoriteMarketPriceSearches() {
        return favoriteMarketPriceSearches;
    }

    public void setFavoriteMarketPriceSearches(List<FavoriteMarketPriceSearch> favoriteMarketPriceSearches) {
        this.favoriteMarketPriceSearches = favoriteMarketPriceSearches;
    }

    public List<FavoriteTradeInfoSearch> getFavoriteTradeInfoSearches() {
        return favoriteTradeInfoSearches;
    }

    public void setFavoriteTradeInfoSearches(List<FavoriteTradeInfoSearch> favoriteTradeInfoSearches) {
        this.favoriteTradeInfoSearches = favoriteTradeInfoSearches;
    }

    public List<FavoriteRegulatedGoodSearch> getFavoriteRegulatedGoodSearches() {
        return favoriteRegulatedGoodSearches;
    }

    public void setFavoriteRegulatedGoodSearches(List<FavoriteRegulatedGoodSearch> favoriteRegulatedGoodSearches) {
        this.favoriteRegulatedGoodSearches = favoriteRegulatedGoodSearches;
    }

    public List<FavoriteExchangeRateConversion> getFavoriteExchangeRateConversions() {
        return favoriteExchangeRateConversions;
    }

    public void setFavoriteExchangeRateConversions(List<FavoriteExchangeRateConversion> favoriteExchangeRateConversions) {
        this.favoriteExchangeRateConversions = favoriteExchangeRateConversions;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority() {
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            String role = userRole.getRole().getName().toUpperCase();
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        return simpleGrantedAuthorities;
    }

    @JsonIgnore
    public void setPasswordEncrypt(String password) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        this.password = bCrypt.encode(password);
    }
}
