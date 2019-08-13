package com.labs.sauti.controller;

import com.labs.sauti.model.regulated_good.Prohibited;
import com.labs.sauti.model.regulated_good.RegulatedGood;
import com.labs.sauti.model.regulated_good.Restricted;
import com.labs.sauti.model.regulated_good.Sensitive;
import com.labs.sauti.service.RegulatedGoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegulatedGoodController {

    private RegulatedGoodService regulatedGoodService;

    public RegulatedGoodController(RegulatedGoodService regulatedGoodService) {
        this.regulatedGoodService = regulatedGoodService;
    }

    @GetMapping("regulated-good/languages")
    public ResponseEntity<List<String>> getLanguages() {
        return new ResponseEntity<>(regulatedGoodService.getLanguages(), HttpStatus.OK);
    }

    @GetMapping("regulated-good/countries")
    public ResponseEntity<List<String>> getCountries(@RequestParam String language) {
        return new ResponseEntity<>(regulatedGoodService.getCountries(language), HttpStatus.OK);
    }

    @GetMapping("regulated-good/search")
    public ResponseEntity<RegulatedGood> getRegulatedGood(@RequestParam String language, @RequestParam String country) {
        return new ResponseEntity<>(regulatedGoodService.getRegulatedGood(language, country), HttpStatus.OK);
    }

    @GetMapping("regulated-good/search/prohibiteds")
    public ResponseEntity<List<Prohibited>> getProhibiteds(@RequestParam String language, @RequestParam String country) {
        return new ResponseEntity<>(regulatedGoodService.getProhibiteds(language, country), HttpStatus.OK);
    }

    @GetMapping("regulated-good/search/sensitives")
    public ResponseEntity<List<Sensitive>> getSensitives(@RequestParam String language, @RequestParam String country) {
        return new ResponseEntity<>(regulatedGoodService.getSensitives(language, country), HttpStatus.OK);
    }

    @GetMapping("regulated-good/search/restricteds")
    public ResponseEntity<List<Restricted>> getRestricteds(@RequestParam String language, @RequestParam String country) {
        return new ResponseEntity<>(regulatedGoodService.getRestricteds(language, country), HttpStatus.OK);
    }
}
