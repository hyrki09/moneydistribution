package com.distribution.moneydistribution.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findOne(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isValidUser(String email, String password) {
        Optional<User> users = findOne(email);

        return users.map(value -> value.getPassword().equals(password)).orElse(false);
    }
}
