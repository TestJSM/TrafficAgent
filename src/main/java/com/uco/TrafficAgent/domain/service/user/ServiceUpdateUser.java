package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;

public class ServiceUpdateUser {

    private final RepositoryUser repositoryUser;

    public ServiceUpdateUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public void executeUpdate(String id, String cellphone, String fullName, String password,
                              TypeIdentification typeIdentification){
        User user = this.repositoryUser.consultUserByIdentification(id);
        if(user != null){
            this.repositoryUser.updateUser(User.createUser(user.getIdentification(),
                    fullName, cellphone, password, typeIdentification));
        }
        else{
            throw new IllegalArgumentException("No hay ningún usuario con esa identificación");
        }
    }
}
