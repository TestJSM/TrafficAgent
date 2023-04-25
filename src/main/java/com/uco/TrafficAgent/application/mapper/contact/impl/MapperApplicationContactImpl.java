package com.uco.TrafficAgent.application.mapper.contact.impl;

import com.uco.TrafficAgent.application.mapper.MapperApplicationObject;
import com.uco.TrafficAgent.application.service.contact.comand.dto.DtoSaveContact;
import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperApplicationContactImpl implements MapperApplicationObject<DtoSaveContact, Contact> {
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public Contact mapperDtoToDomain(DtoSaveContact dto) {
        return Contact.createContact(dto.getLatitud(), dto.getLongitud(), dto.getName(), dto.getDescription(),
                dto.getNumberPhone(), this.repositoryUser.consultUserByIdentification(dto.getIdUser()));
    }
}
