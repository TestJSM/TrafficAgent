package com.uco.TrafficAgent.application.service.contact.comand;

import com.uco.TrafficAgent.application.mapper.contact.impl.MapperApplicationContactImpl;
import com.uco.TrafficAgent.application.service.contact.comand.dto.DtoSaveContact;
import com.uco.TrafficAgent.domain.service.contact.ServiceSaveContact;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationSaveContact {

    private final ServiceSaveContact serviceSaveContact;
    private final MapperApplicationContactImpl mapperApplicationContact;

    public ServiceApplicationSaveContact(ServiceSaveContact serviceSaveContact, MapperApplicationContactImpl mapperApplicationContact) {
        this.serviceSaveContact = serviceSaveContact;
        this.mapperApplicationContact = mapperApplicationContact;
    }

    public void execute(DtoSaveContact dtoSaveContact){
        this.serviceSaveContact.executeSave(this.mapperApplicationContact.mapperDtoToDomain(dtoSaveContact));
    }
}
