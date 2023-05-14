package com.uco.TrafficAgent.infrastructure.repository.report.adapter.entity;

import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "report_users")
public class EntityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private double latitud;
    private double longitud;
    private String description;
    private String url;
    @Column(name = "date_report")
    private LocalDateTime dateReport;

    @ManyToOne
    @JoinColumn(name = "identification")
    private EntityUser entityUser;

    public EntityReport() {
    }

    public EntityReport(double latitud, double longitud, String description, String url, LocalDateTime dateReport,EntityUser entityUser) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.description = description;
        this.url = url;
        this.dateReport = dateReport;
        this.entityUser = entityUser;
    }
}
