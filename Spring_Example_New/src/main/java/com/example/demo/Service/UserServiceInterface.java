package com.example.demo.Service;

import com.example.demo.Model.User;

import java.util.List;

public interface UserServiceInterface {

    User createUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);

}
