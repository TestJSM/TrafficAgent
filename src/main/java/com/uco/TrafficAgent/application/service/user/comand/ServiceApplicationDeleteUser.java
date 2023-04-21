package com.uco.TrafficAgent.application.service.user.comand;

import com.uco.TrafficAgent.domain.service.user.ServiceDeleteUser;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationDeleteUser {

    private final ServiceDeleteUser serviceDeleteUser;


    public ServiceApplicationDeleteUser(ServiceDeleteUser serviceDeleteUser) {
        this.serviceDeleteUser = serviceDeleteUser;
    }

    public void execute(String id){
        this.serviceDeleteUser.executeDelete(id);
    }
}
