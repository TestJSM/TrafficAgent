package com.uco.TrafficAgent.infrastructure.controller.contact.consult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.domain.port.contact.RepositoryContact;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.infrastructure.ApplicationMock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultContactByProximityTests {

    @Autowired
    private RepositoryContact repositoryContact;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultAllContact() throws  Exception{
        mocMvc.perform(get("/contact/list/all/123456789?latitud=10.0&longitud=50.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].numberPhone", is("123456789")))
                .andExpect(jsonPath("$[1].numberPhone", is("8765567")))
                .andExpect(jsonPath("$[2].numberPhone", is("987654")));
    }

    @Test
    void consultAllEmpty() throws  Exception{
        mocMvc.perform(get("/contact/list/all/1?latitud=10.0&longitud=50.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("No tienes contactos cercanos")));
    }

}
