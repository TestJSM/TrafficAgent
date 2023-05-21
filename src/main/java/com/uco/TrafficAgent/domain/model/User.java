package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import com.uco.TrafficAgent.domain.validator.ValidatorObjects;
import lombok.Getter;

import java.util.List;

@Getter
public class User {
    private final String identification;
    private final String fullName;
    private final String cellphone;
    private final String password;

    private final String email;
    private final TypeIdentification typeIdentification;
    private List<RolUser> roles;

    public static User createUser(String identification, String fullName, String cellphone, String password,
                                  String email, TypeIdentification typeIdentification, List<RolUser> roles){
        ValidatorAttributes.validateRequired(identification, "La identifiación del usuraio no puede ser nula");
        ValidatorAttributes.validateRequired(fullName, "El nombre del usuraio no puede ser nulo");
        ValidatorAttributes.validateRequired(cellphone, "El celular del usuraio no puede ser nulo");
        ValidatorAttributes.validateRequired(password, "La contraseña del usuraio no puede ser nulo");
        ValidatorAttributes.validateRequired(email, "Un usuario debe tener un email asociado");
        ValidatorAttributes.stringContainNumbers(cellphone, "El tefono tiene caracteres no soportados");
        ValidatorObjects.validator(typeIdentification, "El tipo de identiicación no puede ser nula");
        ValidatorAttributes.stringContainNumbers(identification, "La identificación tiene caracteres no soportados");
        ValidatorAttributes.noEmpty(roles, "Un usuario debe tener al menos un rol");
        return new User(identification, fullName, cellphone, password, email, typeIdentification, roles);
    }

    public User(String identification, String fullName, String cellphone, String password, String email,
                TypeIdentification typeIdentification, List<RolUser> roles) {
        this.identification = identification;
        this.fullName = fullName;
        this.cellphone = cellphone;
        this.password = password;
        this.email = email;
        this.typeIdentification = typeIdentification;
        this.roles = roles;
    }
}
