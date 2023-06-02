package com.uco.TrafficAgent.infrastructure.repository.report.controller;

import com.uco.TrafficAgent.application.service.report.comand.ServiceApplicationSaveReport;
import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoUpdateUser;
import com.uco.TrafficAgent.infrastructure.aspect.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/report")
public class ComandControllerReport {

    private final ServiceApplicationSaveReport serviceApplicationSaveReport;

    public ComandControllerReport(ServiceApplicationSaveReport serviceApplicationSaveReport) {
        this.serviceApplicationSaveReport = serviceApplicationSaveReport;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    @Secured(roles = {"Peaton", "Conductor"})
    public void saveReport(@RequestBody DtoSaveReport dtoSaveReport) throws MessagingException {
        this.serviceApplicationSaveReport.execute(dtoSaveReport);
    }

}
