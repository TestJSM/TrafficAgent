package com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository;

import com.uco.TrafficAgent.domain.dto.DtoContactSummary;
import com.uco.TrafficAgent.domain.model.Contact;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContact;
import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.entity.EntityContactEmergency;
import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository.jpa.RepositoryContactEmergencyJpa;
import com.uco.TrafficAgent.infrastructure.repository.contact.adapter.repository.jpa.RepositoryContactJpa;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.entity.EntityUser;
import com.uco.TrafficAgent.infrastructure.repository.user.adapter.repository.jpa.RepositoryUserJpa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryContactDB implements RepositoryContact {

    private final RepositoryContactJpa repositoryContactJpa;
    private final RepositoryUserJpa repositoryUserJpa;

    private final RepositoryContactEmergencyJpa repositoryContactEmergencyJpa;

    public RepositoryContactDB(RepositoryContactJpa repositoryContactJpa, RepositoryUserJpa repositoryUserJpa, RepositoryContactEmergencyJpa repositoryContactEmergencyJpa) {
        this.repositoryContactJpa = repositoryContactJpa;
        this.repositoryUserJpa = repositoryUserJpa;
        this.repositoryContactEmergencyJpa = repositoryContactEmergencyJpa;
    }

    @Override
    public List<DtoContactSummary> listContactByUser(String idUser) {
        return null;
    }

    @Override
    public List<DtoContactSummary> listContactByProximity(String id, double latitude, double longitude) {
        List<DtoContactSummary> listAllContacts = new ArrayList<>();


        List<EntityContactEmergency> contactEmergencies = this.repositoryContactEmergencyJpa.findAll();
        List<DtoContactSummary> contactEmergency = contactEmergencies.stream().map(entity -> new DtoContactSummary(entity.getName(),
                entity.getDescription(), entity.getNumberPhone())).toList();


        List<EntityContact> entities = this.repositoryContactJpa.findContactsByLatLong(latitude, longitude, id);
        List<DtoContactSummary> contact = entities.stream().map(entity -> new DtoContactSummary(entity.getName(),
                entity.getDescription(), entity.getNumberPhone())).toList();

        listAllContacts.addAll(contact);
        listAllContacts.addAll(contactEmergency);

        return listAllContacts;
    }

    @Override
    public void saveContact(Contact contact) {
        EntityUser entityUser = this.repositoryUserJpa.findByIdentification(contact.getUser().getIdentification());
        EntityContact entityContact = new EntityContact(contact.getLatitud(), contact.getLongitud(), contact.getName(),
                contact.getDescription(), contact.getNumberPhone(), entityUser);
        this.repositoryContactJpa.save(entityContact);
    }

    @Override
    public boolean existContact(String numberPhone) {
        return this.repositoryContactJpa.findByNumberPhone(numberPhone) != null;
    }
}
