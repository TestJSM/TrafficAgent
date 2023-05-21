package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.model.RolUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;

import java.util.List;

public class ServiceUpdateUser {

    private final RepositoryUser repositoryUser;

    public ServiceUpdateUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public void executeUpdate(String id, String cellphone, String fullName, String password, String email,
                              TypeIdentification typeIdentification, List<RolUser> roles){
        User user = this.repositoryUser.consultUserByIdentification(id);
        if(user != null){
            this.repositoryUser.updateUser(User.createUser(user.getIdentification(),
                    fullName, cellphone, password, email,typeIdentification, roles));
        }
        else{
            throw new IllegalArgumentException("No hay ningún usuario con esa identificación");
        }
    }
}
