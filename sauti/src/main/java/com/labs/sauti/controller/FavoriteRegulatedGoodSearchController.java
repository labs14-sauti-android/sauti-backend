package com.labs.sauti.controller;

import com.labs.sauti.model.regulated_good.FavoriteRegulatedGoodSearch;
import com.labs.sauti.service.FavoriteRegulatedGoodSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteRegulatedGoodSearchController {

    private FavoriteRegulatedGoodSearchService favoriteRegulatedGoodSearchService;

    public FavoriteRegulatedGoodSearchController(FavoriteRegulatedGoodSearchService favoriteRegulatedGoodSearchService) {
        this.favoriteRegulatedGoodSearchService = favoriteRegulatedGoodSearchService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("favorite-regulated-good-searches/all")
    public ResponseEntity<List<Long>> saveAllFavoriteRegulatedGoodSearches(
            @RequestBody  ArrayList<FavoriteRegulatedGoodSearch> favoriteRegulatedGoodSearches
    ) {
        return new ResponseEntity<>(favoriteRegulatedGoodSearchService.saveAll(favoriteRegulatedGoodSearches), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("favorite-regulated-good-searches")
    public ResponseEntity<List<FavoriteRegulatedGoodSearch>> getAllFavoriteRegulatedGoodSearches() {
        return new ResponseEntity<>(favoriteRegulatedGoodSearchService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("favorite-regulated-good-searches")
    public ResponseEntity deleteAllByIds(@RequestBody ArrayList<Long> ids) {
        favoriteRegulatedGoodSearchService.deleteAllByIds(ids);
        return new ResponseEntity(HttpStatus.OK);
    }

}
