package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParsingXMLService {
    private final ExchangeRateGetXMLDataService exchangeRateGetXMLDataService;
    @Autowired
    ParsingXMLService(ExchangeRateGetXMLDataService exchangeRateGetXMLDataService) {
        this.exchangeRateGetXMLDataService = exchangeRateGetXMLDataService;
    }

}
