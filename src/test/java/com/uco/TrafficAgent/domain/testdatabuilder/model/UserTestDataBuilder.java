package com.uco.TrafficAgent.domain.testdatabuilder.model;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.testdatabuilder.dto.DtoUserSummaryTestDataBuilder;

public class UserTestDataBuilder {
    private String identification;
    private String fullName;
    private String cellphone;
    private String password;
    private TypeIdentification typeIdentification;

    public UserTestDataBuilder byDefault() {

        this.identification = "1234567890";
        this.fullName = "Nombre";
        this.cellphone = "123456789";
        this.password = "w6Unpo<code>t0d0";
        this.typeIdentification = new TypeIdentificationTestDataBuilder().byDefault().build();
        return this;
    }

    public UserTestDataBuilder withIdentification(String identification){
        this.identification = identification;
        return this;
    }

    public UserTestDataBuilder withName(String fullName){
        this.fullName = fullName;
        return this;
    }

    public UserTestDataBuilder withCellPhone(String cellphone){
        this.cellphone = cellphone;
        return this;
    }

    public UserTestDataBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UserTestDataBuilder withTypeIdentification (TypeIdentification typeIdentification){
        this.typeIdentification = typeIdentification;
        return this;
    }

    public User build() {
        return User.createUser(identification, fullName, cellphone, password,typeIdentification);
    }
}
