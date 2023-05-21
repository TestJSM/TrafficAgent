package com.uco.TrafficAgent.infrastructure.repository.report.controller;

import com.uco.TrafficAgent.application.service.report.consult.ServiceApplicationListAllReportByDate;
import com.uco.TrafficAgent.application.service.report.consult.ServiceApplicationListAllReportByUser;
import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.infrastructure.aspect.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ConsultControllerReport {

    private final ServiceApplicationListAllReportByUser serviceApplicationListAllReportByUser;
    private final ServiceApplicationListAllReportByDate serviceApplicationListAllReportByDate;

    public ConsultControllerReport(ServiceApplicationListAllReportByUser serviceApplicationListAllReportByUser, ServiceApplicationListAllReportByDate serviceApplicationListAllReportByDate) {
        this.serviceApplicationListAllReportByUser = serviceApplicationListAllReportByUser;
        this.serviceApplicationListAllReportByDate = serviceApplicationListAllReportByDate;
    }

    @GetMapping("/list/all/{id}")
    @Secured(roles = {"Peaton", "Conductor"})
    public List<DtoReportSummary> listUser(@PathVariable String id){
        return this.serviceApplicationListAllReportByUser.execute(id);
    }

    @GetMapping("/list/hour/{date}")
    @Secured(roles = {"Peaton", "Conductor"})
    public List<DtoReportSummary> listByDate(@PathVariable("date") String date){
        LocalDateTime time = LocalDateTime.parse(date);
        return this.serviceApplicationListAllReportByDate.execute(time);
    }

}
