package com.uco.TrafficAgent.infrastructure.service.token;

import com.uco.TrafficAgent.domain.service.token.ServiceGenerateToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ServiceGenerateTokenJWT implements ServiceGenerateToken {

    private final Environment environment;

    public ServiceGenerateTokenJWT(Environment environment) {
        this.environment = environment;
    }


    private static Date createDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String execute(String user, List<String> roles) {
        return Jwts.builder()
                .setIssuer("TrafficAgentDemo")
                .setSubject(user)
                .claim("roles", roles)
                .setIssuedAt(createDate(LocalDateTime.now()))
                .setExpiration(createDate(LocalDateTime.now().plusMonths(12)))
                .setId(UUID.randomUUID().toString())
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(this.environment.getRequiredProperty("token.key"))
                )
                .compact();

    }
}
