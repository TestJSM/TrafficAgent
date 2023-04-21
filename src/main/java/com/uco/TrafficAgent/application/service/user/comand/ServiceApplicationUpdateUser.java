package com.uco.TrafficAgent.application.service.user.comand;

import com.uco.TrafficAgent.application.encrypted.ServiceEncryptedPassword;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoUpdateUser;
import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.port.type.RepositoryTypeId;
import com.uco.TrafficAgent.domain.service.user.ServiceUpdateUser;
import com.uco.TrafficAgent.domain.validator.ValidatorAttributes;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationUpdateUser {

    private final ServiceUpdateUser serviceUpdateUser;
    private final RepositoryTypeId repositoryTypeId;
    private final ServiceEncryptedPassword serviceEncryptedPassword;

    public ServiceApplicationUpdateUser(ServiceUpdateUser serviceUpdateUser, RepositoryTypeId repositoryTypeId, ServiceEncryptedPassword serviceEncryptedPassword) {
        this.serviceUpdateUser = serviceUpdateUser;
        this.repositoryTypeId = repositoryTypeId;
        this.serviceEncryptedPassword = serviceEncryptedPassword;
    }
    public void execute(String id, DtoUpdateUser dtoUpdateUser){
        ValidatorAttributes.sizePassword(dtoUpdateUser.getPassword(), "La contarseña no cumple con el tamaño requerido");
        ValidatorAttributes.specialCharactersPassword(dtoUpdateUser.getPassword(), "La contarseña no es permitida");
        dtoUpdateUser.setPassword(this.serviceEncryptedPassword.execute(dtoUpdateUser.getPassword()));

        TypeIdentification typeIdentification = this.repositoryTypeId.getType(dtoUpdateUser.getType());
        this.serviceUpdateUser.executeUpdate(id,
                dtoUpdateUser.getCellphone(), dtoUpdateUser.getFullName(), dtoUpdateUser.getPassword(),
                typeIdentification);
    }
}
