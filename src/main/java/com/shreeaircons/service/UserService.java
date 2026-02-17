package com.shreeaircons.service;

import com.shreeaircons.entity.User;
import com.shreeaircons.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public User createUser(User user) {
        // Hash password before saving (use proper hashing in production)
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            User loggedInUser = user.get();
            loggedInUser.setLastLogin(LocalDateTime.now());
            userRepository.save(loggedInUser);
            return Optional.of(loggedInUser);
        }
        return Optional.empty();
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(userDetails.getFullName());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            user.setIsActive(userDetails.getIsActive());
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
