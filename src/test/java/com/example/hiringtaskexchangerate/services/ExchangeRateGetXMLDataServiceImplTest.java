package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.feignclients.ExchangeRateServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExchangeRateGetXMLDataServiceImplTest {

    @Mock
    private ExchangeRateServiceClient exchangeRateServiceClient;

    @InjectMocks
    private ExchangeRateGetXMLDataServiceImpl exchangeRateGetXMLDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchXML() {

        String date = "2023-10-01";
        String expectedXml = "<xml>test</xml>";

        when(exchangeRateServiceClient.getXmlData(date)).thenReturn(expectedXml);

        String actualXml = exchangeRateGetXMLDataService.fetchXML(date);

        assertEquals(expectedXml, actualXml);
    }
}
