package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@DataJpaTest
@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchUser() {
        User Mario = new User("Mario","Rossi","mail");
        when(userService.getUserById(Mario.getId()))
                .thenReturn(Mario);
        String name = "Mario";
        assertEquals(Mario.getFirstName(),name);
    }



    @Test
    void getAllUsers() throws Exception {

        when(userService.getAllUsers())
                .thenReturn(List.of(new User("Matteo", "Mansi","matteo.mansi@gmail.com")));
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Matteo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("matteo.mansi@gmail.com"));


    }

    @Test
    public void testUpdateUsers() {
        User user = userService.getUserById(1L);
        user.setEmail("matteo_mansi@gmail.com");

        userService.updateUser(user);

        User updatedUser = userService.getUserById(1L);

        assertEquals(updatedUser.getEmail(),"matteo_mansi@gmail.com");
    }
}