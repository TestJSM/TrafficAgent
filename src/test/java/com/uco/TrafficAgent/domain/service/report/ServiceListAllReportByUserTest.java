package com.uco.TrafficAgent.domain.service.report;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.domain.testdatabuilder.model.ReportTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ServiceListAllReportByUserTest {

    @Test
    void findReportsSuccessfulByDate() throws MessagingException {
        Report report = new ReportTestDataBuilder().byDefault().withDate(LocalDateTime.parse("2023-05-14T10:30")).build();
        DtoReportSummary dtoUno = new DtoReportSummary(report.getLatitud(), report.getLongitud(),
                report.getDescription(), report.getUrl(), report.getUser().getIdentification(),
                report.getDateReport());
        List<DtoReportSummary> list = new ArrayList<>();
        list.add(dtoUno);

        //Create
        RepositoryReport repositoryReport = Mockito.mock(RepositoryReport.class);
        ServiceSaveReport serviceSaveReport = new ServiceSaveReport(repositoryReport);
        serviceSaveReport.executeSave(report);

        //Find
        RepositoryReport repositoryReportFind = Mockito.mock(RepositoryReport.class);
        when(repositoryReportFind.listReportByUser(eq(report.getUser().getIdentification()))).thenReturn(list);
        ServiceListAllReportByUser serviceListAllReportByUser = new ServiceListAllReportByUser(repositoryReportFind);
        List<DtoReportSummary> reportFind = serviceListAllReportByUser.executeListAll(report.getUser().getIdentification());

        Assertions.assertEquals(reportFind.get(0).getDescription(), dtoUno.getDescription());
        Assertions.assertEquals(reportFind.get(0).getLatitud(), dtoUno.getLatitud());
        Assertions.assertEquals(reportFind.get(0).getLongitud(), dtoUno.getLongitud());
        Assertions.assertEquals(reportFind.get(0).getUrl(), dtoUno.getUrl());
        Assertions.assertEquals(reportFind.get(0).getIdUser(), dtoUno.getIdUser());
        Assertions.assertEquals(reportFind.get(0).getDateReport(), dtoUno.getDateReport());

    }

    @Test
    void noFindReport() throws MessagingException {
        Report report = new ReportTestDataBuilder().byDefault().build();
        List<DtoReportSummary> list = new ArrayList<>();

        //Create
        RepositoryReport repositoryReport = Mockito.mock(RepositoryReport.class);
        ServiceSaveReport serviceSaveReport = new ServiceSaveReport(repositoryReport);
        serviceSaveReport.executeSave(report);

        //Find
        RepositoryReport repositoryReportFind = Mockito.mock(RepositoryReport.class);
        when(repositoryReportFind.listReportByUser(eq("12345"))).thenReturn(list);
        ServiceListAllReportByUser serviceListAllReportByUser = new ServiceListAllReportByUser(repositoryReportFind);
        //List<DtoReportSummary> reportFind = serviceListAllReportByUser.executeListAll(report.getUser().getIdentification());

        Assertions.assertEquals("No hay reportes asociados al usuario 12345", Assertions.assertThrows(
                IllegalStateException.class,
                () -> serviceListAllReportByUser.executeListAll("12345")).getMessage());
    }
}
