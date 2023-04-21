package com.uco.TrafficAgent.domain.port.user;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.User;

import java.util.List;

public interface RepositoryUser {
    List<DtoUserSummary> listUser();
    void saveUser(User user);
    boolean existUser(String identification);
    void updateUser(User user);
    void deleteUser(String identification);
    User consultUserByIdentificationANdPassword(String id, String password);
    User consultUserByIdentification(String id);

}
