package com.uco.TrafficAgent.infrastructure.mapper;

public interface MapperInfrastructureObject<E, D>{

    D mapperUserToDomain(E entity);
    E mapperUserToEntity(D domain);
}
