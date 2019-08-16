package com.labs.sauti.controller;

import com.labs.sauti.model.exchange_rate.ExchangeRate;
import com.labs.sauti.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    // TODO change to "exchange-rates"
    @GetMapping("exchange-rate/all")
    public ResponseEntity<List<ExchangeRate>> getAll() {
        return new ResponseEntity<>(exchangeRateService.getExchangeRates(), HttpStatus.OK);
    }


}
