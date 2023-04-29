package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contacts_emergency")
public class EntityContactEmergency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private double latitud;
    private double longitud;
    @Column(name = "name_contact")
    private String name;
    private String description;
    @Column(nullable = true)
    private double distancia;
    @Column(name = "number_phone", unique = true)
    private String numberPhone;
}
