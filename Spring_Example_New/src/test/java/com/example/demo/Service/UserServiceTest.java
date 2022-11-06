package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    void createUser() {
        User user1=new User("Matteo", "Mansi", "matteo.mansi@gmail.com");
        User user2=service.createUser(user1);

        assertEquals(user1.getFirstName(),user2.getFirstName());
        assertEquals(user1.getLastName(),user2.getLastName());
        assertEquals(user1.getEmail(),user2.getEmail());

    }

    @Test
    void getAllUsers() {
        User u1 = new User("Matteo","Mansi","matteo@gmail.com");
        User u2 = new User("Mario","Rossi","mario.rossi@gmail.com");
        service.createUser(u1);
        service.createUser(u2);

        List<User> listUser = service.getAllUsers();

        assertEquals(listUser.size(),service.getAllUsers().size());

    }

    @Test
    void updateUser() {
        User u1 = new User("Matteo","Mansi","matteo@gmail.com");
        User u2 = new User("Mario","Rossi","mario.rossi@gmail.com");
        service.createUser(u1);
        service.createUser(u2);
        User u3 = service.updateUser(u2);

        assertEquals(u3.getEmail(),u2.getEmail());
    }

    @Test
    void deleteUser() {
        User u1 = new User(1,"Matteo","Mansi","matteo@gmail.com");
        service.createUser(u1);

        service.deleteUser(u1.getId());

        assertEquals(service.getAllUsers().size(),0);


    }
}