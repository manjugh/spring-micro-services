package com.learn.microservices.currency.conversion.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionBean {

    private  long id;
    private  String from;

    private  String to;

    private  BigDecimal conversionMultiple;

    private  BigDecimal quantity;

    private  BigDecimal totalCalculationAmount;

    private  int port;


}
