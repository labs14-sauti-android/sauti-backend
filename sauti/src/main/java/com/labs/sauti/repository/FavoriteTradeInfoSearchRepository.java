package com.labs.sauti.repository;

import com.labs.sauti.model.trade_info.FavoriteTradeInfoSearch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FavoriteTradeInfoSearchRepository extends CrudRepository<FavoriteTradeInfoSearch, Long> {

    @Query(value = "SELECT * FROM favorite_trade_info_searches WHERE user_id=:userId", nativeQuery = true)
    ArrayList<FavoriteTradeInfoSearch> findAllByUserId(long userId);

    @Query(value = "DELETE FROM favorite_trade_info_searches WHERE favorite_info_search_id=:id AND user_id=:userId", nativeQuery = true)
    void deleteAllByIdAndUserId(long id, long userId);
}
