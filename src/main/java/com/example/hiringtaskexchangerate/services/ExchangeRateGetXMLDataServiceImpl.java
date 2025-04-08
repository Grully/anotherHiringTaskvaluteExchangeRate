package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.feignclients.ExchangeRateServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExchangeRateGetXMLDataServiceImpl implements ExchangeRateGetXMLDataService {

    private final ExchangeRateServiceClient ExchangeRateServiceClient;

    @Autowired
    public ExchangeRateGetXMLDataServiceImpl(ExchangeRateServiceClient ExchangeRateServiceClient) {
        this.ExchangeRateServiceClient = ExchangeRateServiceClient;
    }

    @Override
    public String fetchXML(String date) {
        return ExchangeRateServiceClient.getXmlData(date);
    }

}