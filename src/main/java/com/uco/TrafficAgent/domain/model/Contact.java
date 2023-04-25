package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import com.uco.TrafficAgent.domain.validator.ValidatorObjects;
import lombok.Getter;

@Getter
public class Contact {

    private final double latitud;
    private final double longitud;
    private final String name;
    private final String description;
    private final String numberPhone;
    private final User user;

    public static Contact createContact(double latitud, double longitud, String name, String description, String numberPhone, User user){
        ValidatorAttributes.validateRequired(description, "La descripción del conatcto no puede ser nulo");
        ValidatorAttributes.validateRequired(name, "El nombre del contacto no puede ser nulo");
        ValidatorAttributes.stringContainNumbersDecimal(String.valueOf(latitud), "La latitud tiene que ser un número decimal");
        ValidatorAttributes.stringContainNumbersDecimal(String.valueOf(longitud), "La logitud tiene que ser un número decimal");
        ValidatorAttributes.validateRequired(numberPhone, "El conatcto debe tener un telefono asociado");
        ValidatorAttributes.stringContainNumbers(numberPhone, "El número telefonico solo puede tener numeros");
        ValidatorObjects.validator(user, "Si hay un contacto debe haber un usuario asociado");
        return new Contact(latitud, longitud, name, description, numberPhone, user);
    }

    private Contact(double latitud, double longitud, String name, String description, String numberPhone, User user) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.name = name;
        this.description = description;
        this.numberPhone = numberPhone;
        this.user = user;
    }
}
