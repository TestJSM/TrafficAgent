package com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity;

import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class EntityUser {
    @Id
    @Column(length = 20, nullable = false)
    private String identification;
    @Column(length = 70, name = "full_name")
    private String fullName;
    @Column(length = 50)
    private String cellphone;
    @Column(length = 255)
    private String password;
    @ManyToOne
    @JoinColumn(name = "type_identification")
    private EntityTypeId entityTypeId;

    public EntityUser() {
    }

    public EntityUser(String identification, String fullName, String cellphone, String password, EntityTypeId entityTypeId) {
        this.identification = identification;
        this.fullName = fullName;
        this.cellphone = cellphone;
        this.password = password;
        this.entityTypeId = entityTypeId;
    }
}
