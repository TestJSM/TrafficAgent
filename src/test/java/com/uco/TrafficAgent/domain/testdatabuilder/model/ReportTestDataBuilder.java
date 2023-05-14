package com.uco.TrafficAgent.domain.testdatabuilder.model;

import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportTestDataBuilder {
    private double latitud;
    private double longitud;
    private String description;
    private String url;
    private User user;

    private LocalDateTime dateReport;

    public ReportTestDataBuilder byDefault() {

        this.latitud = 24.567890;
        this.longitud = 90.34567;
        this.description = "Esta es una decripción";
        this.url = "Url de prueba";
        this.user = new UserTestDataBuilder().byDefault().build();
        this.dateReport = LocalDateTime.now();
        return this;
    }

    public ReportTestDataBuilder withLatitude(double latitude){
        this.latitud = latitude;
        return this;
    }

    public ReportTestDataBuilder withLongitude(Double longitude){
        this.longitud = longitude;
        return this;
    }

    public ReportTestDataBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ReportTestDataBuilder withUrl(String url){
        this.url = url;
        return this;
    }

    public ReportTestDataBuilder withUser(User user){
        this.user = user;
        return this;
    }

    public ReportTestDataBuilder withDate(LocalDateTime dateReport){
        this.dateReport = dateReport;
        return this;
    }

    public Report build() {
        return Report.createReport(latitud, longitud, description, url,user, dateReport);
    }
}
