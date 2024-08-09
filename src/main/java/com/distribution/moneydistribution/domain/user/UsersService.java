package com.distribution.moneydistribution.domain.user;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
public class UsersService {


    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> findOne(String email) {
        return usersRepository.findByEmail(email);
    }

    public boolean isValidUser(String email, String password) {
        Optional<Users> users = findOne(email);

        return users.map(value -> value.getPassword().equals(password)).orElse(false);
    }
}
