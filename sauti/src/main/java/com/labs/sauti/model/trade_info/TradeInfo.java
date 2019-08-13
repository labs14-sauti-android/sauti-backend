package com.labs.sauti.model.trade_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tradeInfos")
public class TradeInfo {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long tradeInfoId;

    private String language;
    private String productCat;
    private String product;
    private String origin;
    private String dest;
    private String value;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Procedure> procedures = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RelevantAgency> relevantAgencies = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RequiredDocument> requiredDocuments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tax> taxes = new ArrayList<>();

    public TradeInfo() {
    }

    public TradeInfo(String language, String productCat, String product, String origin, String dest, String value, List<Procedure> procedures, List<RelevantAgency> relevantAgencies, List<RequiredDocument> requiredDocuments, List<Tax> taxes) {
        this.language = language;
        this.productCat = productCat;
        this.product = product;
        this.origin = origin;
        this.dest = dest;
        this.value = value;
        this.procedures = procedures;
        this.relevantAgencies = relevantAgencies;
        this.requiredDocuments = requiredDocuments;
        this.taxes = taxes;
    }

    public long getTradeInfoId() {
        return tradeInfoId;
    }

    public void setTradeInfoId(long tradeInfoId) {
        this.tradeInfoId = tradeInfoId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<RelevantAgency> getRelevantAgencies() {
        return relevantAgencies;
    }

    public void setRelevantAgencies(List<RelevantAgency> relevantAgencies) {
        this.relevantAgencies = relevantAgencies;
    }

    public List<RequiredDocument> getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(List<RequiredDocument> requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }
}
