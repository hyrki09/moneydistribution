package com.distribution.moneydistribution.domain.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    // 로그인 한 유저는 접근이 불가능하게 만들어야 함
    @GetMapping("")
    public String userJoinPage() {


        return "/register/register";
    }
}
