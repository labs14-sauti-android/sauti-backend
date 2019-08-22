package com.labs.sauti.controller;

import com.labs.sauti.model.exchange_rate.FavoriteExchangeRateConversion;
import com.labs.sauti.service.FavoriteExchangeRateConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteExchangeRateConversionController {

    private FavoriteExchangeRateConversionService favoriteExchangeRateConversionService;

    public FavoriteExchangeRateConversionController(FavoriteExchangeRateConversionService favoriteExchangeRateConversionService) {
        this.favoriteExchangeRateConversionService = favoriteExchangeRateConversionService;
    }

    /** Add unique*/
    @PostMapping("favorite-exchange-rate-conversions/all")
    public ResponseEntity<List<FavoriteExchangeRateConversion>> addAllFavoriteExchangeRateConversions(
            @RequestBody ArrayList<FavoriteExchangeRateConversion> favoriteExchangeRateConversions
    ) {
        return new ResponseEntity<>(favoriteExchangeRateConversionService.saveAll(favoriteExchangeRateConversions), HttpStatus.OK);
    }

    @GetMapping("favorite-exchange-rate-conversions")
    public ResponseEntity<List<FavoriteExchangeRateConversion>> getAllFavoriteExchangeRateConversions() {
        return new ResponseEntity<>(favoriteExchangeRateConversionService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("favorite-exchange-rate-conversions")
    public ResponseEntity deleteAllByIds(@RequestBody ArrayList<Long> ids) {
        favoriteExchangeRateConversionService.deleteAllByIds(ids);
        return new ResponseEntity(HttpStatus.OK);
    }
}
