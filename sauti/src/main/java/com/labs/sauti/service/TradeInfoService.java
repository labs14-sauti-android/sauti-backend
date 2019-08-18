package com.labs.sauti.service;

import com.labs.sauti.model.trade_info.*;

import java.util.List;

public interface TradeInfoService {

    void updateTradeInfos();
    List<TradeInfo> getAll();
    List<String> getLanguages();
    List<String> getCategories(String language);
    List<String> getProducts(String language, String category);
    List<String> getOrigins(String language, String category, String product);
    List<String> getDests(String language, String category, String product, String origin);
    TradeInfo getTradeInfo(String language, String category, String product, String origin, String dest, double value);
    List<Procedure> getProcedures(String language, String category, String product, String origin, String dest, double value);
    List<RelevantAgency> getRelevantAgencies(String language, String category, String product, String origin, String dest, double value);
    List<RequiredDocument> getRequiredDocuments(String language, String category, String product, String origin, String dest, double value);
    List<Tax> getTaxes(String language, String category, String product, String origin, String dest, double value);

}
