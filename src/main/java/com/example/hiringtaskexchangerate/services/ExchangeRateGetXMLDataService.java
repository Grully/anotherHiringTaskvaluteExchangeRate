package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.feignclients.ExchangeRateServiceClient;
import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import com.example.hiringtaskexchangerate.jpa.entities.ValuteEntity;
import com.example.hiringtaskexchangerate.jpa.repositories.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeRateGetXMLDataService {

    private final ExchangeRateServiceClient ExchangeRateServiceClient;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public ExchangeRateGetXMLDataService(ExchangeRateServiceClient ExchangeRateServiceClient, CurrencyExchangeRepository currencyExchangeRepository) {
        this.ExchangeRateServiceClient = ExchangeRateServiceClient;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public void fetchXml(String date) throws Exception {
        String xmlData = ExchangeRateServiceClient.getXmlData(date);
        //System.out.println(xmlData);
        saveCurrencyData(xmlData);
    }


    public CurrencyExchangeEntity parseXML(String xmlData) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new ByteArrayInputStream(xmlData.getBytes()));

        Element rootElement = document.getDocumentElement();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = dateFormat.parse(rootElement.getAttribute("Date"));

        CurrencyExchangeEntity currencyExchange = new CurrencyExchangeEntity();
        currencyExchange.setDate(date);

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

    public void saveCurrencyData(String xmlData) throws Exception {

        CurrencyExchangeEntity currencyExchange = parseXML(xmlData);

        currencyExchangeRepository.save(currencyExchange);
    }
}