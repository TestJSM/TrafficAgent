package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import com.uco.TrafficAgent.domain.validator.ValidatorObjects;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Report {
    private final double latitud;
    private final double longitud;
    private final String description;
    private final String url;
    private final User user;

    private final LocalDateTime dateReport;

    public static Report createReport(double latitud, double longitud, String description, String url, User user, LocalDateTime dateReport){
        ValidatorAttributes.validateRequired(description, "La descripción del reporte no puede ser nulo");
        ValidatorAttributes.validateRequired(url, "La url del reporte no puede ser nulo");
        ValidatorAttributes.stringContainNumbersDecimal(String.valueOf(latitud), "La latitud tiene que ser un número decimal");
        ValidatorAttributes.stringContainNumbersDecimal(String.valueOf(longitud), "La logitud tiene que ser un número decimal");
        ValidatorObjects.validator(user, "Si hay un registro debe haber un usuario asociado");
        return new Report(latitud, longitud, description, url, user, dateReport);
    }

    private Report(double latitud, double longitud, String description, String url, User user, LocalDateTime dateReport) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.description = description;
        this.url = url;
        this.user = user;
        this.dateReport = dateReport;
    }
}
