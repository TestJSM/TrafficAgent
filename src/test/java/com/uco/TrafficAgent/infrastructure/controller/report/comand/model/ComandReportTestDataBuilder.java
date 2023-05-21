package com.uco.TrafficAgent.infrastructure.controller.report.comand.model;

import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;

import java.time.LocalDateTime;

public class ComandReportTestDataBuilder {
    private double latitud;
    private double longitud;
    private String description;
    private String url;
    private String user;

    private LocalDateTime dateReport;

    public ComandReportTestDataBuilder byDefault() {

        this.latitud = 24.567890;
        this.longitud = 90.34567;
        this.description = "Esta es una decripción";
        this.url = "Url de prueba";
        this.user = "123";
        this.dateReport = LocalDateTime.now();
        return this;
    }

    public ComandReportTestDataBuilder withLatitude(double latitude){
        this.latitud = latitude;
        return this;
    }

    public ComandReportTestDataBuilder withLongitude(Double longitude){
        this.longitud = longitude;
        return this;
    }

    public ComandReportTestDataBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ComandReportTestDataBuilder withUrl(String url){
        this.url = url;
        return this;
    }

    public ComandReportTestDataBuilder withUser(String user){
        this.user = user;
        return this;
    }

    public ComandReportTestDataBuilder withDate(LocalDateTime dateReport){
        this.dateReport = dateReport;
        return this;
    }

    public DtoSaveReport build() {
        return new DtoSaveReport(latitud, longitud, description, url,user, dateReport);
    }
}