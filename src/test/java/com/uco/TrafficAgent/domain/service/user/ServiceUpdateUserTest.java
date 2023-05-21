package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.testdatabuilder.model.TypeIdentificationTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

class ServiceUpdateUserTest {
    
    @Test
    void updateUserSuccessful(){
        TypeIdentification typeIdentification = new TypeIdentificationTestDataBuilder().byDefault().withType("NIT").build();

        User user = new UserTestDataBuilder().byDefault().build();
        User userUpdate = new UserTestDataBuilder().byDefault().withCellPhone("0987654321")
                .withPassword("w10Inpo<code>t0d0").withTypeIdentification(typeIdentification).build();

        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);

        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        RepositoryUser repositoryUserUpdate = Mockito.mock(RepositoryUser.class);
        when(repositoryUserUpdate.existUser(Mockito.any())).thenReturn(true);
        when(repositoryUserUpdate.consultUserByIdentification(user.getIdentification())).thenReturn(user);

        ServiceUpdateUser serviceUpdateUser = new ServiceUpdateUser(repositoryUserUpdate);

        serviceUpdateUser
                .executeUpdate(user.getIdentification(), userUpdate.getCellphone(), userUpdate.getFullName(),
                userUpdate.getPassword(), userUpdate.getEmail(),userUpdate.getTypeIdentification(),
                        userUpdate.getRoles());

        Mockito.verify(repositoryUserUpdate, Mockito.times(1)).updateUser(refEq(userUpdate));
    }

    @Test
    void updateUserNoSuccessful(){
        TypeIdentification typeIdentification = new TypeIdentificationTestDataBuilder().byDefault().withType("NIT").build();

        User user = new UserTestDataBuilder().byDefault().build();
        User userUpdate = new UserTestDataBuilder().byDefault().withCellPhone("0987654321")
                .withPassword("w10Inpo<code>t0d0").withTypeIdentification(typeIdentification).build();

        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);

        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        ServiceUpdateUser serviceUpdateUser = new ServiceUpdateUser(repositoryUser);

        Assertions.assertEquals("No hay ningún usuario con esa identificación", Assertions.assertThrows(
                        IllegalArgumentException.class,
                        ()-> serviceUpdateUser.executeUpdate(user.getIdentification(), userUpdate.getCellphone(), userUpdate.getFullName(),
                                userUpdate.getPassword(), userUpdate.getEmail(),userUpdate.getTypeIdentification(),
                                userUpdate.getRoles()))
                .getMessage());
    }

}
