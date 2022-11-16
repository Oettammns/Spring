package com.example.demo.Controller;

import com.example.demo.Errors.UserNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@TestPropertySource(locations="classpath:application-test.properties")
class UserControllerUnitTest {
    //@Autowired
    @Mock
    private UserService service;

    @MockBean
    private UserRepository repository;

    @InjectMocks
    private UserController controller;

    @Test
    void getAllUsers() {
        User expected=new User(1,"Matteo","Mansi","matteo@gmail.com");
        when(service.getAllUsers()).thenReturn(List.of(expected));
        List<User> result = controller.getAllUsers();

        assertEquals(result.size(),service.getAllUsers().size());

    }
    @Test
    void createUser() {
        User user = new User("User2", "Cognome", "email@gmai.com");
        when(service.createUser(user)).thenReturn(user);

        ResponseEntity<User> result=controller.createUser(user);
        assertEquals(user.getFirstName(), result.getBody().getFirstName());
    }

    //java.util.NoSuchElementException: No value present
    @Test
    void updateUser(){
        User user = new User(1,"User2", "Cognome", "email@gmai.com");
        when(service.updateUser(user)).thenReturn(user);

        ResponseEntity<String> res = controller.updateUser(user);

        assertEquals("valid update",res.getBody());

    }

}