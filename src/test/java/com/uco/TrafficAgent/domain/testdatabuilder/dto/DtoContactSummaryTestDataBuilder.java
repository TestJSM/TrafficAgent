package com.uco.TrafficAgent.domain.testdatabuilder.dto;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.testdatabuilder.model.TypeIdentificationTestDataBuilder;

public class DtoContactSummaryTestDataBuilder {

    private String name;
    private String description;
    private String numberPhone;

    public DtoContactSummaryTestDataBuilder byDefault() {

        this.name = "Nombre";
        this.description = "Descripción";
        this.numberPhone = "123456789";
        return this;
    }

    public DtoContactSummaryTestDataBuilder withName(String name){
        this.name = name;
        return this;
    }

    public DtoContactSummaryTestDataBuilder withCellPhone(String numberPhone){
        this.numberPhone = numberPhone;
        return this;
    }

    public DtoContactSummaryTestDataBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public DtoContactSummary build() {
        return new DtoContactSummary(name, description, numberPhone);
    }
}
