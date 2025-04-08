package com.example.hiringtaskexchangerate.controllers;

import com.example.hiringtaskexchangerate.services.ExchangeRateGetXMLDataServiceImpl;
import com.example.hiringtaskexchangerate.services.SavetoDBService;
import com.example.hiringtaskexchangerate.services.SavetoDBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateParamController {

    private final SavetoDBServiceImpl savetoDBService;
@Autowired
    public ExchangeRateParamController(SavetoDBService savetoDBService) {
        this.savetoDBService = (SavetoDBServiceImpl) savetoDBService;
    }


    @GetMapping("/get-rate-xml")
    public void fetchXml(@RequestParam("date") String date) throws Exception {
        savetoDBService.saveCurrencyData(date);
    }
}
