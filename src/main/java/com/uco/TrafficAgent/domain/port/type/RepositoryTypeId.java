package com.uco.TrafficAgent.domain.port.type;

import com.uco.TrafficAgent.domain.model.TypeIdentification;

public interface RepositoryTypeId {

    public TypeIdentification getType(int id);
}
