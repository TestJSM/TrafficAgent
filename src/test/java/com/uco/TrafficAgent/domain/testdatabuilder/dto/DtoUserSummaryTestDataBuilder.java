package com.uco.TrafficAgent.domain.testdatabuilder.dto;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.testdatabuilder.model.TypeIdentificationTestDataBuilder;

public class DtoUserSummaryTestDataBuilder {

    private String identification;
    private String fullName;
    private String cellphone;
    private TypeIdentification typeIdentification;

    public DtoUserSummaryTestDataBuilder byDefault() {

        this.identification = "1234567890";
        this.fullName = "Nombre";
        this.cellphone = "123456789";
        this.typeIdentification = new TypeIdentificationTestDataBuilder().byDefault().build();
        return this;
    }

    public DtoUserSummaryTestDataBuilder withIdentification(String identification){
        this.identification = identification;
        return this;
    }

    public DtoUserSummaryTestDataBuilder withName(String fullName){
        this.fullName = fullName;
        return this;
    }

    public DtoUserSummaryTestDataBuilder withCellPhone(String cellphone){
        this.cellphone = cellphone;
        return this;
    }

    public DtoUserSummaryTestDataBuilder withTypeIdentification (TypeIdentification typeIdentification){
        this.typeIdentification = typeIdentification;
        return this;
    }

    public DtoUserSummary build() {
        return new DtoUserSummary(identification, fullName, cellphone, typeIdentification);
    }
}
