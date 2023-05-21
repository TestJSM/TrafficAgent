package com.uco.TrafficAgent.domain.service.token;

import java.util.List;

public interface ServiceGenerateToken {
    String execute(String user, List<String> roles);

}
