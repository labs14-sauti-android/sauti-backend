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
    public List<Long> saveAll(ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches) {
        User user = userService.getAuthenticatedUser();
        ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearchesSaving = new ArrayList<>(favoriteTradeInfoSearches.size());

        // TODO check type
        for (FavoriteTradeInfoSearch favoriteTradeInfoSearch : favoriteTradeInfoSearches) {
            favoriteTradeInfoSearchesSaving.add(new FavoriteTradeInfoSearch(
                    favoriteTradeInfoSearch.getType(),
                    favoriteTradeInfoSearch.getLanguage(),
                    favoriteTradeInfoSearch.getProductCat(),
                    favoriteTradeInfoSearch.getProduct(),
                    favoriteTradeInfoSearch.getOrigin(),
                    favoriteTradeInfoSearch.getDest(),
                    favoriteTradeInfoSearch.getValue(),
                    user
            ));
        }

        ArrayList<Long> favoriteTradeInfoSearchIds = new ArrayList<>(favoriteTradeInfoSearchesSaving.size());
        favoriteTradeInfoSearchRepository.saveAll(favoriteTradeInfoSearchesSaving)
                .iterator()
                .forEachRemaining(favoriteTradeInfoSearch ->
                        favoriteTradeInfoSearchIds.add(favoriteTradeInfoSearch.getFavoriteTradeInfoSearchId()));

        return favoriteTradeInfoSearchIds;
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
