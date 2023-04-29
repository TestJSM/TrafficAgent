package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository.jpa;

import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContact;
import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContactEmergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryContactEmergencyJpa extends JpaRepository<EntityContactEmergency, Integer> {

    @Query(value = "SELECT c.id, c.description, c.latitud, c.longitud, c.name_contact, c.number_phone, " +
            "6371 * 2 * ASIN(SQRT(POWER(SIN((c.latitud - (:valueLat)) * PI()/180 / 2), 2) + " +
            "COS(c.latitud * PI()/180) * COS((:valueLat) * PI()/180) * " +
            "POWER(SIN((c.longitud - (:valueLon)) * PI()/180 / 2), 2))) AS distancia " +
            "FROM contacts_emergency c " +
            "order by distancia asc",
            nativeQuery = true)
    List<EntityContactEmergency> findContactsByLatLong(@Param("valueLat") double valueLat,
                                              @Param("valueLon") double valueLon);
}
