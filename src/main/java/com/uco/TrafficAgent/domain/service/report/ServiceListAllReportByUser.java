package com.uco.TrafficAgent.domain.service.report;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;

import java.util.List;

public class ServiceListAllReportByUser {
    private final RepositoryReport repositoryReport;

    public ServiceListAllReportByUser(RepositoryReport repositoryReport) {
        this.repositoryReport = repositoryReport;
    }

    public List<DtoReportSummary> executeListAll(String idUser){
        List<DtoReportSummary> report = this.repositoryReport.listReportByUser(idUser);
        if(report.isEmpty()){
            throw new IllegalStateException("No tiene reportes");
        }else {
            return report;
        }
    }
}
