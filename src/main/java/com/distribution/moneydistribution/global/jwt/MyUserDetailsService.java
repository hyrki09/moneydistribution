package com.distribution.moneydistribution.global.jwt;

import com.distribution.moneydistribution.domain.user.Users;
import com.distribution.moneydistribution.domain.user.UsersRepository;
import com.distribution.moneydistribution.domain.user.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
//    private final UsersService usersService;

    private final UsersRepository usersRepository;

    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Users users = usersService.findOne(email).orElseThrow(() -> new UsernameNot    FoundException("회원 없음"));

        log.info("loadUserByUsername");
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("회원 없음"));

        return new MyUserDetails(users);

//        return User.builder()
//                .username(users.getEmail())
//                .password(users.getPassword())
//                .roles(String.valueOf(users.getRole()))
//                .build();
    }
}
