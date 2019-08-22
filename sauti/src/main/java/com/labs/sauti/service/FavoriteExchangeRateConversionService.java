package com.labs.sauti.service;

import com.labs.sauti.model.exchange_rate.FavoriteExchangeRateConversion;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteExchangeRateConversionService {

    List<FavoriteExchangeRateConversion> saveAll(ArrayList<FavoriteExchangeRateConversion> favoriteExchangeRateConversions);
    List<FavoriteExchangeRateConversion> getAll();
    void deleteAllByIds(ArrayList<Long> ids);

}
