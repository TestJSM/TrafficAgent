package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;

import java.util.List;

public class ServiceListAllUser {

    private final RepositoryUser repositoryUser;

    public ServiceListAllUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public List<DtoUserSummary> executeListAll(){
        List<DtoUserSummary> users = this.repositoryUser.listUser();
        if(users.isEmpty()){
            throw new IllegalStateException("No hay usuarios");
        }else {
            return users;
        }
    }

}
