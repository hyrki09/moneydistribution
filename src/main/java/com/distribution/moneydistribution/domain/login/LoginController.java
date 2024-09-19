package com.distribution.moneydistribution.domain.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @GetMapping("")
    public String loginPage() {
        log.info("login 통과");

        return "login/loginpage";
    }


}
