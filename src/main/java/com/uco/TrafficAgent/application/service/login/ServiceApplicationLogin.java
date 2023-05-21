package com.uco.TrafficAgent.application.service.login;

import com.uco.TrafficAgent.application.encrypted.ServiceEncryptedPassword;
import com.uco.TrafficAgent.application.service.DtoResponse;
import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;
import com.uco.TrafficAgent.domain.model.RolUser;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.service.token.ServiceEncryptText;
import com.uco.TrafficAgent.domain.service.token.ServiceGenerateToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceApplicationLogin {
    private final ServiceGenerateToken serviceGenerateToken;
    private final ServiceEncryptedPassword serviceEncryptText;
    private final RepositoryUser repositoryUser;

    public ServiceApplicationLogin(ServiceGenerateToken serviceGenerateToken, ServiceEncryptedPassword serviceEncryptText, RepositoryUser repositoryUser) {
        this.serviceGenerateToken = serviceGenerateToken;
        this.serviceEncryptText = serviceEncryptText;
        this.repositoryUser = repositoryUser;
    }

    public DtoResponse<String> execute(DtoLogin dto) {

        String encrypted = this.serviceEncryptText.execute(dto.getPassword());
        User user = this.repositoryUser.consultUserByIdentificationANdPassword(dto.getIdentification(), encrypted);

        if(user == null) {
            throw new IllegalStateException("Usuario o clave incorrecta");
        }

        List<String> roles = user.getRoles().stream().map(RolUser::getRol).collect(Collectors.toList());

        return new DtoResponse<>(this.serviceGenerateToken.execute(dto.getIdentification(), roles));
    }
}
