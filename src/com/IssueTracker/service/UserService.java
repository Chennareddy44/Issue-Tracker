package com.IssueTracker.service;

import com.IssueTracker.models.User;
import com.IssueTracker.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public User createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
