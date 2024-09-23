package com.distribution.moneydistribution.domain.security;

import com.distribution.moneydistribution.domain.user.MyUserDetails;
import com.distribution.moneydistribution.domain.user.User;
import com.distribution.moneydistribution.domain.user.UserService;
import org.apache.catalina.util.StringUtil;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DefaultSecurityService implements SecurityService{

    private static final String EMPTY = "";
    private UserService userService;

    @Override
    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null)return null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((MyUserDetails) principal).getUser();
        }else {
            return null;
        }
    }

    @Override
    public Long getCurrentUserId() {
        User user = getCurrentUser();

        if (user != null) {
            return user.getId();
        }else{
            return null;
        }
    }

    @Override
    public String getCurrentUserEmail() {
        User user = getCurrentUser();

        if (user != null) {
            return user.getEmail();
        }else{
            return EMPTY;
        }
    }

    @Override
    public String getCurrentUserNickName() {
        User user = getCurrentUser();

        if (user != null) {
            return user.getNickname();
        }else{
            return EMPTY;
        }
    }

    @Override
    public String isVip() {
        return null;
    }
}
