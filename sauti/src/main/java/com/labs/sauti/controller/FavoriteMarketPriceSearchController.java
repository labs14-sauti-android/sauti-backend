package com.labs.sauti.controller;

import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;
import com.labs.sauti.service.FavoriteMarketPriceSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteMarketPriceSearchController {

    private FavoriteMarketPriceSearchService favoriteMarketPriceSearchService;

    public FavoriteMarketPriceSearchController(FavoriteMarketPriceSearchService favoriteMarketPriceSearchService) {
        this.favoriteMarketPriceSearchService = favoriteMarketPriceSearchService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("favorite-market-price-searches/all")
    public ResponseEntity<List<Long>> addAllFavoriteMarketPriceSearches(
            @RequestBody ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches
    ) {
        return new ResponseEntity<>(favoriteMarketPriceSearchService.saveAllFavoriteMarketPriceSearches(favoriteMarketPriceSearches), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("favorite-market-price-searches")
    public ResponseEntity<List<FavoriteMarketPriceSearch>> getFavoriteMarketPriceSearches() {
        return new ResponseEntity<>(favoriteMarketPriceSearchService.getFavoriteMarketPriceSearches(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("favorite-market-price-searches")
    public ResponseEntity deleteAllByIds(@RequestBody ArrayList<FavoriteMarketPriceSearch> favoriteMarketPriceSearches) {
        favoriteMarketPriceSearchService.deleteAllByIds(favoriteMarketPriceSearches);
        return new ResponseEntity(HttpStatus.OK);
    }

}
