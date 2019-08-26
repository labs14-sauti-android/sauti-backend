package com.labs.sauti.service;

import com.labs.sauti.model.trade_info.FavoriteTradeInfoSearch;
import com.labs.sauti.model.user.User;
import com.labs.sauti.repository.FavoriteTradeInfoSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("favoriteTradeInfoSearchService")
public class FavoriteTradeInfoSearchServiceImpl implements FavoriteTradeInfoSearchService {

    private FavoriteTradeInfoSearchRepository favoriteTradeInfoSearchRepository;
    private UserService userService;

    public FavoriteTradeInfoSearchServiceImpl(
            FavoriteTradeInfoSearchRepository favoriteTradeInfoSearchRepository,
            UserService userService
    ) {
        this.favoriteTradeInfoSearchRepository = favoriteTradeInfoSearchRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<FavoriteTradeInfoSearch> saveAll(ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches) {
        User user = userService.getAuthenticatedUser();
        ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearchesSaving = new ArrayList<>(favoriteTradeInfoSearches.size());

        ArrayList<FavoriteTradeInfoSearch> currentFavoriteTradeInfoSearches =
                favoriteTradeInfoSearchRepository.findAllByUserId(user.getUserId());

        // TODO check type
        for (FavoriteTradeInfoSearch favoriteTradeInfoSearch : favoriteTradeInfoSearches) {
            // do not duplicate
            if (contains(currentFavoriteTradeInfoSearches, favoriteTradeInfoSearch)) continue;

            favoriteTradeInfoSearchesSaving.add(new FavoriteTradeInfoSearch(
                    favoriteTradeInfoSearch.getType(),
                    favoriteTradeInfoSearch.getProductCat(),
                    favoriteTradeInfoSearch.getProduct(),
                    favoriteTradeInfoSearch.getOrigin(),
                    favoriteTradeInfoSearch.getDest(),
                    favoriteTradeInfoSearch.getValue(),
                    favoriteTradeInfoSearch.getTimestamp(),
                    user
            ));
        }

        ArrayList<FavoriteTradeInfoSearch> outFavoriteTradeInfoSearches = new ArrayList<>(favoriteTradeInfoSearchesSaving.size());
        favoriteTradeInfoSearchRepository.saveAll(favoriteTradeInfoSearchesSaving)
                .iterator()
                .forEachRemaining(outFavoriteTradeInfoSearches::add);

        return outFavoriteTradeInfoSearches;
    }

    private static boolean contains(
            ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches,
            FavoriteTradeInfoSearch inFavoriteTradeInfoSearch
    ) {
        for (FavoriteTradeInfoSearch favoriteTradeInfoSearch : favoriteTradeInfoSearches) {
            if (favoriteTradeInfoSearch.getType().equals(inFavoriteTradeInfoSearch.getType()) &&
            favoriteTradeInfoSearch.getProductCat().equals(inFavoriteTradeInfoSearch.getProductCat()) &&
            favoriteTradeInfoSearch.getProduct().equals(inFavoriteTradeInfoSearch.getProduct()) &&
            favoriteTradeInfoSearch.getOrigin().equals(inFavoriteTradeInfoSearch.getOrigin()) &&
            favoriteTradeInfoSearch.getDest().equals(inFavoriteTradeInfoSearch.getDest()) &&
            favoriteTradeInfoSearch.getValue() == inFavoriteTradeInfoSearch.getValue()) return true;
        }

        return false;
    }

    @Override
    public List<FavoriteTradeInfoSearch> getAll() {
        User user = userService.getAuthenticatedUser();
        return favoriteTradeInfoSearchRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public void deleteAllByIds(ArrayList<Long> ids) {
        User user = userService.getAuthenticatedUser();
        for (Long id : ids) {
            favoriteTradeInfoSearchRepository.deleteAllByIdAndUserId(id, user.getUserId());
        }
    }
}
