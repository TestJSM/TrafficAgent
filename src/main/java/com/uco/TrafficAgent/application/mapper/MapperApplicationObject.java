package com.uco.TrafficAgent.application.mapper;

public interface MapperApplicationObject<A, D>{

    D mapperDtoToDomain(A dto);
}
