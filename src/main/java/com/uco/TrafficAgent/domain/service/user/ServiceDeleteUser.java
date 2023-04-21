package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.port.user.RepositoryUser;

public class ServiceDeleteUser {

    private final RepositoryUser repositoryUser;

    public ServiceDeleteUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public void executeDelete(String id){
        if(!this.repositoryUser.existUser(id)){
            throw new IllegalArgumentException("No existe este usuario para eliminar");

        }else {
            this.repositoryUser.deleteUser(id);
        }
    }

}
