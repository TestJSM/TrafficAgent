package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository.jpa;

import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepositoryContactJpa extends JpaRepository<EntityContact, Integer> {
    EntityContact findByNumberPhone(String numberPhone);

    @Query(value = "SELECT * FROM contacts " +
            "WHERE identification = :id AND" +
            "(latitud - :valueLat > -20.0 AND latitud - :valueLat < 20.0) " +
            "AND (longitud - :valueLon > -20.0 AND longitud - :valueLon < 20.0)",
            nativeQuery = true)
    List<EntityContact> findContactsByLatLong(@Param("valueLat") double valueLat,
                                              @Param("valueLon") double valueLon,
                                              @Param("id") String id);
}
