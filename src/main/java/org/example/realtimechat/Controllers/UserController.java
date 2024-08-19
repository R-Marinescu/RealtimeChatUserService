package org.example.realtimechat.Controllers;


import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.connector.Response;
import org.example.realtimechat.Config.MyUserPrincipal;
import org.example.realtimechat.DTO.UserDTO;
import org.example.realtimechat.Models.User;
import org.example.realtimechat.Services.UserService;
import org.example.realtimechat.Services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        Optional<UserDTO> optionalUserDTO = userService.getUserById(userId);

        if(optionalUserDTO.isPresent()) {
            UserDTO userDTO = optionalUserDTO.get();

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user-details")
    public UserDTO getUserDetails(@AuthenticationPrincipal MyUserPrincipal user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        if(!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUSer = userService.createUSer(user);
        return new ResponseEntity<>(createdUSer, HttpStatus.OK);
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId) {

        try {
            User updatedUser = userService.updateUser(userId, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
