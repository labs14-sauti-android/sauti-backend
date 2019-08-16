package com.labs.sauti.controller;

import com.labs.sauti.model.trade_info.FavoriteTradeInfoSearch;
import com.labs.sauti.service.FavoriteTradeInfoSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteTradeInfoSearchController {

    private FavoriteTradeInfoSearchService favoriteTradeInfoSearchService;

    public FavoriteTradeInfoSearchController(FavoriteTradeInfoSearchService favoriteTradeInfoSearchService) {
        this.favoriteTradeInfoSearchService = favoriteTradeInfoSearchService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("favorite-trade-info-searches/all")
    public ResponseEntity<List<Long>> saveAllFavoriteTradeInfoSearches(
            @RequestBody ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches
            ) {
        return new ResponseEntity<>(
                favoriteTradeInfoSearchService.saveAllFavoriteTradeInfoSearches(favoriteTradeInfoSearches),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("favorite-trade-info-searches")
    public ResponseEntity<List<FavoriteTradeInfoSearch>> getFavoriteTradeInfoSearches() {
        return new ResponseEntity<>(favoriteTradeInfoSearchService.getFavoriteTradeInfoSearches(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("favorite-trade-info-searches")
    public ResponseEntity deleteAllByIds(@RequestBody ArrayList<FavoriteTradeInfoSearch> favoriteTradeInfoSearches) {
        favoriteTradeInfoSearchService.deleteAllByIds(favoriteTradeInfoSearches);
        return new ResponseEntity(HttpStatus.OK);
    }

}
