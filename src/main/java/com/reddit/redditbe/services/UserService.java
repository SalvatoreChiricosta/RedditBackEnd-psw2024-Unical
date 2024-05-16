package com.reddit.redditbe.services;

import com.reddit.redditbe.models.User;
import com.reddit.redditbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFirstname(userDetails.getFirstname());
            user.setLastname(userDetails.getLastname());
            user.setBirthDate(userDetails.getBirthDate());
            user.setUserImage(userDetails.getUserImage());
            user.setDescription(userDetails.getDescription());
            return userRepository.save(user);
        }).orElseGet(() -> {
            userDetails.setId(id.intValue());
            return userRepository.save(userDetails);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}