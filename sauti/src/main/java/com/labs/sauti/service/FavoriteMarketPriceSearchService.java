package com.labs.sauti.service;

import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteMarketPriceSearchService {

    List<Long> saveAllFavoriteMarketPriceSearches(
            ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches
    );
    List<FavoriteMarketPriceSearch> getFavoriteMarketPriceSearches();
    void deleteAllByIds(ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches);
}
