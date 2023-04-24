package com.uco.TrafficAgent.application.service.report.consult;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.service.report.ServiceListAllReportByUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceApplicationListAllReportByUser {

    private final ServiceListAllReportByUser serviceListAllReportByUser;

    public ServiceApplicationListAllReportByUser(ServiceListAllReportByUser serviceListAllReportByUser) {
        this.serviceListAllReportByUser = serviceListAllReportByUser;
    }


    public List<DtoReportSummary> execute(String idUser){
        return this.serviceListAllReportByUser.executeListAll(idUser);
    }
}
