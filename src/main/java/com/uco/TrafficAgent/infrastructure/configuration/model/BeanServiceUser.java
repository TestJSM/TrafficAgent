package com.uco.TrafficAgent.infrastructure.configuration.model;


import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.service.user.ServiceDeleteUser;
import com.uco.TrafficAgent.domain.service.user.ServiceListAllUser;
import com.uco.TrafficAgent.domain.service.user.ServiceSaveUser;
import com.uco.TrafficAgent.domain.service.user.ServiceUpdateUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServiceUser {

    @Bean
    public ServiceSaveUser serviceSaveUser(RepositoryUser repositoryUser){
        return new ServiceSaveUser(repositoryUser);
    }

    @Bean
    public ServiceDeleteUser serviceDeleteUser(RepositoryUser repositoryUser){
        return new ServiceDeleteUser(repositoryUser);
    }

    @Bean
    public ServiceUpdateUser serviceUpdateUser(RepositoryUser repositoryUser){
        return new ServiceUpdateUser(repositoryUser);
    }

    @Bean
    public ServiceListAllUser serviceListAllUser(RepositoryUser repositoryUser){
        return new ServiceListAllUser(repositoryUser);
    }
}
