package com.labs.sauti.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.sauti.model.ServerStat;
import com.labs.sauti.model.exchange_rate.ExchangeRate;
import com.labs.sauti.model.exchange_rate.consume.Rate;
import com.labs.sauti.repository.ExchangeRateRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("exchangeRates")
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final long UPDATE_DELAY = 120L * 60000L; // 120 mins

    private ServerStatService serverStatService;
    private ExchangeRateRepository exchangeRateRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public ExchangeRateServiceImpl(ServerStatService serverStatService, ExchangeRateRepository exchangeRateRepository) {
        this.serverStatService = serverStatService;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = Long.MAX_VALUE)
    public void updateExchangeRatesInit() {
        updateExchangeRates();
    }

    @Scheduled(cron = "0 10 * * * *")
    public void updateExchangeRatesCron() {
        updateExchangeRates();
    }

    @Override
    @Transactional
    public void updateExchangeRates() {
        System.out.println("updateExchangeRates at " + new Date());

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

        System.gc();
    }

    @Override
    public List<ExchangeRate> getExchangeRates() {
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        exchangeRateRepository.findAll().iterator().forEachRemaining(exchangeRates::add);
        return exchangeRates;
    }
}
