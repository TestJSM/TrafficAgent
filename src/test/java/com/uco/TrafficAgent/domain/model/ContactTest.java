package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.testdatabuilder.model.ContactTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ContactTest {

    @Test
    void validateCreationSuccessful() {
        Contact contact = new ContactTestDataBuilder().byDefault().build();

        Assertions.assertEquals(24.567890, contact.getLatitud());
        Assertions.assertEquals(90.34567, contact.getLongitud());
        Assertions.assertEquals("Esta es una decripción", contact.getDescription());
        Assertions.assertEquals("Esto es un nombre", contact.getName());
        Assertions.assertEquals("12345678900", contact.getNumberPhone());
        Assertions.assertEquals("1234567890", contact.getUser().getIdentification());
        Assertions.assertEquals("123456789", contact.getUser().getCellphone());
        Assertions.assertEquals("w6Unpo<code>t0d0", contact.getUser().getPassword());
        Assertions.assertEquals("C.C", contact.getUser().getTypeIdentification().getType());
    }

    @Test
    void validateFieldsMissingDescription() {
        Assertions.assertEquals("La descripción del conatcto no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withDescription("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingName() {
        Assertions.assertEquals("El nombre del contacto no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withName("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingNumberPhone() {
        Assertions.assertEquals("El conatcto debe tener un telefono asociado",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withNumberPhone("").build()).getMessage());
    }

    @Test
    void validateFieldsDescriptionNull() {
        Assertions.assertEquals("La descripción del conatcto no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withDescription(null).build()).getMessage());
    }

    @Test
    void validateFieldNameNull() {
        Assertions.assertEquals("El nombre del contacto no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withName(null).build()).getMessage());
    }

    @Test
    void validateFieldNumberPhoneNull() {
        Assertions.assertEquals("El conatcto debe tener un telefono asociado",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withNumberPhone(null).build()).getMessage());
    }

    @Test
    void validateFieldNumberPhoneNoContainsNumber() {
        Assertions.assertEquals("El número telefonico solo puede tener numeros",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withNumberPhone("adfg").build()).getMessage());
    }

    @Test
    void validateFieldUserNull() {
        Assertions.assertEquals("Si hay un contacto debe haber un usuario asociado",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ContactTestDataBuilder().byDefault().withUser(null).build()).getMessage());
    }

}
