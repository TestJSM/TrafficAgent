package com.uco.TrafficAgent.infrastructure.controller.report.comand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.application.service.DtoResponse;
import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;
import com.uco.TrafficAgent.application.service.report.comand.dto.DtoSaveReport;
import com.uco.TrafficAgent.domain.port.report.RepositoryReport;
import com.uco.TrafficAgent.domain.testdatabuilder.DtoLoginTestDataBuilder;
import com.uco.TrafficAgent.infrastructure.ApplicationMock;
import com.uco.TrafficAgent.infrastructure.controller.report.comand.model.ComandReportTestDataBuilder;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandReportTest {

    @Autowired
    private RepositoryReport repositoryReport;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    void createReportSuccessful() throws Exception {
        String token = getToken();
        DtoSaveReport comandReportTestsDataBuilder = new ComandReportTestDataBuilder()
                .byDefault().withUser("123456789").build();

        mocMvc.perform(post("/report/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(comandReportTestsDataBuilder)))
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
