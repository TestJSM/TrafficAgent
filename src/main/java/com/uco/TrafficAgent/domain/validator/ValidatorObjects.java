package com.uco.TrafficAgent.domain.validator;

public class ValidatorObjects {

    private ValidatorObjects() {
    }

    public static void validator(Object object, String message){
        if(object == null){
            throw new IllegalArgumentException(message);
        }
    }
}
