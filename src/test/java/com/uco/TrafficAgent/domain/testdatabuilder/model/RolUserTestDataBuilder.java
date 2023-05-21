package com.uco.TrafficAgent.domain.testdatabuilder.model;

import com.uco.TrafficAgent.application.service.user.comand.dto.DtoRolUser;
import com.uco.TrafficAgent.domain.model.RolUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;

public class RolUserTestDataBuilder {

    private String rol;

    public RolUserTestDataBuilder byDefault() {

        this.rol = "Peatón";
        return this;
    }

    public RolUserTestDataBuilder withRol(String rol){
        this.rol = rol;
        return this;
    }

    public RolUser build() {
        return RolUser.of(rol);
    }

}
