package com.uco.TrafficAgent.infrastructure.configuration.model;


import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.domain.service.report.ServiceListAllReportByUser;
import com.uco.TrafficAgent.domain.service.report.ServiceSaveReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServiceReport {

    @Bean
    public ServiceSaveReport serviceSaveReport(RepositoryReport repositoryReport){
        return new ServiceSaveReport(repositoryReport);
    }

    @Bean
    public ServiceListAllReportByUser serviceListAllReportByUser(RepositoryReport repositoryReport){
        return new ServiceListAllReportByUser(repositoryReport);
    }
}
