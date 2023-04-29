package com.uco.TrafficAgent.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoContactSummary {

    private final String name;
    private final String description;
    private final String numberPhone;
    private final double distancia;
}
