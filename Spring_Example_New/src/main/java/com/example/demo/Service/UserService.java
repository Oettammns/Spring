package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User userUpd) {
        User user = userRepository.findById(userUpd.getId()).get();

        user.setEmail(userUpd.getEmail());
        user.setFirstName(userUpd.getFirstName());
        user.setLastName(userUpd.getLastName());
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        this.userRepository.delete(user);
    }
}
