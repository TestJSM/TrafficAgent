package com.uco.TrafficAgent.infrastructure.repository.type.adapter.repository.jpa;

import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTypeIdJpa extends JpaRepository<EntityTypeId, Integer> {

    EntityTypeId findByType(String type);
}
