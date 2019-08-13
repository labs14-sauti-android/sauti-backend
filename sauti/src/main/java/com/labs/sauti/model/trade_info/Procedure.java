package com.labs.sauti.model.trade_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "procedures")
public class Procedure {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long procedureId;

    private int step;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String description;

    public Procedure() {
    }

    public long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(long procedureId) {
        this.procedureId = procedureId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
