package com.uco.TrafficAgent.domain.service.user;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.testdatabuilder.dto.DtoUserSummaryTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class ServiceFindListUserTest {

    @Test
    void findUserSuccessful(){
        User user = new UserTestDataBuilder().byDefault().build();
        DtoUserSummary dtoUno = new DtoUserSummaryTestDataBuilder().byDefault()
                .withIdentification(user.getIdentification()).withName(user.getFullName())
                .withCellPhone(user.getCellphone()).withTypeIdentification(user.getTypeIdentification()).build();
        List<DtoUserSummary> list = new ArrayList<>();
        list.add(dtoUno);

        //Create
        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);
        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        //Find
        RepositoryUser repositoryUserFind = Mockito.mock(RepositoryUser.class);
        when(repositoryUserFind.listUser()).thenReturn(list);
        ServiceListAllUser serviceListAllUser = new ServiceListAllUser(repositoryUserFind);
        List<DtoUserSummary> userFind = serviceListAllUser.executeListAll();

        Assertions.assertEquals(userFind.get(0).getIdentification(), dtoUno.getIdentification());
        Assertions.assertEquals(userFind.get(0).getCellphone(), dtoUno.getCellphone());
        Assertions.assertEquals(userFind.get(0).getFullName(), dtoUno.getFullName());
        Assertions.assertEquals(userFind.get(0).getTypeIdentification().getType(), dtoUno.getTypeIdentification().getType());
    }

    @Test
    void noFindUser(){
        User user = new UserTestDataBuilder().byDefault().build();
        List<DtoUserSummary> list = new ArrayList<>();

        //Create
        RepositoryUser repositoryUser = Mockito.mock(RepositoryUser.class);
        when(repositoryUser.existUser(Mockito.any())).thenReturn(false);
        ServiceSaveUser serviceSaveUser = new ServiceSaveUser(repositoryUser);
        serviceSaveUser.executeSave(user);

        //Find
        RepositoryUser repositoryUserFind = Mockito.mock(RepositoryUser.class);
        when(repositoryUserFind.listUser()).thenReturn(list);
        ServiceListAllUser serviceListAllUser = new ServiceListAllUser(repositoryUserFind);

        Assertions.assertEquals("No hay usuarios", Assertions.assertThrows(
                IllegalStateException.class,
                serviceListAllUser::executeListAll).getMessage());
    }
}
