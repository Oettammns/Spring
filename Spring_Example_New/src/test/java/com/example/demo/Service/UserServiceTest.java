package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Autowired
    @InjectMocks
    UserService service;

    @Mock
    UserRepository userRepository;


    @Test
    void createUser() {
        service = new UserService(userRepository);
        User user=new User("Matteo", "Mansi", "matteo@gmail.com");
        User user2 = service.createUser(user);


    }

    @Test
    void getAllUsers() {
    }

    @Test
    void updateUser() {
        User expected = new User("Matteo","Mansi","matteo@gmail.com");
        User updated = new User("Matteo","Mansi","matteo.mansi@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(updated);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        final User actual=service.updateUser(updated);
        
        assertEquals(actual.getEmail(),expected.getEmail());
        assertEquals(actual.getId(),expected.getId());
    }

    @Test
    void deleteUser() {
        User user=new User("Matteo", "Mansi", "matteo@gmail.com");
        service.createUser(user);

        service.deleteUser(user.getId());

        assertNull(service.getAllUsers());
    }
}