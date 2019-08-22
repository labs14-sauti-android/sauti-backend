package com.labs.sauti.repository;

import com.labs.sauti.model.exchange_rate.FavoriteExchangeRateConversion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface FavoriteExchangeRateConversionRepository extends CrudRepository<FavoriteExchangeRateConversion, Long> {

    @Query(value = "SELECT * FROM favorite_exchange_rate_conversions WHERE user_id=:userId", nativeQuery = true)
    ArrayList<FavoriteExchangeRateConversion> findAllByUserId(long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM favorite_exchange_rate_conversions WHERE favorite_exchange_rate_conversion_id=:id AND user_id=:userId", nativeQuery = true)
    void deleteByIdAndUserId(long id, long userId);

}
