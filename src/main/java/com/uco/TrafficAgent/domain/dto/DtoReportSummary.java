package com.uco.TrafficAgent.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReportSummary {
    private  double latitud;
    private  double longitud;
    private  String description;
    private  String url;
    private  String idUser;
    private LocalDateTime dateReport;
}
