package com.example.demo.Controller;

import com.example.demo.Errors.UserNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerUnitTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @InjectMocks
    private UserController controller;

    @Test
    void getAllUsers() {
        when(repository.findAll()).thenReturn(Stream
                .of(new User("Matteo", "Mansi", "matteo.mansi@gmail,.com"), new User("Mario", "Rossi", "mario.rossi@gmai.com")).collect(Collectors.toList()));
        assertEquals(2, service.getAllUsers().size());
    }

    @Test
    void getUserById() throws UserNotFoundException {
        User expected=new User("Matteo","Mansi","matteo@gmail.com");

        when(service.getUserById(expected.getId())).thenReturn((User) List.of(expected));

        ResponseEntity<User> result = controller.getUserById(expected.getId());

        assertNotNull(result);
        assertEquals(result, expected);
    }

    @Test
    void createUser() {
        User user = new User("User2", "Cognome", "email@gmai.com");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.createUser(user));
    }



    @Test
    void deleteUser() {
    }
}