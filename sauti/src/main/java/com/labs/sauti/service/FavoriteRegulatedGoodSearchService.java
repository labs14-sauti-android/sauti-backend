package com.labs.sauti.service;

import com.labs.sauti.model.regulated_good.FavoriteRegulatedGoodSearch;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteRegulatedGoodSearchService {

    List<FavoriteRegulatedGoodSearch> saveAll(ArrayList<FavoriteRegulatedGoodSearch> favoriteRegulatedGoodSearches);
    List<FavoriteRegulatedGoodSearch> getAll();
    void deleteAllByIds(ArrayList<Long> ids);

}
