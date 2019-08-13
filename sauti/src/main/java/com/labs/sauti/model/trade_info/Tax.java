package com.labs.sauti.model.trade_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "taxes")
public class Tax {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long taxId;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String taxTitle;

    private String taxPerc;

    public Tax() {
    }

    public Tax(String taxTitle, String taxPerc) {
        this.taxTitle = taxTitle;
        this.taxPerc = taxPerc;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public String getTaxTitle() {
        return taxTitle;
    }

    public void setTaxTitle(String taxTitle) {
        this.taxTitle = taxTitle;
    }

    public String getTaxPerc() {
        return taxPerc;
    }

    public void setTaxPerc(String taxPerc) {
        this.taxPerc = taxPerc;
    }
}
