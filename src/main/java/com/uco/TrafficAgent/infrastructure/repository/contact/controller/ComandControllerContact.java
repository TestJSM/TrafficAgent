package com.uco.TrafficAgent.infrastructure.repository.contact.controller;

import com.uco.TrafficAgent.application.service.contact.comand.ServiceApplicationSaveContact;
import com.uco.TrafficAgent.application.service.contact.comand.dto.DtoSaveContact;
import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.infrastructure.aspect.Secured;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ComandControllerContact {

    private final ServiceApplicationSaveContact serviceApplicationSaveContact;

    public ComandControllerContact(ServiceApplicationSaveContact serviceApplicationSaveContact) {
        this.serviceApplicationSaveContact = serviceApplicationSaveContact;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    @Secured(roles = {"Peaton", "Conductor"})
    public void saveContact(@RequestBody DtoSaveContact dto){
        this.serviceApplicationSaveContact.execute(dto);
    }

}
