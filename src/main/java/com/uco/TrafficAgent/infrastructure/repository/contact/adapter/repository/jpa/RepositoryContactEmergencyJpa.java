package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository.jpa;

import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContactEmergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositoryContactEmergencyJpa extends JpaRepository<EntityContactEmergency, Integer> {
}
