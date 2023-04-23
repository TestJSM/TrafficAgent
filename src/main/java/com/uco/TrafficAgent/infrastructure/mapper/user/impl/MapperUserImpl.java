package com.uco.TrafficAgent.infrastructure.mapper.user.impl;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.infrastructure.mapper.MapperInfrastructureObject;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperUserImpl implements MapperInfrastructureObject<EntityUser, User> {

    @Override
    public User mapperEntityToDomain(EntityUser entity) {
        return User.createUser(entity.getIdentification(), entity.getFullName(),
                entity.getCellphone(), entity.getPassword(),
                TypeIdentification.creationType(entity.getEntityTypeId().getType()));
    }

    @Override
    public EntityUser mapperDomainToEntity(User domain) {
        return new EntityUser(domain.getIdentification(), domain.getFullName(), domain.getCellphone(),
                domain.getPassword(), new EntityTypeId(domain.getTypeIdentification().getType()));
    }
}
