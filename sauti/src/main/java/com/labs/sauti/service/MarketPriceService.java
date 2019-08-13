package com.labs.sauti.service;

import com.labs.sauti.model.market_price.MarketPrice;

import java.util.List;

public interface MarketPriceService {

    List<MarketPrice> getAll();
    List<String> getCountries();
    List<String> getMarkets(String country);
    List<String> getCategories(String country, String market);
    List<String> getProducts(String country, String market, String category);
    MarketPrice getMarketPrice(String country, String market, String category, String product);

}
