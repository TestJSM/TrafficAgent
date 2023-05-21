package com.uco.TrafficAgent.infrastructure.repository.user.adapter.repository;

import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.infrastructure.mapper.user.impl.MapperUserImpl;
import com.uco.TrafficAgent.infrastructure.repository.rol.adapter.entity.EntityRol;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.entity.EntityTypeId;
import com.uco.TrafficAgent.infrastructure.repository.type.adapter.repository.jpa.RepositoryTypeIdJpa;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.repository.jpa.RepositoryUserJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryUserDB implements RepositoryUser {
    private final RepositoryUserJpa repositoryUserJpa;
    private final RepositoryTypeIdJpa repositoryTypeIdJpa;
    private final MapperUserImpl mapperUser;

    public RepositoryUserDB(RepositoryUserJpa repositoryUserJpa, RepositoryTypeIdJpa repositoryTypeIdJpa, MapperUserImpl mapperUser) {
        this.repositoryUserJpa = repositoryUserJpa;
        this.repositoryTypeIdJpa = repositoryTypeIdJpa;
        this.mapperUser = mapperUser;
    }

    @Override
    public List<DtoUserSummary> listUser() {
        List<EntityUser> entities = this.repositoryUserJpa.findAll();
        return entities.stream().map(entity -> new DtoUserSummary(entity.getIdentification(),
                entity.getFullName(), entity.getCellphone(),
                TypeIdentification.creationType(entity.getEntityTypeId().getType()))).collect(Collectors.toList());
    }

    @Override
    public void saveUser(User user) {
        EntityTypeId entityTypeId = this.repositoryTypeIdJpa.findByType(user.getTypeIdentification().getType());
        EntityUser entityUser = new EntityUser(user.getIdentification(), user.getFullName(),
                user.getCellphone(), user.getPassword(), user.getEmail(), entityTypeId,
                user.getRoles().stream().map(rol -> new EntityRol(rol.getRol())).collect(Collectors.toList()));

        this.repositoryUserJpa.save(entityUser);
    }

    @Override
    public boolean existUser(String id) {
        return this.repositoryUserJpa.findByIdentification(id) != null;
    }

    @Override
    public void updateUser(User user) {
        EntityTypeId entityTypeId = this.repositoryTypeIdJpa.findByType(user.getTypeIdentification().getType());


        EntityUser entityUser = new EntityUser();
        entityUser.setIdentification(user.getIdentification());
        entityUser.setFullName(user.getFullName());
        entityUser.setCellphone(user.getCellphone());
        entityUser.setPassword(user.getPassword());
        entityUser.setEmail(user.getEmail());
        entityUser.setEntityTypeId(entityTypeId);
        entityUser.setRoles(user.getRoles().stream().map(rol -> new EntityRol(rol.getRol())).toList());

        this.repositoryUserJpa.save(entityUser);
    }

    @Override
    public void deleteUser(String identification) {
        this.repositoryUserJpa.deleteById(identification);
    }

    @Override
    public User consultUserByIdentificationANdPassword(String id, String password) {
        EntityUser entityUser = this.repositoryUserJpa.findByIdentificationAndPassword(id, password);

        if(entityUser == null){
            return null;
        }

        return this.mapperUser.mapperEntityToDomain(entityUser);
    }

    @Override
    public User consultUserByIdentification(String id) {
        EntityUser entityUser = this.repositoryUserJpa.findByIdentification(id);

        if(entityUser == null){
            return null;
        }

        return this.mapperUser.mapperEntityToDomain(entityUser);
    }
}
