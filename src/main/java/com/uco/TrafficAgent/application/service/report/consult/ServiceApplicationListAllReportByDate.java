package com.uco.TrafficAgent.application.service.report.consult;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.service.report.ServiceListAllReportByDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ServiceApplicationListAllReportByDate {

    private final ServiceListAllReportByDate serviceListAllReportByDate;

    public ServiceApplicationListAllReportByDate(ServiceListAllReportByDate serviceListAllReportByDate) {
        this.serviceListAllReportByDate = serviceListAllReportByDate;
    }


    public List<DtoReportSummary> execute(LocalDateTime dateTime){
        return this.serviceListAllReportByDate.executeListAll(dateTime);
    }
}
