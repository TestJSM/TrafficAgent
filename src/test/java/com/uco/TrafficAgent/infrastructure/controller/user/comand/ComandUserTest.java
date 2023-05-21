package com.uco.TrafficAgent.infrastructure.controller.user.comand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.TrafficAgent.application.service.login.dto.DtoLogin;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoRolUser;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoUpdateUser;
import com.uco.TrafficAgent.domain.dto.DtoUserSummary;
import com.uco.TrafficAgent.domain.port.user.RepositoryUser;
import com.uco.TrafficAgent.domain.testdatabuilder.DtoLoginTestDataBuilder;
import com.uco.TrafficAgent.application.service.DtoResponse;
import com.uco.TrafficAgent.infrastructure.ApplicationMock;
import com.uco.TrafficAgent.infrastructure.controller.user.comand.model.ComandRolTestDataBuilder;
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

import java.util.Collections;
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

        Assertions.assertEquals("892", userCreated.get(3).getIdentification());
        Assertions.assertEquals("Nombre nuevo", userCreated.get(3).getFullName());
        Assertions.assertEquals("23456789", userCreated.get(3).getCellphone());
        Assertions.assertEquals("C.C", userCreated.get(3).getTypeIdentification().getType());
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
        createUserTest();
        String token = getToken();
        DtoRolUser rol = new ComandRolTestDataBuilder().byDefault().build();
        List<DtoRolUser> roles = Collections.singletonList(rol);
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789","w9Snpo<code>t0d0", "@email.com",2, roles);

        mocMvc.perform(put("/user/update?id=" + "000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is2xxSuccessful());

        List<DtoUserSummary> userUpdate = repositoryUser.listUser();
        int value = userUpdate.size();

        Assertions.assertEquals("000", userUpdate.get(value-1).getIdentification());
        Assertions.assertEquals("Cambio", userUpdate.get(value-1).getFullName());
        Assertions.assertEquals("T.I", userUpdate.get(value-1).getTypeIdentification().getType());
        Assertions.assertEquals("456789", userUpdate.get(value-1).getCellphone());
    }

    @Test
    void noUpdateUserBecauseNoExists() throws Exception {
        String token = getToken();
        DtoRolUser rol = new ComandRolTestDataBuilder().byDefault().build();
        List<DtoRolUser> roles = Collections.singletonList(rol);
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789", "w9Snpo<code>t0d0", "@email.com",2, roles);

        mocMvc.perform(put("/user/update?id=" + "98")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())

                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("No hay ningún usuario con esa identificación")));
    }

    @Test
    void noUpdateUserPasswordSizeIncorrect() throws Exception {
        String token = getToken();
        DtoRolUser rol = new ComandRolTestDataBuilder().byDefault().build();
        List<DtoRolUser> roles = Collections.singletonList(rol);
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789", "w9245Snpo<code>t0d0", "@email.com",2, roles);

        mocMvc.perform(put("/user/update?id=" + "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())

                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("La contarseña no cumple con el tamaño requerido")));
    }

    @Test
    void noUpdateUserPasswordPasswordIncorrect() throws Exception {
        String token = getToken();
        DtoRolUser rol = new ComandRolTestDataBuilder().byDefault().build();
        List<DtoRolUser> roles = Collections.singletonList(rol);
        DtoUpdateUser comandUserTestsDataBuilder = new DtoUpdateUser("Cambio", "456789", "12fdsbu754hk8", "@email.com",2, roles);

        mocMvc.perform(put("/user/update?id=" + "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(comandUserTestsDataBuilder)))
                .andExpect(status().is4xxClientError())

                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("La contarseña no es permitida")));
    }

    @Test
    void deleteUserSuccessful() throws Exception {
        String token = getToken();
        mocMvc.perform(delete("/user/delete/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void noDeleteUserBecauseNoExists() throws Exception {
        String token = getToken();
        mocMvc.perform(delete("/user/delete/965576")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("exceptionName", is("IllegalArgumentException")))
                .andExpect(jsonPath("message", is("No existe este usuario para eliminar")));
    }

    private void createUserTest() throws Exception {
        DtoSaveUser comandUserTestsDataBuilder = new ComandUserTestDataBuilder()
                .byDefault().withIdentification("000").withName("Nombre nuevo").withCellPhone("000")
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
