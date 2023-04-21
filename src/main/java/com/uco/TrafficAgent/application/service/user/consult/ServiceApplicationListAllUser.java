package com.uco.TrafficAgent.application.service.user.consult;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.service.user.ServiceListAllUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceApplicationListAllUser {

    private final ServiceListAllUser serviceListAllUser;

    public ServiceApplicationListAllUser(ServiceListAllUser serviceListAllUser) {
        this.serviceListAllUser = serviceListAllUser;
    }

    public List<DtoUserSummary> execute(){
        return this.serviceListAllUser.executeListAll();
    }
}
