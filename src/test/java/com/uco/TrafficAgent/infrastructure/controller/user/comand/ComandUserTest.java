package com.uco.TrafficAgent.infrastructure.controller.user.comand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoUpdateUser;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.infrastructure.ApplicationMock;
import com.uco.TrafficAgent.infrastructure.controller.user.comand.comandtestdatabuilder.model.ComandUserTestDataBuilder;
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

import java.util.List;

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
class ComandUserTest {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    void createUserSuccessful() throws Exception {
        DtoSaveUser comandUserTestsDataBuilder = new ComandUserTestDataBuilder()
                .byDefault().withIdentification("892").withName("Nombre nuevo").withCellPhone("23456789")
                .withPassword("w9Snpo<code>t0d0").build();

        mocMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        List<DtoUserSummary> userCreated = repositoryUser.listUser();

        Assertions.assertEquals("892", userCreated.get(2).getIdentification());
        Assertions.assertEquals("Nombre nuevo", userCreated.get(2).getFullName());
        Assertions.assertEquals("23456789", userCreated.get(2).getCellphone());
        Assertions.assertEquals("C.C", userCreated.get(2).getTypeIdentification().getType());
    }

    @Test
    void noCreateUserPasswordSizeIncorrect() throws Exception {
        DtoSaveUser comandUserTestsDataBuilder = new ComandUserTestDataBuilder()
                .byDefault().withIdentification("892").withName("Nombre nuevo").withCellPhone("23456789")
                .withPassword("w9245Snpo<code>t0d0").build();

        mocMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("La contarseña no cumple con el tamaño requerido")));
    }

    @Test
    void noCreateUserPasswordIncorrect() throws Exception {
        DtoSaveUser comandUserTestsDataBuilder = new ComandUserTestDataBuilder()
                .byDefault().withIdentification("892").withName("Nombre nuevo").withCellPhone("23456789")
                .withPassword("12fdsbu754hk8").build();

        mocMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("La contarseña no es permitida")));
    }

    @Test
    void noCreateUserBecauseItWasAlreadyCreated() throws Exception {
        DtoSaveUser comandUserTestsDataBuilder = new ComandUserTestDataBuilder()
                .byDefault().withIdentification("123456789").withName("Nombre 1").withCellPhone("54676468")
                .withPassword("w6Unpo<code>t0d0").withType(1).build();

        mocMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("Ya existe un usuario con esos datos")));
    }
    @Test
    void updateUserSuccessful() throws Exception {
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789","w9Snpo<code>t0d0", "@email.com",2);

        mocMvc.perform(put("/user/update?id=" + "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is2xxSuccessful());

        List<DtoUserSummary> userUpdate = repositoryUser.listUser();

        Assertions.assertEquals("123456789", userUpdate.get(0).getIdentification());
        Assertions.assertEquals("Cambio", userUpdate.get(0).getFullName());
        Assertions.assertEquals("T.I", userUpdate.get(0).getTypeIdentification().getType());
        Assertions.assertEquals("456789", userUpdate.get(0).getCellphone());
    }

    @Test
    void noUpdateUserBecauseNoExists() throws Exception {
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789", "w9Snpo<code>t0d0", "@email.com",2);

        mocMvc.perform(put("/user/update?id=" + "98")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())

                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("No hay ningún usuario con esa identificación")));
    }

    @Test
    void noUpdateUserPasswordSizeIncorrect() throws Exception {
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789", "w9245Snpo<code>t0d0", "@email.com",2);

        mocMvc.perform(put("/user/update?id=" + "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())

                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("La contarseña no cumple con el tamaño requerido")));
    }

    @Test
    void noUpdateUserPasswordPasswordIncorrect() throws Exception {
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789", "12fdsbu754hk8", "@email.com",2);

        mocMvc.perform(put("/user/update?id=" + "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())

                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("La contarseña no es permitida")));
    }

    @Test
    void deleteUserSuccessful() throws Exception {
        mocMvc.perform(delete("/user/delete/34646768")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void noDeleteUserBecauseNoExists() throws Exception {
        mocMvc.perform(delete("/user/delete/965576")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("No existe este usuario para eliminar")));
    }
}
