package com.uco.TrafficAgent.infrastructure.controller.user.comand.model;

import com.uco.TrafficAgent.application.service.user.comand.dto.DtoRolUser;

public class ComandRolTestDataBuilder {
    private String rol;

    public ComandRolTestDataBuilder byDefault() {

        this.rol = "Peatón";
        return this;
    }

    public ComandRolTestDataBuilder withRol(String rol){
        this.rol = rol;
        return this;
    }

    public DtoRolUser build() {
        return new DtoRolUser(rol);
    }
}
