package com.uco.TrafficAgent.domain.service.report;

import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.model.User;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.service.user.ServiceSaveUser;
import com.uco.TrafficAgent.domain.testdatabuilder.model.ReportTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

class ServiceSaveReportTest {

    @Test
    void createUserSuccessful() throws MessagingException {
        Report report = new ReportTestDataBuilder().byDefault().withDate(LocalDateTime.parse("2023-05-14T10:30")).build();

        RepositoryReport repositoryReport = Mockito.mock(RepositoryReport.class);
        ServiceSaveReport serviceSaveReport = new ServiceSaveReport(repositoryReport);
        serviceSaveReport.executeSave(report);

        Mockito.verify(repositoryReport, Mockito.times(1)).saveReport(report);
    }

}