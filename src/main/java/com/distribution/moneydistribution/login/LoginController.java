package com.distribution.moneydistribution.login;

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

        // 현재 로그인 세션이 남아있는지 확인 필요



        return "login/loginpage";
    }

    @PostMapping("")
    public String login(
            @RequestParam String id,
            @RequestParam String password
    ){
        log.info("로그인 시도");
        log.info("id : {}", id);
        log.info("password : {}", password);


        return "login/loginpage";
    }
}
