package com.example.hiringtaskexchangerate.jpa.repositories;

import com.example.hiringtaskexchangerate.jpa.entities.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {
}