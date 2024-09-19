package com.distribution.moneydistribution.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
//    private final UsersService usersService;

    private final UsersRepository usersRepository;

    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername_username : {} ", username);
        Users users = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("회원 없음"));



        return new MyUserDetails(users);
    }
}
