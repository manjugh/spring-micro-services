package com.learn.microservices.currency.conversion.controller;

import com.learn.microservices.currency.conversion.client.CurrencyExchangeClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeClientProxy currencyExchangeClientProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean getConversionController(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/{from}/to/{to}", CurrencyConversionBean.class, Map.of("from", from, "to", to));
        CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
        return CurrencyConversionBean.builder().id(currencyConversionBean.getId())
                .from(from)
                .to(to)
                .conversionMultiple(currencyConversionBean.getConversionMultiple())
                .quantity(quantity)
                .totalCalculationAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()))
                .port(currencyConversionBean.getPort())
                .build();

    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean getConversionControllerFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean currencyConversionBean = currencyExchangeClientProxy.getExchangeValue(from, to);
        return CurrencyConversionBean.builder().id(currencyConversionBean.getId())
                .from(from)
                .to(to)
                .conversionMultiple(currencyConversionBean.getConversionMultiple())
                .quantity(quantity)
                .totalCalculationAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()))
                .port(currencyConversionBean.getPort())
                .build();

    }
}

