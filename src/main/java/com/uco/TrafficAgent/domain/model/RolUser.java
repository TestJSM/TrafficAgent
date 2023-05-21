package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;

public class RolUser {
    private final String rol;

    public static RolUser of(String rol) {

        ValidatorAttributes.validateRequired(rol, "El rol no puede ser vacio");

        return new RolUser(rol);
    }

    private RolUser(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
