package com.uco.TrafficAgent.infrastructure.controller.user.comand.comandtestdatabuilder.model;

import com.uco.TrafficAgent.domain.model.TypeIdentification;

public class ComandTypeIdentificationTestDataBuilder {

    private String type;

    public ComandTypeIdentificationTestDataBuilder byDefault() {
        this.type = "C.C";
        return this;
    }

    public ComandTypeIdentificationTestDataBuilder withType(String type){
        this.type = type;
        return this;
    }

    public TypeIdentification build(){
        return  TypeIdentification.creationType(type);
    }
}
