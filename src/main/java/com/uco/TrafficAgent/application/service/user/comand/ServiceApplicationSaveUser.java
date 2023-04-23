package com.uco.TrafficAgent.application.service.user.comand;

import com.uco.TrafficAgent.application.encrypted.ServiceEncryptedPassword;
import com.uco.TrafficAgent.application.mapper.user.impl.MapperApplicationUserImpl;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.domain.service.user.ServiceSaveUser;
import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationSaveUser {

    private final ServiceSaveUser serviceSaveUser;
    private final MapperApplicationUserImpl mapperApplicationUser;
    private final ServiceEncryptedPassword serviceEncryptedPassword;


    public ServiceApplicationSaveUser(ServiceSaveUser serviceSaveUser, MapperApplicationUserImpl mapperApplicationUser, ServiceEncryptedPassword serviceEncryptedPassword) {
        this.serviceSaveUser = serviceSaveUser;
        this.mapperApplicationUser = mapperApplicationUser;
        this.serviceEncryptedPassword = serviceEncryptedPassword;
    }

    public void execute(DtoSaveUser dto){
        ValidatorAttributes.sizePassword(dto.getPassword(), "La contarseña no cumple con el tamaño requerido");
        ValidatorAttributes.specialCharactersPassword(dto.getPassword(), "La contarseña no es permitida");
        dto.setPassword(this.serviceEncryptedPassword.execute(dto.getPassword()));
        this.serviceSaveUser.executeSave(this.mapperApplicationUser.mapperDtoToDomain(dto));
    }
}
