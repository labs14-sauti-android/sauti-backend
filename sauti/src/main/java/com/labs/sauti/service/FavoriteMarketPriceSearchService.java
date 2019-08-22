package com.labs.sauti.service;

import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteMarketPriceSearchService {

    List<FavoriteMarketPriceSearch> saveAll(ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches);
    List<FavoriteMarketPriceSearch> getAll();
    void deleteAllByIds(ArrayList<Long> ids);
}
