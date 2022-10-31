package com.example.demo.Controller;

import com.example.demo.Errors.UserNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    /*da capire se anche qui da mettere nel costruttore*/
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException{
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/names")
    public ResponseEntity<User> searchUserByNome(@RequestParam(name="firstName") String name) throws UserNotFoundException {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok().body(user);
    }
    /*
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestParam(name="firstName") @Validated String name,@RequestParam(name="lastName") @Validated String lastName,@RequestParam(name="email") @Validated String email){
        User user = new User(name,lastName,email);
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }*/



    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Validated User user){
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody @Validated User userDetails){
        this.userService.updateUser(userDetails);
        return ResponseEntity.ok("valid update");
    }

    @DeleteMapping(value ="/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long userId){
        this.userService.deleteUser(userId);
    }


}
