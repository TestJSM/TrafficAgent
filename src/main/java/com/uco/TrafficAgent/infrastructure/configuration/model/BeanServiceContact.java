package com.uco.TrafficAgent.infrastructure.configuration.model;


import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.domain.service.contact.ServiceListsAllByProximityContact;
import com.uco.TrafficAgent.domain.service.contact.ServiceSaveContact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServiceContact {

    @Bean
    public ServiceSaveContact serviceSaveContact(RepositoryContact repositoryContact){
        return new ServiceSaveContact(repositoryContact);
    }

    @Bean
    public ServiceListsAllByProximityContact serviceListsAllByProximityContact(RepositoryContact repositoryContact){
        return new ServiceListsAllByProximityContact(repositoryContact);
    }
}
