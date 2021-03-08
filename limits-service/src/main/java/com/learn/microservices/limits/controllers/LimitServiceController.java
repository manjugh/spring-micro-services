package com.learn.microservices.limits.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learn.microservices.limits.model.LimitConfiguration;
import com.learn.microservices.limits.config.*;
@RestController
public class LimitServiceController {

    @Autowired
    private Configuration configuration;

    @GetMapping(path = "/config")
    public LimitConfiguration getLimitConfig() {
        return LimitConfiguration.builder().max(configuration.getMaximum())
                .min(configuration.getMinimum())
                .build();
    }
}
