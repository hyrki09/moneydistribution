package com.distribution.moneydistribution.global.config;

import com.distribution.moneydistribution.domain.user.Users;
import com.distribution.moneydistribution.domain.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final UsersService usersService;

    @Autowired
    public MyUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersService.findOne(email).orElseThrow(() -> new UsernameNotFoundException("회원 없음"));

        return User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(String.valueOf(users.getRole()))
                .build();
    }
}
