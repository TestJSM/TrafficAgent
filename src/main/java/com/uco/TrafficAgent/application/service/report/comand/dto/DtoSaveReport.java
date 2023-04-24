package com.uco.TrafficAgent.application.service.report.comand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoSaveReport {
    private double latitud;
    private double longitud;
    private String description;
    private String url;
    private String idUser;
    private LocalDate dateReport;

}
