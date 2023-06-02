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

public class ServiceListAllReportByDateTest {

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
        when(repositoryReportFind.listAllReportByDate(eq(LocalDateTime.parse("2023-05-14T11:30")), eq(LocalDateTime.parse("2023-05-14T23:30")))).thenReturn(list);
        ServiceListAllReportByDate serviceListAllReportByDate = new ServiceListAllReportByDate(repositoryReportFind);
        List<DtoReportSummary> reportFind = serviceListAllReportByDate.executeListAll(LocalDateTime.parse("2023-05-14T23:30"));

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
        when(repositoryReportFind.listAllReportByDate(eq(LocalDateTime.now().plusHours(1)), eq(LocalDateTime.now().minusHours(12)))).thenReturn(list);
        ServiceListAllReportByDate serviceListAllReportByDate = new ServiceListAllReportByDate(repositoryReportFind);
        //List<DtoReportSummary> reportFind = serviceListAllReportByDate.executeListAll(LocalDateTime.now().plusHours(1));

        Assertions.assertEquals("No hay reportes asociados al usuario en este horario", Assertions.assertThrows(
                IllegalStateException.class,
                () -> serviceListAllReportByDate.executeListAll(LocalDateTime.now().plusHours(1))).getMessage());
    }
}
