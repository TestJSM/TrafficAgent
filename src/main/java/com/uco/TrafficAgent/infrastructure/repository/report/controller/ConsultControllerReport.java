package com.uco.TrafficAgent.infrastructure.repository.report.controller;

import com.uco.TrafficAgent.application.service.report.consult.ServiceApplicationListAllReportByUser;
import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ConsultControllerReport {

    private final ServiceApplicationListAllReportByUser serviceApplicationListAllReportByUser;

    public ConsultControllerReport(ServiceApplicationListAllReportByUser serviceApplicationListAllReportByUser) {
        this.serviceApplicationListAllReportByUser = serviceApplicationListAllReportByUser;
    }


    @GetMapping("/list/all/{id}")
    public List<DtoReportSummary> listUser(@PathVariable String id){
        return this.serviceApplicationListAllReportByUser.execute(id);
    }

}
