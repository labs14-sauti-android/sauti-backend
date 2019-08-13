package com.labs.sauti.model.trade_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "requiredDocuments")
public class RequiredDocument {

    @JsonIgnore
    @Id
    @GeneratedValue
    private long requiredDocumentId;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String docTitle;

    @Lob // varchar(max)
    @Type(type = "text") // needed for posgresql when using @Lob
    private String docDescription;

    public RequiredDocument() {
    }

    public RequiredDocument(String docTitle, String docDescription) {
        this.docTitle = docTitle;
        this.docDescription = docDescription;
    }

    public long getRequiredDocumentId() {
        return requiredDocumentId;
    }

    public void setRequiredDocumentId(long requiredDocumentId) {
        this.requiredDocumentId = requiredDocumentId;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

}
