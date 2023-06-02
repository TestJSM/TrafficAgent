package com.uco.TrafficAgent.infrastructure.configuration.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.mail.*;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class JavaMailConfig {

    @Value("${app.email}")
    private String email;

    @Value("${app.email.pass}")
    private String pass;

    @Bean
    public Session emailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,
                        pass);
            }
        };

        return Session.getInstance(props, auth);
    }
}
