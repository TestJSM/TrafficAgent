package com.uco.TrafficAgent.domain.service.contact;

import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;

public class ServiceSaveContact {

    private final RepositoryContact repositoryContact;

    public ServiceSaveContact(RepositoryContact repositoryContact) {
        this.repositoryContact = repositoryContact;
    }

    public void executeSave(Contact contact){
        if(this.repositoryContact.existContact(contact.getNumberPhone())){
            throw new IllegalArgumentException("Ya existe un contacto con este numero telefonico");
        }else{
            this.repositoryContact.saveContact(contact);
        }

    }
}
