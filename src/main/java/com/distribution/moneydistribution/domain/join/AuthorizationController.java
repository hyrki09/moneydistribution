package com.distribution.moneydistribution.domain.join;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthorizationController {
    private final JoinService joinService;

    @Autowired
    public AuthorizationController(JoinService joinService) {
        this.joinService = joinService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> join(@RequestBody UserJoinDto dto) {
//        log.info("dto : {}", dto.getName());
//        try {
//            // 변형해야함
//            Long id = joinService.joinUser(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getPhoneNum(), dto.getNickName(), dto.getAge());
////            Long id = registerService.join(dto.getEmail(), dto.getPassword());
//            log.info("id : {}", id);
//
//            return ResponseEntity.ok("join success");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
