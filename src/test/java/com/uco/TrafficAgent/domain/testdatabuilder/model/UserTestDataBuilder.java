package com.uco.TrafficAgent.domain.testdatabuilder.model;

import com.uco.TrafficAgent.domain.model.RolUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserTestDataBuilder {
    private String identification;
    private String fullName;
    private String cellphone;
    private String password;

    private String email;
    private TypeIdentification typeIdentification;
    private List<RolUser> roles;


    public UserTestDataBuilder byDefault() {
        RolUser rol = new RolUserTestDataBuilder().byDefault().build();
        List<RolUser> roles = Collections.singletonList(rol);

        this.identification = "1234567890";
        this.fullName = "Nombre";
        this.cellphone = "123456789";
        this.password = "w6Unpo<code>t0d0";
        this.email = "algo@email.com";
        this.typeIdentification = new TypeIdentificationTestDataBuilder().byDefault().build();
        this.roles = roles;
        return this;
    }

    public UserTestDataBuilder withIdentification(String identification){
        this.identification = identification;
        return this;
    }

    public UserTestDataBuilder withName(String fullName){
        this.fullName = fullName;
        return this;
    }

    public UserTestDataBuilder withCellPhone(String cellphone){
        this.cellphone = cellphone;
        return this;
    }

    public UserTestDataBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UserTestDataBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserTestDataBuilder withTypeIdentification (TypeIdentification typeIdentification){
        this.typeIdentification = typeIdentification;
        return this;
    }

    public User build() {
        return User.createUser(identification, fullName, cellphone, password, email, typeIdentification, roles);
    }
}
