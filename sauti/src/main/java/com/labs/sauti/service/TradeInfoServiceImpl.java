package com.labs.sauti.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.sauti.model.ServerStat;
import com.labs.sauti.model.trade_info.*;
import com.labs.sauti.model.trade_info.consume.TradeInfo_Consume;
import com.labs.sauti.repository.TradeInfoRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tradeInfoService")
public class TradeInfoServiceImpl implements TradeInfoService {

    private static final long UPDATE_DELAY = 80L * 60000L; // 80 mins

    private ServerStatService serverStatService;
    private TradeInfoRepository tradeInfoRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public TradeInfoServiceImpl(ServerStatService serverStatService, TradeInfoRepository tradeInfoRepository) {
        this.serverStatService = serverStatService;
        this.tradeInfoRepository = tradeInfoRepository;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = UPDATE_DELAY)
    @Transactional
    public void updateTradeInfos() {
        ResponseEntity<String> responseEntity =
                restTemplate.exchange("http://sautiafrica.org/endpoints/api.php?url=v1/tradeProcedures/&type=json",
                        HttpMethod.GET, null, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            HashMap<String, ArrayList<TradeInfo_Consume>> languageTradeInfosMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, ArrayList<TradeInfo_Consume>>>() {});

            int tradeInfosSize = 0;
            for (Map.Entry<String, ArrayList<TradeInfo_Consume>> pair : languageTradeInfosMap.entrySet()) {
                tradeInfosSize += pair.getValue().size();
            }

            ArrayList<TradeInfo> tradeInfos = new ArrayList<>(tradeInfosSize);
            for (Map.Entry<String, ArrayList<TradeInfo_Consume>> pair : languageTradeInfosMap.entrySet()) {
                for (TradeInfo_Consume tradeInfo_consume : pair.getValue()) {

                    TradeInfo tradeInfo = new TradeInfo(
                            pair.getKey(),
                            tradeInfo_consume.getProductCat(),
                            tradeInfo_consume.getProduct(),
                            tradeInfo_consume.getOrigin(),
                            tradeInfo_consume.getDest(),
                            tradeInfo_consume.getValue(),
                            tradeInfo_consume.getProcedures(),
                            tradeInfo_consume.getRelevantAgencies(),
                            tradeInfo_consume.getRequiredDocuments(),
                            tradeInfo_consume.getTaxes()
                    );

                    tradeInfos.add(tradeInfo);
                }
            }

            tradeInfoRepository.deleteAll();
            tradeInfoRepository.saveAll(tradeInfos);

            serverStatService.addValueOrCreateServerStat(new ServerStat("TradeInfos Update", 1.0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TradeInfo> getAll() {
        ArrayList<TradeInfo> tradeInfos = new ArrayList<>();
        tradeInfoRepository.findAll().iterator().forEachRemaining(tradeInfos::add);
        return tradeInfos;
    }

    @Override
    public List<String> getLanguages() {
        return tradeInfoRepository.getLanguages();
    }

    @Override
    public List<String> getCategories(String language) {
        return tradeInfoRepository.getCategories(language);
    }

    @Override
    public List<String> getProducts(String language, String category) {
        return tradeInfoRepository.getProducts(language, category);
    }

    @Override
    public List<String> getOrigins(String language, String category, String product) {
        return tradeInfoRepository.getOrigins(language, category, product);
    }

    @Override
    public List<String> getDests(String language, String category, String product, String origin) {
        return tradeInfoRepository.getDests(language, category, product, origin);
    }

    @Override
    @Nullable
    public TradeInfo getTradeInfo(String language, String category, String product, String origin, String dest, double value) {
        String valueStr;
        if (value < 2000) {
            valueStr = "under2000USD";
        } else {
            valueStr = "over2000USD";
        }

        return tradeInfoRepository.findTradeInfo(language, category, product, origin, dest, valueStr);
    }

    @Override
    public List<Procedure> getProcedures(String language, String category, String product, String origin, String dest, double value) {
        TradeInfo tradeInfo = getTradeInfo(language, category, product, origin, dest, value);
        if (tradeInfo == null) return new ArrayList<>();
        return tradeInfo.getProcedures();
    }

    @Override
    public List<RelevantAgency> getRelevantAgencies(String language, String category, String product, String origin, String dest, double value) {
        TradeInfo tradeInfo = getTradeInfo(language, category, product, origin, dest, value);
        if (tradeInfo == null) return new ArrayList<>();
        return tradeInfo.getRelevantAgencies();
    }

    @Override
    public List<RequiredDocument> getRequiredDocuments(String language, String category, String product, String origin, String dest, double value) {
        TradeInfo tradeInfo = getTradeInfo(language, category, product, origin, dest, value);
        if (tradeInfo == null) return new ArrayList<>();
        return tradeInfo.getRequiredDocuments();
    }

    @Override
    public List<Tax> getTaxes(String language, String category, String product, String origin, String dest, double value) {
        TradeInfo tradeInfo = getTradeInfo(language, category, product, origin, dest, value);
        if (tradeInfo == null) return new ArrayList<>();
        return tradeInfo.getTaxes();
    }
}
