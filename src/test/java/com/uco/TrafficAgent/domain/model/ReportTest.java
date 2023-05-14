package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.testdatabuilder.model.ReportTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportTest {

    @Test
    void validateCreationSuccessful() {
        Report report = new ReportTestDataBuilder().byDefault().build();

        Assertions.assertEquals(24.567890, report.getLatitud());
        Assertions.assertEquals(90.34567, report.getLongitud());
        Assertions.assertEquals("Esta es una decripción", report.getDescription());
        Assertions.assertEquals("Url de prueba", report.getUrl());
        Assertions.assertEquals("1234567890", report.getUser().getIdentification());
        Assertions.assertEquals("123456789", report.getUser().getCellphone());
        Assertions.assertEquals("w6Unpo<code>t0d0", report.getUser().getPassword());
        Assertions.assertEquals("C.C", report.getUser().getTypeIdentification().getType());
    }

    @Test
    void validateFieldsMissingDescription() {
        Assertions.assertEquals("La descripción del reporte no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ReportTestDataBuilder().byDefault().withDescription("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingUrl() {
        Assertions.assertEquals("La url del reporte no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ReportTestDataBuilder().byDefault().withUrl("").build()).getMessage());
    }

    @Test
    void validateFieldsDescriptionNull() {
        Assertions.assertEquals("La descripción del reporte no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ReportTestDataBuilder().byDefault().withDescription(null).build()).getMessage());
    }

    @Test
    void validateFieldUrlNull() {
        Assertions.assertEquals("La url del reporte no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ReportTestDataBuilder().byDefault().withUrl(null).build()).getMessage());
    }

    @Test
    void validateFieldUserNull() {
        Assertions.assertEquals("Si hay un registro debe haber un usuario asociado",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new ReportTestDataBuilder().byDefault().withUser(null).build()).getMessage());
    }
}
