package com.uco.TrafficAgent.domain.service.report;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;

import java.time.LocalDateTime;
import java.util.List;

public class ServiceListAllReportByDate {

    private final RepositoryReport repositoryReport;

    public ServiceListAllReportByDate(RepositoryReport repositoryReport) {
        this.repositoryReport = repositoryReport;
    }

    public List<DtoReportSummary> executeListAll(LocalDateTime dateTime){
        LocalDateTime dateTimeAfter = dateTime.plusHours(12);
        List<DtoReportSummary> reports = this.repositoryReport.listAllReportByDate(dateTime, dateTimeAfter);
        if(reports.isEmpty()){
            throw new IllegalStateException("No hay reportes asociados al usuario en este horario");
        }else {
            return reports;
        }
    }

}
