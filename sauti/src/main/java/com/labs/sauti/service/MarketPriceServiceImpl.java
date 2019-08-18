package com.labs.sauti.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.sauti.model.ServerStat;
import com.labs.sauti.model.market_price.MarketPrice;
import com.labs.sauti.repository.MarketPriceRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("marketPriceService")
public class MarketPriceServiceImpl implements MarketPriceService {

    private static final long MAX_AGE = 5L * 7L * 24L * 60L * 60L * 1000L; // 5 weeks
    private static final long UPDATE_DELAY = 70L * 60000L; // 70 mins

    private ServerStatService serverStatService;
    private MarketPriceRepository marketPriceRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public MarketPriceServiceImpl(ServerStatService serverStatService, MarketPriceRepository marketPriceRepository) {
        this.serverStatService = serverStatService;
        this.marketPriceRepository = marketPriceRepository;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = Long.MAX_VALUE)
    public void updateMarketPricesInit() {
        updateMarketPrices();
    }

    @Scheduled(cron = "0 30 * * * *")
    public void updateMarketPricesCron() {
        updateMarketPrices();
    }

    @Override
    @Transactional
    public void updateMarketPrices() {
        System.out.println("updateMarketPrices at " + new Date());

        ResponseEntity<String> responseEntity =
                restTemplate.exchange("http://sautiafrica.org/endpoints/api.php?url=v1/marketPrices/&type=json",
                        HttpMethod.GET, null, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ArrayList<MarketPrice> marketPrices = objectMapper.readValue(responseEntity.getBody(), new TypeReference<ArrayList<MarketPrice>>() {});

            ArrayList<MarketPrice> validMarketPrices = new ArrayList<>(marketPrices.size());
            // filter out market price that exceeds MAX_AGE
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
            long minMillis = System.currentTimeMillis() - MAX_AGE;
            for (MarketPrice marketPrice : marketPrices) {
                try {
                    Date date = simpleDateFormat.parse(marketPrice.getDate());
                    long millis = date.getTime();
                    if (millis > minMillis) {
                        validMarketPrices.add(marketPrice);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            marketPriceRepository.deleteAll();
            marketPriceRepository.saveAll(validMarketPrices);

            serverStatService.addValueOrCreateServerStat(new ServerStat("MarketPrices Update", 1.0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.gc();
    }

    @Override
    public List<MarketPrice> getAll() {
        ArrayList<MarketPrice> marketPrices = new ArrayList<>();
        marketPriceRepository.findAll().iterator().forEachRemaining(marketPrices::add);
        return marketPrices;
    }

    @Override
    public List<String> getCountries() {
        return marketPriceRepository.findAllDistinctCountries();
    }

    @Override
    public List<String> getMarkets(String country) {
        return marketPriceRepository.findAllDistinctMarkets(country);
    }

    @Override
    public List<String> getCategories(String country, String market) {
        return marketPriceRepository.findAllDistinctCategories(country, market);
    }

    @Override
    public List<String> getProducts(String country, String market, String category) {
        return marketPriceRepository.findAllDistinctProducts(country, market, category);
    }

    @Override
    public MarketPrice getMarketPrice(String country, String market, String category, String product) {
        return marketPriceRepository.findMarketPrice(country, market, category, product);
    }
}
