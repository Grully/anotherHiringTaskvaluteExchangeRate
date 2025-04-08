package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public interface ParsingXMLService {
    public CurrencyExchangeEntity parseXML(String date) throws ParserConfigurationException, IOException, SAXException, ParseException;

}
