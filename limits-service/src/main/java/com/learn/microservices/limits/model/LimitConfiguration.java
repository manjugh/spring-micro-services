package com.learn.microservices.limits.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LimitConfiguration {

    private int max;

    private int min;

}
