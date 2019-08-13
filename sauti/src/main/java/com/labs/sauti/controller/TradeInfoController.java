package com.labs.sauti.controller;

import com.labs.sauti.model.trade_info.*;
import com.labs.sauti.service.TradeInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeInfoController {

    private TradeInfoService tradeInfoService;

    public TradeInfoController(TradeInfoService tradeInfoService) {
        this.tradeInfoService = tradeInfoService;
    }

    @GetMapping("trade-info/all")
    public ResponseEntity<List<TradeInfo>> getAll() {
        return new ResponseEntity<>(tradeInfoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "trade-info/languages")
    public ResponseEntity<List<String>> getLanguages() {
        return new ResponseEntity<>(tradeInfoService.getLanguages(), HttpStatus.OK);
    }

    @GetMapping("trade-info/categories")
    public ResponseEntity<List<String>> getCategories(@RequestParam String language) {
        return new ResponseEntity<>(tradeInfoService.getCategories(language), HttpStatus.OK);
    }

    @GetMapping("trade-info/products")
    public ResponseEntity<List<String>> getProducts(@RequestParam String language, @RequestParam String category) {
        return new ResponseEntity<>(tradeInfoService.getProducts(language, category), HttpStatus.OK);
    }

    @GetMapping("trade-info/origins")
    public ResponseEntity<List<String>> getOrigins(@RequestParam String language, @RequestParam String category, @RequestParam String product) {
        return new ResponseEntity<>(tradeInfoService.getOrigins(language, category, product), HttpStatus.OK);
    }

    @GetMapping("trade-info/dests")
    public ResponseEntity<List<String>> getDests(
            @RequestParam String language,
            @RequestParam String category,
            @RequestParam String product,
            @RequestParam String origin
    ) {
        return new ResponseEntity<>(tradeInfoService.getDests(language, category, product, origin), HttpStatus.OK);
    }

    @GetMapping("trade-info/search")
    public ResponseEntity<TradeInfo> getTradeInfo(
        @RequestParam String language,
        @RequestParam String category,
        @RequestParam String product,
        @RequestParam String origin,
        @RequestParam String dest,
        @RequestParam Double value
    ) {
        return new ResponseEntity<>(tradeInfoService.getTradeInfo(language, category, product, origin, dest, value), HttpStatus.OK);
    }

    @GetMapping("trade-info/search/procedures")
    public ResponseEntity<List<Procedure>> getProcedures(
            @RequestParam String language,
            @RequestParam String category,
            @RequestParam String product,
            @RequestParam String origin,
            @RequestParam String dest,
            @RequestParam Double value
    ) {
        return new ResponseEntity<>(tradeInfoService.getProcedures(language, category, product,origin, dest, value), HttpStatus.OK);
    }

    @GetMapping("trade-info/search/relevant-agencies")
    public ResponseEntity<List<RelevantAgency>> getRelevantAgencies(
            @RequestParam String language,
            @RequestParam String category,
            @RequestParam String product,
            @RequestParam String origin,
            @RequestParam String dest,
            @RequestParam Double value
    ) {
        return new ResponseEntity<>(tradeInfoService.getRelevantAgencies(language, category, product,origin, dest, value), HttpStatus.OK);
    }

    @GetMapping("trade-info/search/required-documents")
    public ResponseEntity<List<RequiredDocument>> getRequiredDocuments(
            @RequestParam String language,
            @RequestParam String category,
            @RequestParam String product,
            @RequestParam String origin,
            @RequestParam String dest,
            @RequestParam Double value
    ) {
        return new ResponseEntity<>(tradeInfoService.getRequiredDocuments(language, category, product,origin, dest, value), HttpStatus.OK);
    }

    @GetMapping("trade-info/search/taxes")
    public ResponseEntity<List<Tax>> getTaxes(
            @RequestParam String language,
            @RequestParam String category,
            @RequestParam String product,
            @RequestParam String origin,
            @RequestParam String dest,
            @RequestParam Double value
    ) {
        return new ResponseEntity<>(tradeInfoService.getTaxes(language, category, product,origin, dest, value), HttpStatus.OK);
    }

}
