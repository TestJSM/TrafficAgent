package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class ServiceDeleteUserTest {
    
    @Test
    void deleteUserSuccessful(){
        User user = new UserTestDataBuilder().byDefault().build();

        //Create
        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);

        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        //Delete
        RepositoryUser repositoryUserDelete = Mockito.mock(RepositoryUser.class);
        when(repositoryUserDelete.existUser(Mockito.any())).thenReturn(true);

        ServiceDeleteUser serviceDeleteUser = new ServiceDeleteUser(repositoryUserDelete);

        serviceDeleteUser.executeDelete(user.getIdentification());

        Mockito.verify(repositoryUserDelete, Mockito.times(1)).deleteUser(user.getIdentification());
    }

    @Test
    void deleteUserNoSuccessful(){
        User user = new UserTestDataBuilder().byDefault().build();

        //Create
        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);

        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        //Delete
        RepositoryUser repositoryUserDelete = Mockito.mock(RepositoryUser.class);
        when(repositoryUserDelete.existUser(Mockito.any())).thenReturn(false);

        ServiceDeleteUser serviceDeleteUser = new ServiceDeleteUser(repositoryUserDelete);
        Assertions.assertEquals("No existe este usuario para eliminar", Assertions.assertThrows(
                        IllegalArgumentException.class,
                        ()-> serviceDeleteUser.executeDelete(user.getIdentification()))
                .getMessage());
    }

}
