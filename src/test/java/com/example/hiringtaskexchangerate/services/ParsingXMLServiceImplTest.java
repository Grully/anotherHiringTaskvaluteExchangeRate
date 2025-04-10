package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import com.example.hiringtaskexchangerate.jpa.entities.ValuteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ParsingXMLServiceImplTest {

    @Mock
    private ExchangeRateGetXMLDataServiceImpl exchangeRateGetXMLDataServiceImpl;

    @InjectMocks
    private ParsingXMLServiceImpl parsingXMLService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testParseXML() throws ParserConfigurationException, IOException, SAXException, ParseException {
        // Arrange
        String date = "01/10/2023";
        String xmlData = "<Valute>" +
                "<NumCode>840</NumCode>" +
                "<CharCode>USD</CharCode>" +
                "<Nominal>1</Nominal>" +
                "<Name>Доллар США</Name>" +
                "<Value>75,00</Value>" +
                "<VunitRate>1,00</VunitRate>" +
                "</Valute>";

        when(exchangeRateGetXMLDataServiceImpl.fetchXML(date)).thenReturn(xmlData);

        CurrencyExchangeEntity currencyExchange = parsingXMLService.parseXML(date);

        assertNotNull(currencyExchange);
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse(date), currencyExchange.getDate());

        List<ValuteEntity> valutes = currencyExchange.getValutes();
        assertNotNull(valutes);
        assertEquals(1, valutes.size());

        ValuteEntity valute = valutes.get(0);
        assertEquals("840", valute.getNumCode());
        assertEquals("USD", valute.getCharCode());
        assertEquals(1, valute.getNominal());
        assertEquals("Доллар США", valute.getName());
        assertEquals(75.00, valute.getValue());
        assertEquals(1.00, valute.getVunitRate());
    }

    @Test
    public void testParseXMLWithEmptyResponse() throws ParserConfigurationException {

        String date = "01/10/2023";

        when(exchangeRateGetXMLDataServiceImpl.fetchXML(date)).thenReturn("");

        try {
            parsingXMLService.parseXML(date);
            assert false : "Expected an exception to be thrown";
        } catch (SAXException | IOException | ParseException e) {
            assert true;
        }
    }
}