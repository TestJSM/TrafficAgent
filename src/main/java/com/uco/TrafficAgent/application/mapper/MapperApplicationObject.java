package com.uco.TrafficAgent.application.mapper;

public interface MapperApplicationObject<A, D>{

    D mapperUserToDomain(A dto);
}
