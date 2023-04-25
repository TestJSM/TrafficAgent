package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity;

import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class EntityContact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private double latitud;
    private double longitud;
    private String name;
    private String description;
    @Column(name = "number_phone", unique = true)
    private String numberPhone;

    @ManyToOne
    @JoinColumn(name = "identification")
    private EntityUser entityUser;

    public EntityContact() {
    }

    public EntityContact(double latitud, double longitud, String name, String description, String numberPhone, EntityUser entityUser) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.name = name;
        this.description = description;
        this.numberPhone = numberPhone;
        this.entityUser = entityUser;
    }
}
