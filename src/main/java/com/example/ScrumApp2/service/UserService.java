package com.example.ScrumApp2.service;

import com.example.ScrumApp2.model.User;
import com.example.ScrumApp2.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public List<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = iUserRepository.findById(id);
        return userOptional.orElse(null);
    }

    public User createUser(User user) {
        return iUserRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = iUserRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setTasks(updatedUser.getTasks());
            existingUser.setProjects(updatedUser.getProjects());
            return iUserRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public String deleteUser(Long id) {
        iUserRepository.deleteById(id);
        return "User deleted successfully";
    }
}
