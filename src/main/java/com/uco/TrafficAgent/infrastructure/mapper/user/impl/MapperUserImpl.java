package com.uco.TrafficAgent.infrastructure.mapper.user.impl;

import com.uco.TrafficAgent.domain.model.RolUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.infrastructure.mapper.MapperInfrastructureObject;
import com.uco.TrafficAgent.infrastructure.repository.rol.adapter.entity.EntityRol;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class MapperUserImpl implements MapperInfrastructureObject<EntityUser, User> {

    @Override
    public User mapperEntityToDomain(EntityUser entity) {
        return User.createUser(entity.getIdentification(), entity.getFullName(),
                entity.getCellphone(), entity.getPassword(), entity.getEmail(),
                TypeIdentification.creationType(entity.getEntityTypeId().getType()),
                entity.getRoles().stream().map(rol -> RolUser.of(rol.getRol())).collect(Collectors.toList()));
    }

    @Override
    public EntityUser mapperDomainToEntity(User domain) {
        return new EntityUser(domain.getIdentification(), domain.getFullName(), domain.getCellphone(),
                domain.getPassword(), domain.getEmail(),
                new EntityTypeId(domain.getTypeIdentification().getType()),
                domain.getRoles().stream().map(rol -> new EntityRol(rol.getRol())).collect(Collectors.toList()));
    }
}
