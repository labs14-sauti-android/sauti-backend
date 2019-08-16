package com.labs.sauti.service;

import com.labs.sauti.model.regulated_good.FavoriteRegulatedGoodSearch;
import com.labs.sauti.model.user.User;
import com.labs.sauti.repository.FavoriteRegulatedGoodSearchRepository;
import com.labs.sauti.repository.FavoriteTradeInfoSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("favoriteRegulatedGoodSearchService")
public class FavoriteRegulatedGoodSearchServiceImpl implements FavoriteRegulatedGoodSearchService {

    private FavoriteRegulatedGoodSearchRepository favoriteRegulatedGoodSearchRepository;
    private UserService userService;

    public FavoriteRegulatedGoodSearchServiceImpl(
            FavoriteRegulatedGoodSearchRepository favoriteRegulatedGoodSearchRepository,
            UserService userService
    ) {
        this.favoriteRegulatedGoodSearchRepository = favoriteRegulatedGoodSearchRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<Long> saveAll(ArrayList<FavoriteRegulatedGoodSearch> favoriteRegulatedGoodSearches) {
        User user = userService.getAuthenticatedUser();
        ArrayList<FavoriteRegulatedGoodSearch> favoriteRegulatedGoodSearchesSaving = new ArrayList<>(favoriteRegulatedGoodSearches.size());

        for (FavoriteRegulatedGoodSearch favoriteRegulatedGoodSearch : favoriteRegulatedGoodSearches) {
            favoriteRegulatedGoodSearchesSaving.add(new FavoriteRegulatedGoodSearch(
                    favoriteRegulatedGoodSearch.getLanguage(),
                    favoriteRegulatedGoodSearch.getCountry(),
                    user
            ));
        }

        ArrayList<Long> favoriteRegulatedGoodIds = new ArrayList<>(favoriteRegulatedGoodSearchesSaving.size());
        favoriteRegulatedGoodSearchRepository.saveAll(favoriteRegulatedGoodSearchesSaving)
                .iterator()
                .forEachRemaining(favoriteRegulatedGood ->
                        favoriteRegulatedGoodIds.add(favoriteRegulatedGood.getFavoriteRegulatedGoodSearchId()));

        return favoriteRegulatedGoodIds;
    }

    @Override
    public List<FavoriteRegulatedGoodSearch> getAll() {
        User user = userService.getAuthenticatedUser();
        return favoriteRegulatedGoodSearchRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public void deleteAllByIds(ArrayList<Long> ids) {
        User user = userService.getAuthenticatedUser();
        for (Long id : ids) {
            favoriteRegulatedGoodSearchRepository.deleteByIdAndUserId(id, user.getUserId());
        }
    }
}
