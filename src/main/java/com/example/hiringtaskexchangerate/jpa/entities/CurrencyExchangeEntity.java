package com.example.hiringtaskexchangerate.jpa.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "currency_exchange")
public class CurrencyExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "currencyExchangeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ValuteEntity> valutes;

    public CurrencyExchangeEntity() {
    }

    public CurrencyExchangeEntity(int id, Date date, List<ValuteEntity> valutes) {
        this.id = id;
        this.date = date;
        this.valutes = valutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ValuteEntity> getValutes() {
        return valutes;
    }

    public void setValutes(List<ValuteEntity> valutes) {
        this.valutes = valutes;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeEntity{" +
                "id=" + id +
                ", date=" + date +
                ", valutes=" + valutes +
                '}';
    }
}
