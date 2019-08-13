package com.labs.sauti.model.trade_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "relevantAgencies")
public class RelevantAgency {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long relevantAgencyId;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String agencyName;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String agencyDescription;

    public RelevantAgency() {
    }

    public RelevantAgency(String agencyName, String agencyDescription) {
        this.agencyName = agencyName;
        this.agencyDescription = agencyDescription;
    }

    public long getRelevantAgencyId() {
        return relevantAgencyId;
    }

    public void setRelevantAgencyId(long relevantAgencyId) {
        this.relevantAgencyId = relevantAgencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyDescription() {
        return agencyDescription;
    }

    public void setAgencyDescription(String agencyDescription) {
        this.agencyDescription = agencyDescription;
    }
}
