package com.uco.TrafficAgent.infrastructure.repository.report.adapter.repository;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.infrastructure.repository.report.adapter.entity.EntityReport;
import com.uco.TrafficAgent.infrastructure.repository.report.adapter.repository.jpa.RepositoryReportJpa;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.repository.jpa.RepositoryUserJpa;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RepositoryReportDB implements RepositoryReport {
    private final RepositoryReportJpa repositoryReportJpa;
    private final RepositoryUserJpa repositoryUserJpa;

    public RepositoryReportDB(RepositoryReportJpa repositoryReportJpa, RepositoryUserJpa repositoryUserJpa) {
        this.repositoryReportJpa = repositoryReportJpa;
        this.repositoryUserJpa = repositoryUserJpa;
    }


    @Override
    public List<DtoReportSummary> listReportByUser(String idUser) {
        List<EntityReport> entities = this.repositoryReportJpa.findByIdentification(idUser);
        return entities.stream().map(entity -> new DtoReportSummary(
                entity.getLatitud(), entity.getLongitud(), entity.getDescription(), entity.getUrl(),
                entity.getEntityUser().getIdentification(),
                entity.getDateReport())).toList();
    }

    @Override
    public void saveReport(Report report) {
        EntityUser entityUser = this.repositoryUserJpa.findByIdentification(report.getUser().getIdentification());
        EntityReport entityReport = new EntityReport(report.getLatitud(), report.getLongitud(),
                report.getDescription(), report.getUrl(), report.getDateReport(),entityUser);

        this.repositoryReportJpa.save(entityReport);
    }

    @Override
    public List<DtoReportSummary> listAllReportByDate(LocalDateTime start, LocalDateTime end) {
        List<EntityReport> entities = this.repositoryReportJpa.findByDateBetween(start, end);
        return entities.stream().map(entity -> new DtoReportSummary(
                entity.getLatitud(), entity.getLongitud(), entity.getDescription(), entity.getUrl(),
                entity.getEntityUser().getIdentification(),
                entity.getDateReport())).toList();
    }


}
