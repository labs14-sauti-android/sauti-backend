package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Question;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface QuestionService
{
    List<Question> findAll();

    Question findQuestionById(long id);

    List<Question> findByUserName(String username);

    void delete(long id);

    Question save(Question question, Authentication authentication);

}
