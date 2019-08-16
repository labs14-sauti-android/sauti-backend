package com.labs.sauti.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.sauti.model.ServerStat;
import com.labs.sauti.model.regulated_good.Prohibited;
import com.labs.sauti.model.regulated_good.RegulatedGood;
import com.labs.sauti.model.regulated_good.Restricted;
import com.labs.sauti.model.regulated_good.Sensitive;
import com.labs.sauti.model.regulated_good.consume.RegulatedGood_Consume;
import com.labs.sauti.repository.RegulatedGoodRepository;
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

@Service("regulatedGoodService")
public class RegulatedGoodServiceImpl implements RegulatedGoodService {

    private static final long UPDATE_DELAY = 60L * 60000L; // 60 mins

    private ServerStatService serverStatService;
    private RegulatedGoodRepository regulatedGoodRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public RegulatedGoodServiceImpl(
            ServerStatService serverStatService,
            RegulatedGoodRepository regulatedGoodRepository
    ) {
        this.serverStatService = serverStatService;
        this.regulatedGoodRepository = regulatedGoodRepository;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = UPDATE_DELAY)
    @Transactional
    public void updateRegulatedGoods() {
        ResponseEntity<String> responseEntity =
                restTemplate.exchange("http://sautiafrica.org/endpoints/api.php?url=v1/regulatedGoods/&type=json",
                        HttpMethod.GET, null, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            HashMap<String, HashMap<String, RegulatedGood_Consume>> languageCountryRegulatedGoodMap = objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<HashMap<String, HashMap<String, RegulatedGood_Consume>>>() {});

            ArrayList<RegulatedGood> regulatedGoods = new ArrayList<>();
            for (Map.Entry<String, HashMap<String, RegulatedGood_Consume>> languageCountryRegulatedGoodPair : languageCountryRegulatedGoodMap.entrySet()) {
                for (Map.Entry<String, RegulatedGood_Consume> countryRegulatedGoodPair :
                        languageCountryRegulatedGoodPair.getValue().entrySet()) {

                    List<String> prohibitedNames = countryRegulatedGoodPair.getValue().getProhibiteds();
                    ArrayList<Prohibited> prohibiteds = new ArrayList<>(prohibitedNames.size());
                    for (String prohibitedName : prohibitedNames) {
                        prohibiteds.add(new Prohibited(prohibitedName));
                    }

                    List<String> sensitiveNames = countryRegulatedGoodPair.getValue().getSensitives();
                    ArrayList<Sensitive> sensitives = new ArrayList<>(sensitiveNames.size());
                    for (String sensitiveName : sensitiveNames) {
                        sensitives.add(new Sensitive(sensitiveName));
                    }

                    List<String> restrictedNames = countryRegulatedGoodPair.getValue().getRestricteds();
                    ArrayList<Restricted> restricteds = new ArrayList<>(restrictedNames.size());
                    for (String restrictedName : restrictedNames) {
                        restricteds.add(new Restricted(restrictedName));
                    }

                    RegulatedGood newRegulatedGood = new RegulatedGood(
                            languageCountryRegulatedGoodPair.getKey(),
                            countryRegulatedGoodPair.getKey(),
                            prohibiteds,
                            sensitives,
                            restricteds
                    );

                    regulatedGoods.add(newRegulatedGood);
                }
            }

            regulatedGoodRepository.deleteAll();
            regulatedGoodRepository.saveAll(regulatedGoods);

            serverStatService.addValueOrCreateServerStat(new ServerStat("RegulatedGoods Update", 1.0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLanguages() {
        return regulatedGoodRepository.getLanguages();
    }

    @Override
    public List<String> getCountries(String language) {
        return regulatedGoodRepository.getCountries(language);
    }

    @Override
    @Nullable
    public RegulatedGood getRegulatedGood(String language, String country) {
        return regulatedGoodRepository.getRegulatedGoodByLanguageAndCountry(language, country);
    }

    @Override
    public List<Prohibited> getProhibiteds(String language, String country) {
        RegulatedGood regulatedGood = getRegulatedGood(language, country);
        if (regulatedGood == null) return new ArrayList<>();
        return regulatedGood.getProhibiteds();
    }

    @Override
    public List<Sensitive> getSensitives(String language, String country) {
        RegulatedGood regulatedGood = getRegulatedGood(language, country);
        if (regulatedGood == null) return new ArrayList<>();
        return regulatedGood.getSensitives();
    }

    @Override
    public List<Restricted> getRestricteds(String language, String country) {
        RegulatedGood regulatedGood = getRegulatedGood(language, country);
        if (regulatedGood == null) return new ArrayList<>();
        return regulatedGood.getRestricteds();
    }
}
