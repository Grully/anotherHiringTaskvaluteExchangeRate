package com.example.hiringtaskexchangerate.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "externalService", url = "https://cbr.ru/scripts")
public interface ExchangeRateServiceClient {

    @GetMapping(value = "/XML_daily.asp")
    String getXmlData(@RequestParam("date_req") String date);
}