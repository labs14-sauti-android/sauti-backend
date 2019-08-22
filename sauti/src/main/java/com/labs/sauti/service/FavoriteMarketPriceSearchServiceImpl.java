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
    public List<FavoriteMarketPriceSearch> saveAll(ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches) {
        User user = userService.getAuthenticatedUser();
        ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearchesSaving = new ArrayList<>(favoriteMarketPriceSearches.size());

        List<FavoriteMarketPriceSearch> currentFavoriteMarketPriceSearches =
                favoriteMarketPriceSearchRepository.findAllByUserId(user.getUserId());

        for (FavoriteMarketPriceSearch favoriteMarketPriceSearch : favoriteMarketPriceSearches) {
            // do not duplicate
            if (contains(currentFavoriteMarketPriceSearches, favoriteMarketPriceSearch)) continue;

            favoriteMarketPriceSearchesSaving.add(new FavoriteMarketPriceSearch(
                    favoriteMarketPriceSearch.getCountry(),
                    favoriteMarketPriceSearch.getMarket(),
                    favoriteMarketPriceSearch.getCategory(),
                    favoriteMarketPriceSearch.getProduct(),
                    user
            ));
        }
        ArrayList<FavoriteMarketPriceSearch> outFavoriteMarketPriceSearches = new ArrayList<>(favoriteMarketPriceSearchesSaving.size());
        favoriteMarketPriceSearchRepository.saveAll(favoriteMarketPriceSearchesSaving)
                .iterator()
                .forEachRemaining(outFavoriteMarketPriceSearches::add);

        return outFavoriteMarketPriceSearches;
    }

    private static boolean contains(
            List<FavoriteMarketPriceSearch> favoriteMarketPriceSearches,
            FavoriteMarketPriceSearch inFavoriteMarketPriceSearch
    ) {
        for (FavoriteMarketPriceSearch favoriteMarketPriceSearch : favoriteMarketPriceSearches) {
            if (favoriteMarketPriceSearch.getCountry().equals(inFavoriteMarketPriceSearch.getCountry()) &&
            favoriteMarketPriceSearch.getMarket().equals(inFavoriteMarketPriceSearch.getMarket()) &&
            favoriteMarketPriceSearch.getCategory().equals(inFavoriteMarketPriceSearch.getCategory()) &&
            favoriteMarketPriceSearch.getProduct().equals(inFavoriteMarketPriceSearch.getProduct())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<FavoriteMarketPriceSearch> getAll() {
        User user = userService.getAuthenticatedUser();
        return favoriteMarketPriceSearchRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public void deleteAllByIds(ArrayList<Long> ids) {
        User user = userService.getAuthenticatedUser();
        for (Long id : ids) {
            favoriteMarketPriceSearchRepository.deleteByIdAndUserId(id, user.getUserId());
        }
    }
}
