package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import com.example.hiringtaskexchangerate.jpa.entities.ValuteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParsingXMLServiceImpl implements ParsingXMLService{
    private final ExchangeRateGetXMLDataServiceImpl exchangeRateGetXMLDataServiceImpl;
    @Autowired
    ParsingXMLServiceImpl(ExchangeRateGetXMLDataServiceImpl exchangeRateGetXMLDataServiceImpl) {
        this.exchangeRateGetXMLDataServiceImpl = exchangeRateGetXMLDataServiceImpl;
    }
    public CurrencyExchangeEntity parseXML(String date) throws ParserConfigurationException, IOException, SAXException, ParseException {

        String xmlData = exchangeRateGetXMLDataServiceImpl.fetchXML(date);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new ByteArrayInputStream(xmlData.getBytes()));

        Element rootElement = document.getDocumentElement();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        CurrencyExchangeEntity currencyExchange = new CurrencyExchangeEntity();
        currencyExchange.setDate(dateFormat.parse(date));

        NodeList valuteNodes = document.getElementsByTagName("Valute");

        List<ValuteEntity> valutesList = new ArrayList<>();

        for (int i = 0; i < valuteNodes.getLength(); i++) {
            Element valuteElement = (Element) valuteNodes.item(i);

            ValuteEntity valute = new ValuteEntity();
            valute.setNumCode(valuteElement.getElementsByTagName("NumCode").item(0).getTextContent());
            valute.setCharCode(valuteElement.getElementsByTagName("CharCode").item(0).getTextContent());
            valute.setNominal(Integer.parseInt(valuteElement.getElementsByTagName("Nominal").item(0).getTextContent()));
            valute.setName(valuteElement.getElementsByTagName("Name").item(0).getTextContent().replace(',', '.'));
            valute.setValue(Double.parseDouble(valuteElement.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.')));
            valute.setVunitRate(Double.parseDouble(valuteElement.getElementsByTagName("VunitRate").item(0).getTextContent().replace(',', '.')));
            valute.setCurrencyExchangeEntity(currencyExchange);
            valutesList.add(valute);
        }

        currencyExchange.setValutes(valutesList);

        return currencyExchange;
    }
}
