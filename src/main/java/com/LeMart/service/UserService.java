package com.LeMart.service;

import com.LeMart.dto.UserProfileUpdateDTO;
import com.LeMart.exception.UserNotFoundException;
import com.LeMart.model.User;
import com.LeMart.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }
    
    public User updateUserProfile(Long id, UserProfileUpdateDTO updateDTO) {
        User existingUser = findUserById(id);
        
        if (updateDTO.getUsername() != null && !updateDTO.getUsername().isBlank()) {
            existingUser.setUsername(updateDTO.getUsername());
        }
        if (updateDTO.getEmail() != null && !updateDTO.getEmail().isBlank()) {
            existingUser.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getStreetAddress() != null) {
            existingUser.setStreetAddress(updateDTO.getStreetAddress());
        }
        if (updateDTO.getCity() != null && !updateDTO.getCity().isBlank()) {
            existingUser.setCity(updateDTO.getCity());
        }
        if (updateDTO.getState() != null && !updateDTO.getState().isBlank()) {
            existingUser.setState(updateDTO.getState());
        }
        if (updateDTO.getZipCode() != null && !updateDTO.getZipCode().isBlank()) {
            existingUser.setZipCode(updateDTO.getZipCode());
        }
        
        return userRepo.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User findUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " does not exist"));
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " does not exist"));
    }
}