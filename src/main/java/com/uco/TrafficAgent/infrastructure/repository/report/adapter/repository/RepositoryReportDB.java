package com.uco.TrafficAgent.infrastructure.repository.report.adapter.repository;

import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.model.Report;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.infrastructure.repository.report.adapter.entity.EntityReport;
import com.uco.TrafficAgent.infrastructure.repository.report.adapter.repository.jpa.RepositoryReportJpa;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.repository.jpa.RepositoryUserJpa;
import org.springframework.stereotype.Repository;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryReportDB implements RepositoryReport {
    private final RepositoryReportJpa repositoryReportJpa;
    private final RepositoryUserJpa repositoryUserJpa;
    private final Session emailSession;
    private final SpringTemplateEngine templateEngine;


    public RepositoryReportDB(RepositoryReportJpa repositoryReportJpa, RepositoryUserJpa repositoryUserJpa, Session emailSession, SpringTemplateEngine templateEngine) {
        this.repositoryReportJpa = repositoryReportJpa;
        this.repositoryUserJpa = repositoryUserJpa;
        this.emailSession = emailSession;
        this.templateEngine = templateEngine;
    }


    @Override
    public List<DtoReportSummary> listReportByUser(String idUser) {
        List<EntityReport> entities = this.repositoryReportJpa.findByIdentification(idUser);
        return entities.stream().map(entity -> new DtoReportSummary(
                entity.getLatitud(), entity.getLongitud(), entity.getDescription(), entity.getUrl(),
                entity.getEntityUser().getIdentification(),
                entity.getDateReport())).collect(Collectors.toList());
    }

    @Override
    public void saveReport(Report report) throws MessagingException {
        EntityUser entityUser = this.repositoryUserJpa.findByIdentification(report.getUser().getIdentification());
        EntityReport entityReport = new EntityReport(report.getLatitud(), report.getLongitud(),
                report.getDescription(), report.getUrl(), report.getDateReport(),entityUser);

        sendEmail(entityUser.getEmail(), entityUser.getFullName(), "Reporte creado con exito en TrafficAgent",
                "Su registro se ha creado con exito");

        this.repositoryReportJpa.save(entityReport);
    }

    @Override
    public List<DtoReportSummary> listAllReportByDate(LocalDateTime start, LocalDateTime end) {
        List<EntityReport> entities = this.repositoryReportJpa.findByDateBetween(start, end);
        return entities.stream().map(entity -> new DtoReportSummary(
                entity.getLatitud(), entity.getLongitud(), entity.getDescription(), entity.getUrl(),
                entity.getEntityUser().getIdentification(),
                entity.getDateReport())).collect(Collectors.toList());
    }

    private void sendEmail(String to, String name, String subject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(emailSession);
        message.setFrom(new InternetAddress("santicarfranco8@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Creación de reporte");

        Context context = new Context();
        context.setVariable("nombre", name);
        context.setVariable("cuerpoCorreo", body);
        context.setVariable("asunto", subject);
        String htmlBody = templateEngine.process("index.html", context);

        message.setContent(htmlBody, "text/html; charset=utf-8");
        Transport.send(message);
    }

}
