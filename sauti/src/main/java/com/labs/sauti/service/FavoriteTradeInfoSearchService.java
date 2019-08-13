package com.labs.sauti.service;

import com.labs.sauti.model.trade_info.FavoriteTradeInfoSearch;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteTradeInfoSearchService {

    List<Long> saveAllFavoriteTradeInfoSearches(ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches);
    List<FavoriteTradeInfoSearch> getFavoriteTradeInfoSearches();
    void deleteAllByIds(ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches);

}
