package com.example.hiringtaskexchangerate.jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "valute")
public class ValuteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_code")
    private String numCode;

    @Column(name = "char_code")
    private String charCode;

    @Column(name = "nominal")
    private Integer nominal;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;

    @Column(name = "vunit_rate")
    private Double vunitRate;

    @ManyToOne
    @JoinColumn(name = "currency_exchange_id", nullable = false)
    private CurrencyExchangeEntity currencyExchangeEntity;

    public ValuteEntity() {
    }

    public ValuteEntity(Long id, String numCode, String charCode, Integer nominal, String name, Double value, Double vunitRate, CurrencyExchangeEntity currencyExchangeEntity) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.vunitRate = vunitRate;
        this.currencyExchangeEntity = currencyExchangeEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getVunitRate() {
        return vunitRate;
    }

    public void setVunitRate(Double vunitRate) {
        this.vunitRate = vunitRate;
    }

    public CurrencyExchangeEntity getCurrencyExchangeEntity() {
        return currencyExchangeEntity;
    }

    public void setCurrencyExchangeEntity(CurrencyExchangeEntity currencyExchangeEntity) {
        this.currencyExchangeEntity = currencyExchangeEntity;
    }
}