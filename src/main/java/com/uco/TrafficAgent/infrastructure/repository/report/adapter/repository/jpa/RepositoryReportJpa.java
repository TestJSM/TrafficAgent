package com.uco.TrafficAgent.infrastructure.repository.report.adapter.repository.jpa;

import com.uco.TrafficAgent.infrastructure.repository.report.adapter.entity.EntityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepositoryReportJpa extends JpaRepository<EntityReport, Integer> {

    @Query(value = "SELECT * FROM report_users WHERE identification = ?1", nativeQuery = true)
    List<EntityReport> findByIdentification(String identification);

    @Query(value = "SELECT * FROM report_users WHERE date_report BETWEEN :start AND :end", nativeQuery = true)
    List<EntityReport> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
