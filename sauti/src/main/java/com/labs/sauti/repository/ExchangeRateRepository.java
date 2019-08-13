package com.labs.sauti.repository;

import com.labs.sauti.model.exchange_rate.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {

}
