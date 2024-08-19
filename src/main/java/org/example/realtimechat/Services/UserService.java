package org.example.realtimechat.Services;

import org.example.realtimechat.DTO.UserDTO;
import org.example.realtimechat.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> getUserById(Integer userId);
    List<UserDTO> getAllUsers();
    User createUSer(User user);
    User updateUser(Integer userId, UserDTO userDTO);
    void deleteUser(Integer userId);
}
