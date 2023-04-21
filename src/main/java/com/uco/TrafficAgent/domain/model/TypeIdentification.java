package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import lombok.Getter;

@Getter
public class TypeIdentification {
    private final String type;

    public static TypeIdentification creationType(String type){
        ValidatorAttributes.validateRequired(type, "El tipo de una identifiación no puede ser nula");
        return new TypeIdentification(type);
    }

    private TypeIdentification(String type) {
        this.type = type;
    }
}
