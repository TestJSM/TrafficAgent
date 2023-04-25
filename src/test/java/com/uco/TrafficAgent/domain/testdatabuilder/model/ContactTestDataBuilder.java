package com.uco.TrafficAgent.domain.testdatabuilder.model;

import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.model.User;

public class ContactTestDataBuilder {
    private double latitud;
    private double longitud;
    private String name;
    private String description;
    private String numberPhone;
    private User user;

    public ContactTestDataBuilder byDefault() {

        this.latitud = 24.567890;
        this.longitud = 90.34567;
        this.description = "Esta es una decripción";
        this.name = "Esto es un nombre";
        this.numberPhone = "12345678900";
        this.user = new UserTestDataBuilder().byDefault().build();
        return this;
    }

    public ContactTestDataBuilder withLatitude(double latitude){
        this.latitud = latitude;
        return this;
    }

    public ContactTestDataBuilder withLongitude(Double longitude){
        this.longitud = longitude;
        return this;
    }

    public ContactTestDataBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ContactTestDataBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ContactTestDataBuilder withNumberPhone(String numberPhone){
        this.numberPhone = numberPhone;
        return this;
    }

    public ContactTestDataBuilder withUser(User user){
        this.user = user;
        return this;
    }

    public Contact build() {
        return Contact.createContact(latitud, longitud, name,description, numberPhone, user);
    }
}
