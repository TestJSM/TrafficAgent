package com.uco.TrafficAgent.application.service.user.comand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoSaveUser {
    private String identification;
    private String fullName;
    private String cellphone;
    private String password;

    private String email;
    private int type;
    private List<DtoRolUser> roles;
}
