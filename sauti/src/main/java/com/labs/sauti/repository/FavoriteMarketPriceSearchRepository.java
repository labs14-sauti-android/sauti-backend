package com.labs.sauti.repository;

import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface FavoriteMarketPriceSearchRepository extends CrudRepository<FavoriteMarketPriceSearch, Long> {

    @Query(value = "SELECT * FROM favorite_market_price_searches WHERE user_id=:userId", nativeQuery = true)
    ArrayList<FavoriteMarketPriceSearch> findAllByUserId(long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM favorite_market_price_searches WHERE favorite_market_price_search_id=:id AND user_id=:userId", nativeQuery = true)
    void deleteByIdAndUserId(long id, long userId);

}
