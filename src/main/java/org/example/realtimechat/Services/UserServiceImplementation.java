package org.example.realtimechat.Services;

import jakarta.persistence.EntityNotFoundException;
import org.example.realtimechat.DTO.UserDTO;
import org.example.realtimechat.Models.User;
import org.example.realtimechat.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImplementation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDTO convertUserToDTO(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName());
    }


    @Override
    public Optional<UserDTO> getUserById(Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);

        return optionalUser.map(this::convertUserToDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User createUSer(User user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());

        return userRepo.save(newUser);
    }

    @Override
    public User updateUser(Integer userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepo.findById(userId);
        User existingUser;

        if(optionalUser.isPresent()) {
            existingUser = optionalUser.get();

            if(userDTO.getUsername() != null) {
                existingUser.setUsername(userDTO.getUsername());
            }

            if(userDTO.getFirstName() != null) {
                existingUser.setFirstName(userDTO.getFirstName());
            }

            if(userDTO.getLastName() != null) {
                existingUser.setLastName(userDTO.getLastName());
            }
        } else {
            throw new EntityNotFoundException("User with Id " + userId + " not found");
        }

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) {

    }
}
