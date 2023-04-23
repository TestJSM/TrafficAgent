package com.uco.TrafficAgent.domain.service.report;

import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;

public class ServiceSaveReport {

    private final RepositoryReport repositoryReport;

    public ServiceSaveReport(RepositoryReport repositoryReport) {
        this.repositoryReport = repositoryReport;
    }

    public void executeSave(Report report){
        this.repositoryReport.saveReport(report);
    }
}
