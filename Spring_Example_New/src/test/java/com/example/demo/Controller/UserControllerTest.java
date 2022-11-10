package com.example.demo.Controller;

import com.example.demo.dto.UserDto;
import com.google.gson.Gson;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.aspectj.lang.annotation.Before;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

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
//@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@RestController
class UserControllerTest {
    @MockBean
    private UserService userService;
/*
    @Autowired
    private WebApplicationContext webApplicationContext;
*/

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
    //java.lang.AssertionError: Response status expected:<201> but was:<404>
    @Test
    void testUpdate() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(List.of(new User("Matteo", "Mansi","matteo.mansi@mail.com")));
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().is(201))
                .andExpect(jsonPath("$.firstName").value("Matteo"));


    }
    //java.lang.AssertionError: Content type not set
    @Test
    void testCreate() throws Exception{

      //  mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User user=new User("Matteo","Mansi","matteo.mansi@mail.com");

        Gson gson=new Gson();
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.firstName").value("Matteo"))
                .andExpect(jsonPath("$.email").value("matteo.mansi@mail.com"));

    }


}