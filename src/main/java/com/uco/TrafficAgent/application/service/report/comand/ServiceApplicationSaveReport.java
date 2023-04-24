package com.uco.TrafficAgent.application.service.report.comand;

import com.uco.TrafficAgent.application.mapper.report.impl.MapperApplicationReportImpl;
import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.domain.service.report.ServiceSaveReport;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationSaveReport {

    private final ServiceSaveReport serviceSaveReport;
    private final MapperApplicationReportImpl mapperApplicationReport;

    public ServiceApplicationSaveReport(ServiceSaveReport serviceSaveReport, MapperApplicationReportImpl mapperApplicationReport) {
        this.serviceSaveReport = serviceSaveReport;
        this.mapperApplicationReport = mapperApplicationReport;
    }

    public void execute(DtoSaveReport dto){
        this.serviceSaveReport.executeSave(this.mapperApplicationReport.mapperDtoToDomain(dto));
    }
}
