package com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "type_id")
public class EntityTypeId {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true)
    private String type;

    public EntityTypeId() {
    }

    public EntityTypeId(String type) {
        this.type = type;
    }
}
