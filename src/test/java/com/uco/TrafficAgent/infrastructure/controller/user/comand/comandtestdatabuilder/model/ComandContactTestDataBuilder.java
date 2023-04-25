package com.uco.TrafficAgent.infrastructure.controller.user.comand.comandtestdatabuilder.model;

import com.uco.TrafficAgent.application.service.contact.comand.dto.DtoSaveContact;
import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;

public class ComandContactTestDataBuilder {
    private double latitud;
    private double longitud;
    private String name;
    private String description;
    private String numberPhone;
    private String user;

    public ComandContactTestDataBuilder byDefault() {

        this.latitud = 24.56789;
        this.longitud = 90.34567;
        this.name = "Esto es un nombre";
        this.description = "Esta es una decripción";
        this.numberPhone = "1234567890";
        this.user = "123456789";
        return this;
    }

    public ComandContactTestDataBuilder withLatitude(double latitude){
        this.latitud = latitude;
        return this;
    }

    public ComandContactTestDataBuilder withLongitude(Double longitude){
        this.longitud = longitude;
        return this;
    }

    public ComandContactTestDataBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ComandContactTestDataBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ComandContactTestDataBuilder withNumberPhone(String numberPhone){
        this.numberPhone = numberPhone;
        return this;
    }

    public ComandContactTestDataBuilder withUser(String user){
        this.user = user;
        return this;
    }

    public DtoSaveContact build() {
        return new DtoSaveContact(latitud, longitud, name,description, numberPhone, user);
    }
}
