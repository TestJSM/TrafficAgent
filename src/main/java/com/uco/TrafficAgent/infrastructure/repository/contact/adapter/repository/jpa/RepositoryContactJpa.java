package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository.jpa;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface RepositoryContactJpa extends JpaRepository<EntityContact, Integer> {
    EntityContact findByNumberPhone(String numberPhone);

    @Query(value = "SELECT c.id, c.description, c.latitud, c.longitud, c.name_contact, c.number_phone, c.identification, " +
            "6371 * 2 * ASIN(SQRT(POWER(SIN((c.latitud - (:valueLat)) * PI()/180 / 2), 2) + " +
            "COS(c.latitud * PI()/180) * COS((:valueLat) * PI()/180) * " +
            "POWER(SIN((c.longitud - (:valueLon)) * PI()/180 / 2), 2))) AS distancia " +
            "FROM contacts c WHERE c.identification = :id " +
            "order by distancia asc",
            nativeQuery = true)
    List<EntityContact> findContactsByLatLong(@Param("valueLat") double valueLat,
                                              @Param("valueLon") double valueLon,
                                              @Param("id") String id);

}
