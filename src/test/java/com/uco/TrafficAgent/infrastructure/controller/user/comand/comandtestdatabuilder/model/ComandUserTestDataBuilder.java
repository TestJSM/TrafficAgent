package com.uco.TrafficAgent.infrastructure.controller.user.comand.comandtestdatabuilder.model;

import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;

public class ComandUserTestDataBuilder {
    private String identification;
    private String fullName;
    private String cellphone;
    private String password;

    private String email;
    private int type;

    public ComandUserTestDataBuilder byDefault() {

        this.identification = "1234567890";
        this.fullName = "Nombre";
        this.cellphone = "123456789";
        this.password = "w6Unpo<code>t0d0";
        this.email = "@email.com";
        this.type = 1;
        return this;
    }

    public ComandUserTestDataBuilder withIdentification(String identification){
        this.identification = identification;
        return this;
    }

    public ComandUserTestDataBuilder withName(String fullName){
        this.fullName = fullName;
        return this;
    }

    public ComandUserTestDataBuilder withCellPhone(String cellphone){
        this.cellphone = cellphone;
        return this;
    }

    public ComandUserTestDataBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public ComandUserTestDataBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public ComandUserTestDataBuilder withType(int type){
        this.type = type;
        return this;
    }

    public DtoSaveUser build() {
        return new DtoSaveUser(identification, fullName, cellphone, password, email,type);
    }
}
