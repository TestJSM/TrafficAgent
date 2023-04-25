package com.uco.TrafficAgent.domain.port.contact;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.dto.DtoReportSummary;
import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.model.Report;

import java.util.List;

public interface RepositoryContact {

    List<DtoContactSummary> listContactByUser(String idUser);
    List<DtoContactSummary> listContactByProximity(String id, double latitude, double longitude);
    void saveContact(Contact contact);

    boolean existContact(String numberPhone);
}
