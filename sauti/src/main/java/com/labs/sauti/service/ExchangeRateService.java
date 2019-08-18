package com.labs.sauti.service;

import com.labs.sauti.model.exchange_rate.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {

    void updateExchangeRates();
    List<ExchangeRate> getExchangeRates();

}
