package com.microservices.limits_service.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Limits {
    private int minimum;
    private int maximum;
}
