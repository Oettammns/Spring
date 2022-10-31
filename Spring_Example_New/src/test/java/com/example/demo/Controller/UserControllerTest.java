package com.example.demo.Controller;

import com.example.demo.dto.UserDto;
import com.google.gson.Gson;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@DataJpaTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
//@TestPropertySource(locations="classpath:test.properties")
class UserControllerTest {

    @Autowired
    public UserController userController;

    @MockBean
    private UserService userService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {

        when(userService.getAllUsers())
                .thenReturn(List.of(new User("Matteo", "Mansi","matteo.mansi@gmail.com")));
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Matteo"))
                .andExpect(jsonPath("$[0].email").value("matteo.mansi@gmail.com"));


    }

    /*da capire come inizializzare lo user
    @Test
    void testDelete() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(List.of(new User("Matteo", "Mansi","matteo.mansi@gmail.com")));

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

    }
     */

    @Test
    void testUpdate() throws Exception {
        /*
        when(userService.getAllUsers())
                .thenReturn(List.of(new User("Matteo", "Mansi","matteo.mansi@gmail.com")));

        mockMvc.perform(MockMvcRequestBuilders.put("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Matteo"))
                .andExpect(jsonPath("$.email").value("matteoMail@gmail.com"));*/
        UserDto userDto = UserDto.builder()
                .firstName("Matteo")
                .lastName("Mansi")
                .email("matteo.mansi@mail.com")
                .build();

        when(userService.getAllUsers())
                .thenReturn(List.of(new User("Matteo", "Mansi","matteo.mansi@mail.com")));
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().is(201))
                .andExpect(jsonPath("$.firstName").value("Matteo"));


    }


}