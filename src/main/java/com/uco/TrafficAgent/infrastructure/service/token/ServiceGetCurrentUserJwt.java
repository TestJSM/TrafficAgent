package com.uco.TrafficAgent.infrastructure.service.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.uco.TrafficAgent.domain.dto.DtoCurrentUser;
import com.uco.TrafficAgent.domain.service.token.ServiceGetCurrentUser;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class ServiceGetCurrentUserJwt implements ServiceGetCurrentUser {

    private String obtenerTokenActual() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    @Override
    public DtoCurrentUser execute() {
        DecodedJWT decoded = JWT.decode(obtenerTokenActual());
        return new DtoCurrentUser(decoded.getSubject(), decoded.getClaim("roles").asList(String.class));

    }
}
