package com.uco.TrafficAgent.domain.service.contact;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.domain.testdatabuilder.dto.DtoContactSummaryTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.ContactTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ServiceFindListContactByProximityTest {

    @Test
    void findContactSuccessful(){
        Contact contact = new ContactTestDataBuilder().byDefault().build();
        DtoContactSummary dtoUno = new DtoContactSummaryTestDataBuilder().byDefault()
                .withName(contact.getName()).withCellPhone(contact.getNumberPhone())
                .withDescription(contact.getDescription()).build();
        List<DtoContactSummary> list = new ArrayList<>();
        list.add(dtoUno);

        //Create
        RepositoryContact repositoryContact = Mockito.mock(RepositoryContact.class);
        when(repositoryContact.existContact(Mockito.any())).thenReturn(false);
        ServiceSaveContact serviceSaveContact = new ServiceSaveContact(repositoryContact);
        serviceSaveContact.executeSave(contact);

        //Find
        RepositoryContact repositoryContactFind = Mockito.mock(RepositoryContact.class);
        when(repositoryContactFind.listContactByProximity(Mockito.any(),
                eq(5.0), eq(80.0))).thenReturn(list);
        ServiceListsAllByProximityContact serviceListsAllByProximityContact = new ServiceListsAllByProximityContact(repositoryContactFind);
        List<DtoContactSummary> contactFind = serviceListsAllByProximityContact.executeList(contact.getUser().getIdentification(),
                5.0, 80.0);

        Assertions.assertEquals(contactFind.get(0).getDescription(), dtoUno.getDescription());
        Assertions.assertEquals(contactFind.get(0).getNumberPhone(), dtoUno.getNumberPhone());
        Assertions.assertEquals(contactFind.get(0).getName(), dtoUno.getName());
    }

    @Test
    void noFindContact(){
        Contact contact = new ContactTestDataBuilder().byDefault().build();
        List<DtoContactSummary> list = new ArrayList<>();

        //Create
        RepositoryContact repositoryContact = Mockito.mock(RepositoryContact.class);
        when(repositoryContact.existContact(Mockito.any())).thenReturn(false);
        ServiceSaveContact serviceSaveContact = new ServiceSaveContact(repositoryContact);
        serviceSaveContact.executeSave(contact);

        //Find
        RepositoryContact repositoryContactFind = Mockito.mock(RepositoryContact.class);
        when(repositoryContactFind.listContactByProximity(Mockito.any(),
                eq(5.0), eq(80.0))).thenReturn(list);
        ServiceListsAllByProximityContact serviceListsAllByProximityContact = new ServiceListsAllByProximityContact(repositoryContactFind);

        Assertions.assertEquals("No tienes contactos cercanos", Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> serviceListsAllByProximityContact.executeList(contact.getUser().getIdentification(),
                        5.0, 80.0)).getMessage());
    }
}
