package com.uco.TrafficAgent.infrastructure.controller.user.consult;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class ConsultUserTests {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultAllUsers() throws  Exception{
        mocMvc.perform(get("/user/list/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].identification", is("123456789")))
                .andExpect(jsonPath("$[0].fullName", is("Nomble 1")))
                .andExpect(jsonPath("$[0].cellphone", is("54676468")))
                .andExpect(jsonPath("$[0].typeIdentification.type", is("C.C")))
                .andExpect(jsonPath("$[1].identification", is("34646768")))
                .andExpect(jsonPath("$[1].fullName", is("Nomble 2")))
                .andExpect(jsonPath("$[1].cellphone", is("345679098")))
                .andExpect(jsonPath("$[1].typeIdentification.type", is("T.I")));
    }

}
