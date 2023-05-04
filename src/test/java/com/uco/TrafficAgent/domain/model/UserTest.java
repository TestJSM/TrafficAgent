package com.uco.TrafficAgent.domain.model;

import com.uco.TrafficAgent.domain.testdatabuilder.model.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void validateCreationSuccessful() {
        User user = new UserTestDataBuilder().byDefault().build();

        Assertions.assertEquals("1234567890", user.getIdentification());
        Assertions.assertEquals("Nombre", user.getFullName());
        Assertions.assertEquals("123456789", user.getCellphone());
        Assertions.assertEquals("w6Unpo<code>t0d0", user.getPassword());
        Assertions.assertEquals("C.C", user.getTypeIdentification().getType());
    }


    @Test
    void validateFieldsMissingIdentification() {
        Assertions.assertEquals("La identifiación del usuraio no puede ser nula",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withIdentification("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingFullName() {
        Assertions.assertEquals("El nombre del usuraio no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withName("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingEmail() {
        Assertions.assertEquals("Un usuario debe tener un email asociado",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withEmail("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingCellPhone() {
        Assertions.assertEquals("El celular del usuraio no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withCellPhone("").build()).getMessage());
    }

    @Test
    void validateFieldsMissingPassword() {
        Assertions.assertEquals("La contraseña del usuraio no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withPassword("").build()).getMessage());
    }

    @Test
    void validateContainNumberIdentification() {
        Assertions.assertEquals("La identificación tiene caracteres no soportados",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withIdentification("agfhjkjñ").build()).getMessage());
    }

    @Test
    void validateContainNumberCellPhone() {
        Assertions.assertEquals("El tefono tiene caracteres no soportados",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withCellPhone("13sah").build()).getMessage());
    }

    @Test
    void validateFieldsIdentificationNull() {
        Assertions.assertEquals("La identifiación del usuraio no puede ser nula",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withIdentification(null).build()).getMessage());
    }

    @Test
    void validateFieldsFullNameNull() {
        Assertions.assertEquals("El nombre del usuraio no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withName(null).build()).getMessage());
    }

    @Test
    void validateFieldsCellPhoneNull() {
        Assertions.assertEquals("El celular del usuraio no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withCellPhone(null).build()).getMessage());
    }

    @Test
    void validateFieldsPasswordNull() {
        Assertions.assertEquals("La contraseña del usuraio no puede ser nulo",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withPassword(null).build()).getMessage());
    }

    @Test
    void validateFieldsIdentificationType() {
        Assertions.assertEquals("El tipo de identiicación no puede ser nula",
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new UserTestDataBuilder().byDefault().withTypeIdentification(null).build()).getMessage());
    }
}
