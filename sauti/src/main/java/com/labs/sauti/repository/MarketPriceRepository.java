package com.labs.sauti.repository;

import com.labs.sauti.model.market_price.MarketPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MarketPriceRepository extends CrudRepository<MarketPrice, Long> {

    @Query(value = "SELECT DISTINCT country FROM market_prices", nativeQuery = true)
    ArrayList<String> findAllDistinctCountries();

    @Query(value = "SELECT DISTINCT market FROM market_prices WHERE country=:country", nativeQuery = true)
    ArrayList<String> findAllDistinctMarkets(String country);

    @Query(value = "SELECT DISTINCT product_cat FROM market_prices WHERE country=:country AND market=:market", nativeQuery = true)
    ArrayList<String> findAllDistinctCategories(String country, String market);

    @Query(value = "SELECT DISTINCT product FROM market_prices WHERE country=:country AND market=:market AND product_cat=:category", nativeQuery = true)
    ArrayList<String> findAllDistinctProducts(String country, String market, String category);

    @Query(value = "SELECT * FROM market_prices WHERE country=:country AND market=:market AND product_cat=:category AND product=:product", nativeQuery = true)
    MarketPrice findMarketPrice(String country, String market, String category, String product);
}
