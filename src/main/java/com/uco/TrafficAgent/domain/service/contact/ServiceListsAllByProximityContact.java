package com.uco.TrafficAgent.domain.service.contact;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;

import java.util.List;

public class ServiceListsAllByProximityContact {

    private final RepositoryContact repositoryContact;

    public ServiceListsAllByProximityContact(RepositoryContact repositoryContact) {
        this.repositoryContact = repositoryContact;
    }

    public List<DtoContactSummary> executeList(String id, double latitude, double longitude){
        List<DtoContactSummary> list = this.repositoryContact.listContactByProximity(id, latitude, longitude);
        if(list.isEmpty()){
            throw new IllegalArgumentException("No tienes contactos cercanos");
        }
        else{
            return list;
        }
    }
}
