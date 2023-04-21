package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import com.uco.TrafficAgent.domain.validator.ValidatorObjects;
import lombok.Getter;

@Getter
public class User {
    private final String identification;
    private final String fullName;
    private final String cellphone;
    private final String password;
    private final TypeIdentification typeIdentification;

    public static User createUser(String identification, String fullName, String cellphone, String password,
                                  TypeIdentification typeIdentification){
        ValidatorAttributes.validateRequired(identification, "La identifiación del usuraio no puede ser nula");
        ValidatorAttributes.validateRequired(fullName, "El nombre del usuraio no puede ser nulo");
        ValidatorAttributes.validateRequired(cellphone, "El celular del usuraio no puede ser nulo");
        ValidatorAttributes.validateRequired(password, "La contraseña del usuraio no puede ser nulo");
        ValidatorAttributes.stringContainNumbers(cellphone, "El tefono tiene caracteres no soportados");
        ValidatorObjects.validator(typeIdentification, "El tipo de identiicación no puede ser nula");
        ValidatorAttributes.stringContainNumbers(identification, "La identificación tiene caracteres no soportados");
        return new User(identification, fullName, cellphone, password, typeIdentification);
    }

    public User(String identification, String fullName, String cellphone, String password, TypeIdentification typeIdentification) {
        this.identification = identification;
        this.fullName = fullName;
        this.cellphone = cellphone;
        this.password = password;
        this.typeIdentification = typeIdentification;
    }
}
