package com.uco.TrafficAgent.application.service.contact.consult;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.service.contact.ServiceListsAllByProximityContact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceApplicationListAllByProximityContact {

    private final ServiceListsAllByProximityContact serviceListsAllByProximityContact;

    public ServiceApplicationListAllByProximityContact(ServiceListsAllByProximityContact serviceListsAllByProximityContact) {
        this.serviceListsAllByProximityContact = serviceListsAllByProximityContact;
    }

    public List<DtoContactSummary> execute(String id, double latitude, double longitude){
        return this.serviceListsAllByProximityContact.executeList(id, latitude, longitude);
    }
}
