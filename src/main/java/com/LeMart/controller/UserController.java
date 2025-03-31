package com.LeMart.controller;

import com.LeMart.dto.UserDTO;
import com.LeMart.dto.UserProfileUpdateDTO;
import com.LeMart.model.User;
import com.LeMart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(convertToDTO(createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user.setId(id); // Ensure the ID is set
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(convertToDTO(updatedUser), HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserProfile(
        @PathVariable Long id,
        @RequestBody UserProfileUpdateDTO updateDTO) {
        
        User updatedUser = userService.updateUserProfile(id, updateDTO);
        return ResponseEntity.ok(convertToDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setStreetAddress(user.getStreetAddress());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState());
        userDTO.setZipCode(user.getZipCode());
        userDTO.setAdmin(user.isAdmin());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setStreetAddress(userDTO.getStreetAddress());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setZipCode(userDTO.getZipCode());
        user.setAdmin(userDTO.isAdmin());
        return user;
    }
}