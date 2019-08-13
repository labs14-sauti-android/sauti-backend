package com.labs.sauti.model.trade_info.consume;

import com.labs.sauti.model.trade_info.Procedure;
import com.labs.sauti.model.trade_info.RelevantAgency;
import com.labs.sauti.model.trade_info.RequiredDocument;
import com.labs.sauti.model.trade_info.Tax;

import java.util.ArrayList;

public class TradeInfo_Consume {

    private String productCat;
    private String product;
    private String origin;
    private String dest;
    private String value;

    private ArrayList<Procedure> procedures = new ArrayList<>();

    private ArrayList<RelevantAgency> relevantAgencies = new ArrayList<>();

    private ArrayList<RequiredDocument> requiredDocuments = new ArrayList<>();
    private ArrayList<Tax> taxes = new ArrayList<>();

    public TradeInfo_Consume() {
    }

    public TradeInfo_Consume(String productCat, String product, String origin, String dest, String value, ArrayList<Procedure> procedures, ArrayList<RelevantAgency> relevantAgencies, ArrayList<RequiredDocument> requiredDocuments, ArrayList<Tax> taxes) {
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

    public ArrayList<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(ArrayList<Procedure> procedures) {
        this.procedures = procedures;
    }

    public ArrayList<RelevantAgency> getRelevantAgencies() {
        return relevantAgencies;
    }

    public void setRelevantAgencies(ArrayList<RelevantAgency> relevantAgencies) {
        this.relevantAgencies = relevantAgencies;
    }

    public ArrayList<RequiredDocument> getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(ArrayList<RequiredDocument> requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public ArrayList<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<Tax> taxes) {
        this.taxes = taxes;
    }
}
