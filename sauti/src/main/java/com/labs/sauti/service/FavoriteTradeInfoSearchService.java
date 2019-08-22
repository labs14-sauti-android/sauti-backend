package com.labs.sauti.service;

import com.labs.sauti.model.trade_info.FavoriteTradeInfoSearch;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteTradeInfoSearchService {

    List<FavoriteTradeInfoSearch> saveAll(ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches);
    List<FavoriteTradeInfoSearch> getAll();
    void deleteAllByIds(ArrayList<Long> ids);

}
