package com.uco.TrafficAgent.infrastructure.controller.contact.comand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.application.service.contact.comand.dto.DtoSaveContact;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.infrastructure.ApplicationMock;
import com.uco.TrafficAgent.infrastructure.controller.user.comand.comandtestdatabuilder.model.ComandContactTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandContactTest {

    @Autowired
    private RepositoryContact repositoryContact;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    void createContactSuccessful() throws Exception {
        DtoSaveContact comandUserTestsDataBuilder = new ComandContactTestDataBuilder()
                .byDefault().withNumberPhone("546789097").build();

        mocMvc.perform(post("/contact/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Boolean exist = repositoryContact.existContact(comandUserTestsDataBuilder.getNumberPhone());

        Assertions.assertEquals(true, exist);
    }

    @Test
    void noCreateContactBecauseItWasAlreadyCreatedWhitSameNumberPhone() throws Exception {
        DtoSaveContact comandUserTestsDataBuilder = new ComandContactTestDataBuilder()
                .byDefault().withNumberPhone("8765567").build();

        mocMvc.perform(post("/contact/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("Ya existe un contacto con este numero telefonico")));
    }
}
