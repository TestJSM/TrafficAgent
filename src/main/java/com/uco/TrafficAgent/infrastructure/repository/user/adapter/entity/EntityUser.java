package com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity;

import com.uco.TrafficAgent.infrastructure.repository.rol.adapter.entity.EntityRol;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "type_identification")
    private EntityTypeId entityTypeId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "id_rol_user")
    private List<EntityRol> roles;

    public EntityUser() {
    }

    public EntityUser(String identification, String fullName, String cellphone, String password, String email, EntityTypeId entityTypeId, List<EntityRol> roles) {
        this.identification = identification;
        this.fullName = fullName;
        this.cellphone = cellphone;
        this.password = password;
        this.email = email;
        this.entityTypeId = entityTypeId;
        this.roles = roles;
    }
}
