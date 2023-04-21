package com.uco.TrafficAgent.application.service.user.comand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoSaveUser {
    private String identification;
    private String fullName;
    private String cellphone;
    private String password;
    private int type;
}
