package com.distribution.moneydistribution.domain.security;

import com.distribution.moneydistribution.domain.user.MyUserDetails;
import com.distribution.moneydistribution.domain.user.MyUserDetailsService;
import com.distribution.moneydistribution.domain.user.UserService;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Slf4j
//@WithMockUser(username = "joke" , roles = "USER")
class DefaultSecurityServiceTest {

    @Autowired
    UserService userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultSecurityServiceTest.class);
//    String name = SecurityContextHolder.getContext().getAuthentication().getName();
//    //
////        // 세션 현재 사용자 role
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    //
//    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//    GrantedAuthority auth = iterator.next();
//    String role = auth.getAuthority();

//    @Test
//    @WithUserDetails(value = "joke", userDetailsServiceBeanName  = "MyUserDetailsService")
//    public void getCurrentUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        Object principal = authentication.getPrincipal();
//        LOGGER.info("toString : {}", principal instanceof UserDetails);
//        LOGGER.info("getUsername : {}", ((MyUserDetails)principal).getUsername());
//        LOGGER.info("getAuthorities : {}", ((MyUserDetails)principal).getAuthorities());
//
//
//        LOGGER.info("하이용");
//
//
//
//    }

    @Test
    @WithUserDetails(value = "joke", userDetailsServiceBeanName = "myUserDetailsService")
    public void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();

//
        Object principal = authentication.getPrincipal();
        LOGGER.info("toString : {}", principal instanceof UserDetails);
        LOGGER.info("getUsername : {}", ((MyUserDetails)principal).getUsername());
        LOGGER.info("getAuthorities : {}", ((MyUserDetails)principal).getAuthorities());


        LOGGER.info("하이용");
    }



}