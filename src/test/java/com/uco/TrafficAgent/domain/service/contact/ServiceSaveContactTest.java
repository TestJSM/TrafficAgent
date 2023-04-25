package com.uco.TrafficAgent.domain.service.contact;

import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.domain.testdatabuilder.model.ContactTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

class ServiceSaveContactTest {

    @Test
    void createContactSuccessful(){
        Contact contact = new ContactTestDataBuilder().byDefault().build();

        RepositoryContact repositoryContact = Mockito.mock(RepositoryContact.class);
        when(repositoryContact.existContact(Mockito.any())).thenReturn(false);

        ServiceSaveContact serviceSaveContact = new ServiceSaveContact(repositoryContact);
        serviceSaveContact.executeSave(contact);

        Mockito.verify(repositoryContact, Mockito.times(1)).saveContact(contact);
        Mockito.verify(repositoryContact, Mockito.times(1)).existContact(contact.getNumberPhone());
    }

    @Test
    void noCreateContact(){
        Contact contact = new ContactTestDataBuilder().byDefault().build();

        RepositoryContact repositoryContact = Mockito.mock(RepositoryContact.class);
        when(repositoryContact.existContact(Mockito.any())).thenReturn(true);

        ServiceSaveContact serviceSaveContact = new ServiceSaveContact(repositoryContact);

        Assertions.assertEquals("Ya existe un contacto con este numero telefonico",
                Assertions.assertThrows(
                                IllegalArgumentException.class, ()-> serviceSaveContact.executeSave(contact))
                        .getMessage());
    }
}
