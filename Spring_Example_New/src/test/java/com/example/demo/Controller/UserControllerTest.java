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
//@TestPropertySource(locations="classpath:application-test.properties")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@RestController
class UserControllerTest {


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
        User expected=new User("Matteo","Mansi","matteo@gmail.com");

        Gson gson=new Gson();

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(expected)));
        mockMvc .perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Matteo"))
                .andExpect(jsonPath("$[0].email").value("matteo@gmail.com"));


    }


    @Test
    void testUpdate() throws Exception {
        User expected=new User(1,"Matteo","Mansi","matteo@gmail.com");
        User user_new=new User(1,"firstName2","firstName2","email2@mail.com");


        Gson gson=new Gson();

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(expected)));
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(user_new))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
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
                //.andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Matteo"))
                .andExpect(jsonPath("$.email").value("matteo.mansi@mail.com"));

    }


}