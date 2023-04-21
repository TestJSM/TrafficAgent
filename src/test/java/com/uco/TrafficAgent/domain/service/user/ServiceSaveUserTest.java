package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

class ServiceSaveUserTest {

    @Test
    void createUserSuccessful(){
        User user = new UserTestDataBuilder().byDefault().build();

        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);

        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        Mockito.verify(repositoryUser, Mockito.times(1)).saveUser(user);
        Mockito.verify(repositoryUser, Mockito.times(1)).existUser(user.getIdentification());
    }

    @Test
    void noCreateUser(){
        User user = new UserTestDataBuilder().byDefault().build();

        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(true);

        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);

        Assertions.assertEquals("Ya existe un usuario con esos datos",
                Assertions.assertThrows(
                                IllegalArgumentException.class, ()-> serviceSaveUser.executeSave(user))
                        .getMessage());
    }
}
