package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import com.example.hiringtaskexchangerate.jpa.repositories.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

@Service
public class SavetoDBServiceImpl implements  SavetoDBService{

    ParsingXMLService parsingXMLService;
    CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public SavetoDBServiceImpl(ParsingXMLService parsingXMLServiceImpl, CurrencyExchangeRepository currencyExchangeRepository) {
        this.parsingXMLService = parsingXMLServiceImpl;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public void saveCurrencyData(String date) throws ParserConfigurationException, IOException, ParseException, SAXException {

        CurrencyExchangeEntity currencyExchange = parsingXMLService.parseXML(date);

        currencyExchangeRepository.save(currencyExchange);
    }
}
