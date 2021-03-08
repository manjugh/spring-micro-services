package com.learn.microservices.currency.exchange.controller;

import com.learn.microservices.currency.exchange.model.ExchangeValue;
import com.learn.microservices.currency.exchange.repo.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        com.learn.microservices.currency.exchange.model.ExchangeValue byFromAndTo = exchangeValueRepository.findByFromAndTo(from, to);
        byFromAndTo.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return byFromAndTo;
    }
}
