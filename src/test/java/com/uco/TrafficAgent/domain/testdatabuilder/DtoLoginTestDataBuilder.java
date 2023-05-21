package com.uco.TrafficAgent.domain.testdatabuilder;

import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;

public class DtoLoginTestDataBuilder {
    private String id;
    private String password;

    public DtoLoginTestDataBuilder byDefault(){
        this.id = "123456789";
        this.password = "w6Unpo<code>t0d0";
        return this;
    }

    public DtoLogin build() {
        return new DtoLogin(id, password);
    }
}
