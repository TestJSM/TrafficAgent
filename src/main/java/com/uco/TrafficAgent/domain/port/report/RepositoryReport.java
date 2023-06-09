package com.uco.TrafficAgent.domain.port.report;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryReport {

    List<DtoReportSummary> listReportByUser(String idUser);
    void saveReport(Report report);
    List<DtoReportSummary> listAllReportByDate(LocalDateTime start, LocalDateTime end);
}
