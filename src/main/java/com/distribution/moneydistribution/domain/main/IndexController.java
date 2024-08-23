package com.distribution.moneydistribution.domain.main;

import com.distribution.moneydistribution.global.config.anotation.AdminAuthorize;
import com.distribution.moneydistribution.global.config.anotation.UserAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Controller
@ResponseBody
public class IndexController {


    @RequestMapping({"/"})
    public String main() {
        // 현재 사용자 아이디
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        // 세션 현재 사용자 role
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();


        return "main IndexController : " + name + role;
    }

    // TEST
    @RequestMapping("/test/admin")
    public String adminPage() {
        return "Admin Controller";
    }

    @RequestMapping("/test/user")
    public String userPage() {
        return "Main Controller";
    }


}
