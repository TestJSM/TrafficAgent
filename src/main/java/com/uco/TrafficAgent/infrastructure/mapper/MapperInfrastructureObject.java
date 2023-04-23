package com.uco.TrafficAgent.infrastructure.mapper;

public interface MapperInfrastructureObject<E, D>{

    D mapperEntityToDomain(E entity);
    E mapperDomainToEntity(D domain);
}
