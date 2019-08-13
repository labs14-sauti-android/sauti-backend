package com.labs.sauti.service;

import com.labs.sauti.model.regulated_good.Prohibited;
import com.labs.sauti.model.regulated_good.RegulatedGood;
import com.labs.sauti.model.regulated_good.Restricted;
import com.labs.sauti.model.regulated_good.Sensitive;

import java.util.List;

public interface RegulatedGoodService {

    List<String> getLanguages();
    List<String> getCountries(String language);
    RegulatedGood getRegulatedGood(String language, String country);
    List<Prohibited> getProhibiteds(String language, String country);
    List<Sensitive> getSensitives(String language, String country);
    List<Restricted> getRestricteds(String language, String country);

}
