package com.uco.TrafficAgent.domain.dto;

import com.uco.TrafficAgent.domain.model.TypeIdentification;
import com.uco.TrafficAgent.domain.testdatabuilder.dto.DtoContactSummaryTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.dto.DtoUserSummaryTestDataBuilder;
import com.uco.TrafficAgent.domain.testdatabuilder.model.TypeIdentificationTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DtoContactSummaryTest {

    @Test
    void validateCreationSuccessful() {
        DtoContactSummary dto = new DtoContactSummaryTestDataBuilder().byDefault().build();
        Assertions.assertEquals("Descripción", dto.getDescription());
        Assertions.assertEquals("Nombre", dto.getName());
        Assertions.assertEquals("123456789", dto.getNumberPhone());
    }
}
