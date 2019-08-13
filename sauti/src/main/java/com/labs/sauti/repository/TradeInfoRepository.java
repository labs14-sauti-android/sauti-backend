package com.labs.sauti.repository;

import com.labs.sauti.model.trade_info.TradeInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TradeInfoRepository extends CrudRepository<TradeInfo, Long> {

    @Query(value = "SELECT DISTINCT language FROM trade_infos", nativeQuery = true)
    ArrayList<String> getLanguages();

    @Query(value = "SELECT DISTINCT product_cat FROM trade_infos WHERE language=:language", nativeQuery = true)
    ArrayList<String> getCategories(String language);

    @Query(value = "SELECT DISTINCT product FROM trade_infos WHERE language=:language AND product_cat=:category", nativeQuery = true)
    ArrayList<String> getProducts(String language, String category);

    @Query(value = "SELECT DISTINCT origin FROM trade_infos WHERE language=:language AND product_cat=:category AND product=:product", nativeQuery = true)
    ArrayList<String> getOrigins(String language, String category, String product);

    @Query(value = "SELECT DISTINCT dest FROM trade_infos WHERE language=:language AND product_cat=:category AND product=:product AND origin=:origin", nativeQuery = true)
    ArrayList<String> getDests(String language, String category, String product, String origin);

    TradeInfo getTradeInfoByLanguageAndProductCatAndProductAndOriginAndDestAndValue(String language, String category, String product, String origin, String dest, String value);

    @Query(value = "SELECT * FROM trade_infos WHERE language=:language AND product_cat=:category AND product=:product AND origin=:origin AND dest=:dest AND value=:value", nativeQuery = true)
    TradeInfo findTradeInfo(String language, String category, String product, String origin, String dest, String value);
}
