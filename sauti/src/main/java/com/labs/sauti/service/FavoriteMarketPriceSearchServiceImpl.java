package com.labs.sauti.service;

import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;
import com.labs.sauti.model.user.User;
import com.labs.sauti.repository.FavoriteMarketPriceSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service("favoriteMarketPriceSearchService")
public class FavoriteMarketPriceSearchServiceImpl implements FavoriteMarketPriceSearchService {

    private FavoriteMarketPriceSearchRepository favoriteMarketPriceSearchRepository;
    private UserService userService;

    public FavoriteMarketPriceSearchServiceImpl(FavoriteMarketPriceSearchRepository favoriteMarketPriceSearchRepository, UserService userService) {
        this.favoriteMarketPriceSearchRepository = favoriteMarketPriceSearchRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<Long> saveAllFavoriteMarketPriceSearches(
            ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches
    ) {
        User user = userService.getAuthenticatedUser();
        ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearchesSaving = new ArrayList<>(favoriteMarketPriceSearches.size());

        for (FavoriteMarketPriceSearch favoriteMarketPriceSearch : favoriteMarketPriceSearches) {
            favoriteMarketPriceSearchesSaving.add(new FavoriteMarketPriceSearch(
                    favoriteMarketPriceSearch.getCountry(),
                    favoriteMarketPriceSearch.getMarket(),
                    favoriteMarketPriceSearch.getCategory(),
                    favoriteMarketPriceSearch.getProduct(),
                    user
            ));
        }
        ArrayList<Long> favoriteMarketPriceSearchIds = new ArrayList<>();
        favoriteMarketPriceSearchRepository.saveAll(favoriteMarketPriceSearchesSaving)
                .iterator()
                .forEachRemaining((favoriteMarketPriceSearch) ->
                        favoriteMarketPriceSearchIds.add(favoriteMarketPriceSearch.getFavoriteMarketPriceSearchId()));

        return favoriteMarketPriceSearchIds;
    }

    @Override
    public List<FavoriteMarketPriceSearch> getFavoriteMarketPriceSearches() {
        User user = userService.getAuthenticatedUser();
        return favoriteMarketPriceSearchRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public void deleteAllByIds(ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches) {
        User user = userService.getAuthenticatedUser();
        for (FavoriteMarketPriceSearch favoriteMarketPriceSearch : favoriteMarketPriceSearches) {
            favoriteMarketPriceSearchRepository.deleteByIdAndUserId(
                    favoriteMarketPriceSearch.getFavoriteMarketPriceSearchId(),
                    user.getUserId()
            );
        }
    }
}
