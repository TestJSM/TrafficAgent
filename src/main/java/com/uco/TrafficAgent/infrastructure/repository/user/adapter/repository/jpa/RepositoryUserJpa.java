package com.uco.TrafficAgent.infrastructure.repository.user.adapter.repository.jpa;

import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUserJpa extends JpaRepository<EntityUser, String> {

    EntityUser findByIdentificationAndPassword(String identification, String password);
    EntityUser findByIdentification(String identification);
}
