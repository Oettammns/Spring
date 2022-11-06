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
class UserControllerUnitTest {

    @Autowired
    @Mock
    private UserService service;

    @MockBean
    private UserRepository repository;

    @InjectMocks
    private UserController controller;

    /*
    *
    * org.opentest4j.AssertionFailedError:
        Expected :0
        Actual   :2
    * */
    @Test
    void getAllUsers() {
        when(service.getAllUsers()).thenReturn(List
                .of(new User("Matteo", "Mansi", "matteo.mansi@gmail,.com"), new User("Mario", "Rossi", "mario.rossi@gmai.com")));

        List<User> result = controller.getAllUsers();

        assertEquals(result.size(),service.getAllUsers().size());

    }

    //java.lang.NullPointerException: Cannot invoke "com.example.demo.Model.User.getFirstName()" because the return value of "org.springframework.http.ResponseEntity.getBody()" is null
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

        assertEquals("valid update",res);

    }



    @Test
    void deleteUser() {
        User user = new User( "Matteo", "Mansi", "matteo.mansi@gmail.com");
        service.deleteUser(user.getId());
        verify(repository, times(1)).delete(user);
    }
}