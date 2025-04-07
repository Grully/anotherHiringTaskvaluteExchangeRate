package com.example.hiringtaskexchangerate.controllers;

import com.example.hiringtaskexchangerate.services.ExchangeRateGetXMLDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateParamController {

    private final ExchangeRateGetXMLDataService exchangeRateGetXMLDataService;

    @Autowired
    public ExchangeRateParamController(ExchangeRateGetXMLDataService exchangeRateGetXMLDataService) {
        this.exchangeRateGetXMLDataService = exchangeRateGetXMLDataService;
    }

    @GetMapping("/get-rate-xml")
    public void fetchXml(@RequestParam("date") String date) throws Exception {
        exchangeRateGetXMLDataService.fetchXml(date);
    }
}
