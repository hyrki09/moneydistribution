package com.distribution.moneydistribution.domain.main;

import lombok.extern.slf4j.Slf4j;
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
}
