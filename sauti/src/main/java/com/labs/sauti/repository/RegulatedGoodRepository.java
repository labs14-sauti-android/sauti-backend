package com.labs.sauti.repository;

import com.labs.sauti.model.regulated_good.RegulatedGood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RegulatedGoodRepository extends CrudRepository<RegulatedGood, Long> {

    @Query(value = "SELECT DISTINCT language FROM regulated_goods", nativeQuery = true)
    ArrayList<String> getLanguages();

    @Query(value = "SELECT DISTINCT country FROM regulated_goods WHERE language=:language", nativeQuery = true)
    ArrayList<String> getCountries(String language);

    RegulatedGood getRegulatedGoodByLanguageAndCountry(String language, String country);

}
