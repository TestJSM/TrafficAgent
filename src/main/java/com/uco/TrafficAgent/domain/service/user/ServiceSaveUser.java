package com.uco.TrafficAgent.domain.service.user;


import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;

public class ServiceSaveUser {
    private final RepositoryUser repositoryUser;

    public ServiceSaveUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public void executeSave(User user){
        if(!this.repositoryUser.existUser(user.getIdentification())){
            this.repositoryUser.saveUser(user);
        }else{
            throw new IllegalArgumentException("Ya existe un usuario con esos datos");
        }
    }
}
