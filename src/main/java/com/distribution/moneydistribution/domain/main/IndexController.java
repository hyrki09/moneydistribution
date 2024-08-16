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
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
//    @AdminAuthorize
    public String adminPage() {
        return "Admin Controller";
//        return "/main/admin";
    }

    @RequestMapping("/test/user")
    @ResponseBody
//    @UserAuthorize
    public String userPage() {
        return "Main Controller";
//        return "/main/main";
    }


}
