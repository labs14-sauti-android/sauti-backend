package com.labs.sauti.service;

import com.labs.sauti.model.exchange_rate.FavoriteExchangeRateConversion;
import com.labs.sauti.model.user.User;
import com.labs.sauti.repository.FavoriteExchangeRateConversionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("favoriteExchangeRateConversionService")
public class FavoriteExchangeRateConversionServiceImpl implements FavoriteExchangeRateConversionService {

    private FavoriteExchangeRateConversionRepository favoriteExchangeRateConversionRepository;
    private UserService userService;

    public FavoriteExchangeRateConversionServiceImpl(
            FavoriteExchangeRateConversionRepository favoriteExchangeRateConversionRepository,
            UserService userService
    ) {
        this.favoriteExchangeRateConversionRepository = favoriteExchangeRateConversionRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<FavoriteExchangeRateConversion> saveAll(ArrayList<FavoriteExchangeRateConversion> favoriteExchangeRateConversions) {
        User user = userService.getAuthenticatedUser();
        ArrayList<FavoriteExchangeRateConversion> favoriteExchangeRateConversionsSaving = new ArrayList<>(favoriteExchangeRateConversions.size());

        ArrayList<FavoriteExchangeRateConversion> currentFavoriteExchangeRateConversions =
                favoriteExchangeRateConversionRepository.findAllByUserId(user.getUserId());

        for (FavoriteExchangeRateConversion favoriteExchangeRateConversion : favoriteExchangeRateConversions) {
            if (contains(currentFavoriteExchangeRateConversions, favoriteExchangeRateConversion)) continue;

            favoriteExchangeRateConversionsSaving.add(new FavoriteExchangeRateConversion(
                    favoriteExchangeRateConversion.getFromCurrency(),
                    favoriteExchangeRateConversion.getToCurrency(),
                    favoriteExchangeRateConversion.getValue(),
                    favoriteExchangeRateConversion.getTimestamp(),
                    user
            ));
        }

        ArrayList<FavoriteExchangeRateConversion> outFavoriteExchangeRateConversions = new ArrayList<>(favoriteExchangeRateConversionsSaving.size());
        favoriteExchangeRateConversionRepository.saveAll(favoriteExchangeRateConversionsSaving)
                .iterator()
                .forEachRemaining(outFavoriteExchangeRateConversions::add);

        return outFavoriteExchangeRateConversions;
    }

    private static boolean contains(
            ArrayList<FavoriteExchangeRateConversion> favoriteExchangeRateConversions,
            FavoriteExchangeRateConversion inFavoriteExchangeRateConversion
    ) {
        for (FavoriteExchangeRateConversion favoriteExchangeRateConversion : favoriteExchangeRateConversions) {
            if (favoriteExchangeRateConversion.getFromCurrency().equals(inFavoriteExchangeRateConversion.getFromCurrency()) &&
            favoriteExchangeRateConversion.getToCurrency().equals(inFavoriteExchangeRateConversion.getToCurrency()) &&
            favoriteExchangeRateConversion.getValue() == inFavoriteExchangeRateConversion.getValue()) return true;
        }

        return false;
    }

    @Override
    public List<FavoriteExchangeRateConversion> getAll() {
        User user = userService.getAuthenticatedUser();
        return favoriteExchangeRateConversionRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public void deleteAllByIds(ArrayList<Long> ids) {
        User user = userService.getAuthenticatedUser();
        for (Long id : ids) {
            favoriteExchangeRateConversionRepository.deleteByIdAndUserId(id, user.getUserId());
        }
    }
}
