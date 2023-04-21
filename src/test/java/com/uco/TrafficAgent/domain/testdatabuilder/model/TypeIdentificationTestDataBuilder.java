package com.uco.TrafficAgent.domain.testdatabuilder.model;

import com.uco.TrafficAgent.domain.model.TypeIdentification;

public class TypeIdentificationTestDataBuilder {

    private String type;

    public TypeIdentificationTestDataBuilder byDefault() {
        this.type = "C.C";
        return this;
    }

    public TypeIdentificationTestDataBuilder withType(String type){
        this.type = type;
        return this;
    }

    public TypeIdentification build(){
        return  TypeIdentification.creationType(type);
    }
}
