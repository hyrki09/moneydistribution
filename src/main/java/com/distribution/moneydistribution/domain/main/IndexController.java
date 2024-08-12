package com.distribution.moneydistribution.domain.main;

import com.distribution.moneydistribution.global.config.anotation.AdminAuthorize;
import com.distribution.moneydistribution.global.config.anotation.UserAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping({"/", "/index"})
    public String main(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userId", user.getUsername());
        model.addAttribute("userRoles", user.getAuthorities());

        return "/main/index";
    }

    // TEST
    @RequestMapping("/test/admin")
    @AdminAuthorize
    public String adminPage() {
        return "/main/admin";
    }

    @RequestMapping("/test/user")
    @UserAuthorize
    public String userPage() {
        return "/main/main";
    }


}
