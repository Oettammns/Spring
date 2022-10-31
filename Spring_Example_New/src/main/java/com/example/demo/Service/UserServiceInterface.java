package com.example.demo.Service;

import com.example.demo.Errors.UserNotFoundException;
import com.example.demo.Model.User;

import java.util.List;

public interface UserServiceInterface {

    User createUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id) throws UserNotFoundException;
    void deleteUser(Long id);
    User getUserByName(String name) throws UserNotFoundException;

}
