package com.distribution.moneydistribution.domain.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthorizationController {
    private final RegisterService registerService;

    @Autowired
    public AuthorizationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> join(@RequestBody UserJoinDto dto) {
        log.info("dto : {}", dto.getName());
        try {
            // 변형해야함
            Long id = registerService.registerUser(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getPhoneNum(), dto.getNickName(), dto.getAge());
//            Long id = registerService.join(dto.getEmail(), dto.getPassword());
            log.info("id : {}", id);

            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
