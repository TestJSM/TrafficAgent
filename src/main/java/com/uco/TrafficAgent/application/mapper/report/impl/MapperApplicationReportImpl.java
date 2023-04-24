package com.uco.TrafficAgent.application.mapper.report.impl;

import com.uco.TrafficAgent.application.mapper.MapperApplicationObject;
import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.type.RepositoryTypeId;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperApplicationReportImpl implements MapperApplicationObject<DtoSaveReport, Report> {

    @Autowired
    private RepositoryUser repositoryUser;


    @Override
    public Report mapperDtoToDomain(DtoSaveReport dto) {
        return Report.createReport(dto.getLatitud(), dto.getLongitud(), dto.getDescription(),
                dto.getUrl(),
                this.repositoryUser.consultUserByIdentification(dto.getIdUser()));
    }
}
