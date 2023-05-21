package com.uco.TrafficAgent.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DtoCurrentUser {

    private String user;
    private List<String> roles;
}
