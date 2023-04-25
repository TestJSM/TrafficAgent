package com.uco.TrafficAgent.application.service.contact.comand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoSaveContact {
    private double latitud;
    private double longitud;
    private String name;
    private String description;
    private String numberPhone;
    private String idUser;
}
