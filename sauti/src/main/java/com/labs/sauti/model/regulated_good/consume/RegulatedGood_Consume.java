package com.labs.sauti.model.regulated_good.consume;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RegulatedGood_Consume {

    @JsonProperty("Prohibited")
    private List<String> prohibiteds = new ArrayList<>();

    @JsonProperty("Sensitive")
    private List<String> sensitives = new ArrayList<>();

    @JsonProperty("Restricted")
    private List<String> restricteds = new ArrayList<>();

    public RegulatedGood_Consume() {
    }

    public RegulatedGood_Consume(List<String> prohibiteds, List<String> sensitives, List<String> restricteds) {
        this.prohibiteds = prohibiteds;
        this.sensitives = sensitives;
        this.restricteds = restricteds;
    }

    public List<String> getProhibiteds() {
        return prohibiteds;
    }

    public void setProhibiteds(List<String> prohibiteds) {
        this.prohibiteds = prohibiteds;
    }

    public List<String> getSensitives() {
        return sensitives;
    }

    public void setSensitives(List<String> sensitives) {
        this.sensitives = sensitives;
    }

    public List<String> getRestricteds() {
        return restricteds;
    }

    public void setRestricteds(List<String> restricteds) {
        this.restricteds = restricteds;
    }
}
