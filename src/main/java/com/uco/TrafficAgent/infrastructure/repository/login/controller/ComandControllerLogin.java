package com.uco.TrafficAgent.infrastructure.repository.login.controller;

import com.uco.TrafficAgent.application.service.DtoResponse;
import com.uco.TrafficAgent.application.service.login.ServiceApplicationLogin;
import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;
import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.infrastructure.aspect.LogExecutionTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ComandControllerLogin {

    private final ServiceApplicationLogin serviceApplicationLogin;

    public ComandControllerLogin(ServiceApplicationLogin serviceApplicationLogin) {
        this.serviceApplicationLogin = serviceApplicationLogin;
    }


    @PostMapping("/login")
    @LogExecutionTime
    public DtoResponse<String> login(@RequestBody DtoLogin dto) {
        return this.serviceApplicationLogin.execute(dto);
    }

}
