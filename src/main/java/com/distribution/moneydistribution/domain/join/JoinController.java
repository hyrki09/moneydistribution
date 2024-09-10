package com.distribution.moneydistribution.domain.join;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class JoinController {

    private JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    // 로그인 한 유저는 접근이 불가능하게 만들어야 함
    @GetMapping("join")
    public String userJoinPage() {
        return "/join/register";
    }

    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<String> joinProcess(UserJoinDto joinDto){

        return joinService.joinUser(joinDto);

    }
}
