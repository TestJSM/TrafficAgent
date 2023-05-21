package com.uco.TrafficAgent.infrastructure.controller.report.consult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.application.service.DtoResponse;
import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.domain.testdatabuilder.DtoLoginTestDataBuilder;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
class ConsultReportTests {

    @Autowired
    private RepositoryReport repositoryReport;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultAllReportByDate() throws  Exception{
        String token = getToken();
        mocMvc.perform(get("/report/list/hour/2023-05-14T11:30")
                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", token))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].latitud", is(80.0)))
                .andExpect(jsonPath("$[0].longitud", is(45.2)))
                .andExpect(jsonPath("$[0].description", is("Description 1")))
                .andExpect(jsonPath("$[0].idUser", is("123456789")));

    }

    @Test
    void consultAllEmptyByDate() throws  Exception{
        String token = getToken();
        mocMvc.perform(get("/report/list/hour/2023-05-21T11:30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalStateException")))
                .andExpect(jsonPath("message", is("No hay reportes asociados al usuario en este horario")));
    }

    @Test
    void consultAllReportByUser() throws  Exception{
        String token = getToken();
        mocMvc.perform(get("/report/list/all/123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].latitud", is(80.0)))
                .andExpect(jsonPath("$[0].longitud", is(45.2)))
                .andExpect(jsonPath("$[0].description", is("Description 1")))
                .andExpect(jsonPath("$[0].idUser", is("123456789")))
                .andExpect(jsonPath("$[1].latitud", is(100.0)))
                .andExpect(jsonPath("$[1].longitud", is(90.0)))
                .andExpect(jsonPath("$[1].description", is("Description 2")))
                .andExpect(jsonPath("$[1].idUser", is("123456789")));

    }

    @Test
    void consultAllEmptyByUser() throws  Exception{
        String token = getToken();
        mocMvc.perform(get("/report/list/all/34646768")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalStateException")))
                .andExpect(jsonPath("message", is("No hay reportes asociados al usuario 34646768")));
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
