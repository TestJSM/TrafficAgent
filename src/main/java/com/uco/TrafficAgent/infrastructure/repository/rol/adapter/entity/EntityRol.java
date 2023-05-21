package com.uco.TrafficAgent.infrastructure.repository.rol.adapter.entity;

import javax.persistence.*;

@Entity
@Table(name= "rol_users")
public class EntityRol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String rol;

    public EntityRol(String rol) {
        this.rol = rol;
    }

    public EntityRol() {
    }

    public String getRol() {
        return rol;
    }

    public Long getId() {
        return id;
    }
}
