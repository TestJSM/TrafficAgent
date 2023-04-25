package com.uco.TrafficAgent.infrastructure.repository.contact.controller;

import com.uco.TrafficAgent.application.service.contact.consult.ServiceApplicationListAllByProximityContact;
import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ConsultControllerContact {

    private final ServiceApplicationListAllByProximityContact serviceApplicationListAllByProximityContact;

    public ConsultControllerContact(ServiceApplicationListAllByProximityContact serviceApplicationListAllByProximityContact) {
        this.serviceApplicationListAllByProximityContact = serviceApplicationListAllByProximityContact;
    }


    @GetMapping("/list/all/{id}")
    public List<DtoContactSummary> listUser(@PathVariable String id,
            @RequestParam("latitud") double latitud,
            @RequestParam("longitud") double longitud){
        return this.serviceApplicationListAllByProximityContact.execute(id, latitud, longitud);
    }

}
