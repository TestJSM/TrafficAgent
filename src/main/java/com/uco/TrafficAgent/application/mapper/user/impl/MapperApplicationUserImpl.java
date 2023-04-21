package com.uco.TrafficAgent.application.mapper.user.impl;

import com.uco.TrafficAgent.application.mapper.MapperApplicationObject;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.type.RepositoryTypeId;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperApplicationUserImpl implements MapperApplicationObject<DtoSaveUser, User> {

    @Autowired
    private RepositoryTypeId repositoryTypeId;

    @Override
    public User mapperUserToDomain(DtoSaveUser dto) {
        return User.createUser(dto.getIdentification(), dto.getFullName(), dto.getCellphone(),
                dto.getPassword(),
                this.repositoryTypeId.getType(dto.getType()));
    }

}
