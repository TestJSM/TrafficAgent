package com.uco.TrafficAgent.domain.dto;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoUserSummary {

    private String identification;
    private String fullName;
    private String cellphone;
    private TypeIdentification typeIdentification;
}
