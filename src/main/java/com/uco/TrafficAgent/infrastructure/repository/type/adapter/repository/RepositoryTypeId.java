package com.uco.TrafficAgent.infrastructure.repository.type.adapter.repository;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.repository.jpa.RepositoryTypeIdJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RepositoryTypeId implements com.uco.TrafficAgent.domain.port.type.RepositoryTypeId {

    private final RepositoryTypeIdJpa repositoryTypeIdJpa;

    public RepositoryTypeId(RepositoryTypeIdJpa repositoryTypeIdJpa) {
        this.repositoryTypeIdJpa = repositoryTypeIdJpa;
    }

    @Override
    public TypeIdentification getType(int id) {
        EntityTypeId entityTypeId = this.repositoryTypeIdJpa.findById(id).orElse(null);

        if(entityTypeId == null){
            return null;
        }
        return TypeIdentification.creationType(entityTypeId.getType());
    }
}
