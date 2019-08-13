package com.labs.sauti.controller;

import com.labs.sauti.model.market_price.MarketPrice;
import com.labs.sauti.service.MarketPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketPriceController {

    private MarketPriceService marketPriceService;

    public MarketPriceController(MarketPriceService marketPriceService) {
        this.marketPriceService = marketPriceService;
    }

    @GetMapping("market-price/all")
    public ResponseEntity<List<MarketPrice>> getAll() {
        return new ResponseEntity<>(marketPriceService.getAll(), HttpStatus.OK);
    }

    @GetMapping("market-price/countries")
    public ResponseEntity<List<String>> getCountries() {
        return new ResponseEntity<>(marketPriceService.getCountries(), HttpStatus.OK);
    }

    @GetMapping("market-price/markets")
    public ResponseEntity<List<String>> getMarkets(@RequestParam String country) {
        return new ResponseEntity<>(marketPriceService.getMarkets(country), HttpStatus.OK);
    }

    @GetMapping("market-price/categories")
    public ResponseEntity<List<String>> getCategories(@RequestParam String country, @RequestParam String market) {
        return new ResponseEntity<>(marketPriceService.getCategories(country, market), HttpStatus.OK);
    }

    @GetMapping("market-price/products")
    public ResponseEntity<List<String>> getProducts(
            @RequestParam String country,
            @RequestParam String market,
            @RequestParam String category
    ) {
        return new ResponseEntity<>(marketPriceService.getProducts(country, market, category), HttpStatus.OK);
    }

    @GetMapping("market-price/search")
    public ResponseEntity<MarketPrice> searchMarketPrice(
            @RequestParam String country,
            @RequestParam String market,
            @RequestParam String category,
            @RequestParam String product
    ) {
        return new ResponseEntity<>(marketPriceService.getMarketPrice(country, market, category, product), HttpStatus.OK);
    }

}
