package com.uco.TrafficAgent.infrastructure.repository.user.controller;

import com.uco.TrafficAgent.application.service.user.consult.ServiceApplicationListAllUser;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ConsultControllerUser {

    private final ServiceApplicationListAllUser serviceApplicationListAllUser;

    public ConsultControllerUser(ServiceApplicationListAllUser serviceApplicationListAllUser) {
        this.serviceApplicationListAllUser = serviceApplicationListAllUser;
    }


    @GetMapping("/list/all")
    public List<DtoUserSummary> listUser(){
        return this.serviceApplicationListAllUser.execute();
    }

}
