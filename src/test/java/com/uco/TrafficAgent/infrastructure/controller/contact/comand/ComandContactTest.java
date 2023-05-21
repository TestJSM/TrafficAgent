package com.uco.TrafficAgent.infrastructure.controller.contact.comand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.application.service.DtoResponse;
import com.uco.TrafficAgent.application.service.contact.comand.dto.DtoSaveContact;
import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.domain.testdatabuilder.DtoLoginTestDataBuilder;
import com.uco.TrafficAgent.infrastructure.ApplicationMock;
import com.uco.TrafficAgent.infrastructure.controller.contact.comand.model.ComandContactTestDataBuilder;
import com.uco.TrafficAgent.infrastructure.controller.user.comand.model.ComandUserTestDataBuilder;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
        createUserTest();
        String token = getToken();
        DtoSaveContact comandUserTestsDataBuilder = new ComandContactTestDataBuilder()
                .byDefault().withUser("111").withNumberPhone("546789097").build();

        mocMvc.perform(post("/contact/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Boolean exist = repositoryContact.existContact(comandUserTestsDataBuilder.getNumberPhone());

        Assertions.assertEquals(true, exist);
    }

    @Test
    void noCreateContactBecauseItWasAlreadyCreatedWhitSameNumberPhone() throws Exception {
        createUserTest();
        String token = getToken();
        DtoSaveContact comandUserTestsDataBuilder = new ComandContactTestDataBuilder()
                .byDefault().withUser("111").withNumberPhone("8765567").build();

        mocMvc.perform(post("/contact/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("Ya existe un contacto con este numero telefonico")));
    }

    private void createUserTest() throws Exception {
        DtoSaveUser comandUserTestsDataBuilder = new ComandUserTestDataBuilder()
                .byDefault().withIdentification("111").withName("Nombre nuevo").withCellPhone("111")
                .withPassword("w9Snpo<code>t0d0").build();

        mocMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }

    private String getToken() throws Exception {
        DtoLogin login = new DtoLoginTestDataBuilder().byDefault().build();
        var resultLogin = mocMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login))
                )
                .andExpect(status().isOk())
                .andReturn();
        return (String) objectMapper.readValue(resultLogin.getResponse().getContentAsString(), DtoResponse.class).getValor();
    }
}
