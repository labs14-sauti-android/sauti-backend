package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionsid;

    @Column(nullable = false)
    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"questions", "hibernateLazyInitializer"})
    private User user;

    public Question()
    {
    }

    public Question(String question, User user)
    {
        this.question = question;
        this.user = user;
    }

    public long getQuotesid()
    {
        return questionsid;
    }

    public void setQuotesid(long quotesid)
    {
        this.questionsid = quotesid;
    }

    public String getQuote()
    {
        return question;
    }

    public void setQuote(String quote)
    {
        this.question = quote;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}