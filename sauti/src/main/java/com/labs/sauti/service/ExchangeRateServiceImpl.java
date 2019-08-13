package com.labs.sauti.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.sauti.model.ServerStat;
import com.labs.sauti.model.exchange_rate.ExchangeRate;
import com.labs.sauti.model.exchange_rate.Rate;
import com.labs.sauti.repository.ExchangeRateRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("exchangeRates")
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final long UPDATE_DELAY = 5L * 60000L; // 5 mins

    private ServerStatService serverStatService;
    private ExchangeRateRepository exchangeRateRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public ExchangeRateServiceImpl(ServerStatService serverStatService, ExchangeRateRepository exchangeRateRepository) {
        this.serverStatService = serverStatService;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = UPDATE_DELAY)
    @Transactional
    public void updateExchangeRates() {
        ResponseEntity<String> responseEntity =
                restTemplate.exchange("http://sautiafrica.org/endpoints/api.php?url=v1/exchangeRates/&type=json",
                        HttpMethod.GET, null, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            HashMap<String, Rate> currencyRateMap = objectMapper.readValue(responseEntity.getBody(), new TypeReference<HashMap<String, Rate>>() {});

            ArrayList<ExchangeRate> exchangeRates = new ArrayList<>(currencyRateMap.size());
            currencyRateMap.forEach((currency, rate) -> exchangeRates.add(new ExchangeRate(currency, rate.getRate())));

            exchangeRateRepository.deleteAll();
            exchangeRateRepository.saveAll(exchangeRates);

            serverStatService.addValueOrCreateServerStat(new ServerStat("ExchangeRates Update", 1.0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExchangeRate> getExchangeRates() {
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        exchangeRateRepository.findAll().iterator().forEachRemaining(exchangeRates::add);
        return exchangeRates;
    }
}