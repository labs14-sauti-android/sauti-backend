package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Question;
import com.lambdaschool.starthere.services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuestionsController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    QuestionService questionService;

    @GetMapping(value = "/quotes", produces = {"application/json"})
    public ResponseEntity<?> listAllQuotes(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Question> allQuestions = questionService.findAll();
        return new ResponseEntity<>(allQuestions, HttpStatus.OK);
    }


    @GetMapping(value = "/quote/{quoteId}", produces = {"application/json"})
    public ResponseEntity<?> getQuote(HttpServletRequest request, @PathVariable
                                              Long quoteId)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Question q = questionService.findQuoteById(quoteId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}", produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(HttpServletRequest request, @PathVariable
                                                         String userName)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Question> theQuestions = questionService.findByUserName(userName);
        return new ResponseEntity<>(theQuestions, HttpStatus.OK);
    }



    @PostMapping(value = "/quote")
    public ResponseEntity<?> addNewQuote(HttpServletRequest request, @Valid @RequestBody
            Question newQuestion) throws URISyntaxException
    {
        logger.trace(request.getRequestURI() + " accessed");

        newQuestion = questionService.save(newQuestion);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{quoteid}")
                .buildAndExpand(newQuestion.getQuotesid())
                .toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/quote/{id}")
    public ResponseEntity<?> deleteQuoteById(HttpServletRequest request, @PathVariable
                                                     long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
