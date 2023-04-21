package com.uco.TrafficAgent.domain.dto;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.testdatabuilder.dto.DtoUserSummaryTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.TypeIdentificationTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DtoUserSummaryTest {

    @Test
    void validateCreationSuccessful() {
        DtoUserSummary dto = new DtoUserSummaryTestDataBuilder().byDefault().build();
        TypeIdentification type = new TypeIdentificationTestDataBuilder().byDefault().build();

        Assertions.assertEquals("1234567890", dto.getIdentification());
        Assertions.assertEquals("Nombre", dto.getFullName());
        Assertions.assertEquals("123456789", dto.getCellphone());
        Assertions.assertEquals(type.getType(), dto.getTypeIdentification().getType());
    }
}
