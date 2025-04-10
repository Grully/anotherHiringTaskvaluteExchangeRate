package com.example.hiringtaskexchangerate.services;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import com.example.hiringtaskexchangerate.jpa.repositories.CurrencyExchangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

import static org.mockito.Mockito.*;

public class SavetoDBServiceImplTest {

    @Mock
    private ParsingXMLService parsingXMLService;

    @Mock
    private CurrencyExchangeRepository currencyExchangeRepository;

    @InjectMocks
    private SavetoDBServiceImpl saveToDBService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCurrencyData() throws ParserConfigurationException, IOException, SAXException, ParseException {
        String date = "01/10/2023";
        CurrencyExchangeEntity currencyExchange = new CurrencyExchangeEntity();
        when(parsingXMLService.parseXML(date)).thenReturn(currencyExchange);

        saveToDBService.saveCurrencyData(date);

        verify(parsingXMLService).parseXML(date);
        verify(currencyExchangeRepository).save(currencyExchange);
    }

    @Test
    public void testSaveCurrencyDataThrowsExceptions() throws ParserConfigurationException, IOException, SAXException, ParseException {

        String date = "01/10/2023";

        when(parsingXMLService.parseXML(date)).thenThrow(new ParseException("Ошибка парсинга", 0));

        try {
            saveToDBService.saveCurrencyData(date);
            assert false : "Expected an exception to be thrown";
        } catch (ParseException e) {
            assert true;
        }

        verify(parsingXMLService).parseXML(date);
        verify(currencyExchangeRepository, never()).save(any());
    }
}