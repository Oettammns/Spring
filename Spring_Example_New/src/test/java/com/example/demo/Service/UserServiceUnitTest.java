package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Autowired
    @InjectMocks
    UserService service;

    @Mock
    UserRepository userRepository;

    @Test
    void createUser() {
        User user=new User("Matteo", "Mansi", "matteo@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertEquals(user,service.createUser(new User()));
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
    void getAllUsers() {
        User u1 = new User("Matteo","Mansi","matteo@gmail.com");
        User u2 = new User("Mario","Rossi","mario.rossi@gmail.com");
        when(userRepository.findAll()).thenReturn(List.of(u1,u2));

        List<User> userList = service.getAllUsers();

        // then - verify the output
        assertNotNull(userList);
        assertEquals(userList.size(),2);


    }

    @Test
    void deleteUser() {
        long id = 1L;

        doNothing().when(userRepository).deleteById(1L);

        service.deleteUser(id);
        verify(userRepository,times(1)).deleteById(id);
        verifyNoMoreInteractions(userRepository);
    }
}