package com.example.hiringtaskexchangerate.services;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public interface SavetoDBService {
    public void saveCurrencyData(String date) throws ParserConfigurationException, IOException, ParseException, SAXException;
}
