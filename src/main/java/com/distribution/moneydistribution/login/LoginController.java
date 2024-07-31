package com.distribution.moneydistribution.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {



    @GetMapping("")
    public String login() {

        log.debug("login 통과");


        return "login/loginpage";
    }
}
