package com.distribution.moneydistribution.domain.join;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Slf4j
@RequestMapping("/join")
public class JoinController {

    private JoinService joinService;

    private final static String REGEXP_EMAIL = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    // 로그인 한 유저는 접근이 불가능하게 만들어야 함
    @GetMapping("")
    public String userJoinPage() {

        return "/join/register";
    }

    @PostMapping("")
    public String joinProcess(@Valid UserJoinDto joinDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("joinDTO : {}", joinDto.toString());

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                log.warn("field : {}", fieldError.getField());
                log.warn("message : {}", message);

                sb.append("message : ").append(message);
            });
            redirectAttributes.addFlashAttribute("errorMessage", sb.toString());

            return "redirect:/join";
        }
        try {
            joinService.joinUser(joinDto);
        }
        catch (RuntimeException e){
            log.error("회원가입 ERROR : {}",e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "회원가입에 실패했습니다: " + e.getMessage());
            return "redirect:/join";
        }

        return "redirect:/login";
    }

    @PostMapping("/duplicateEmails")
    @ResponseBody
    public ResponseEntity<String> checkDuplicateEmail(@RequestParam String email){

        Pattern pattern = Pattern.compile(REGEXP_EMAIL);
        Matcher matcher = pattern.matcher(email);

//        boolean result = false;

        log.info("matcher : {}", matcher.matches());
        // 빈값 or email 형태로 안오면 return
        if (!matcher.matches()) {
            return ResponseEntity.badRequest().body("이메일 형태로 입력해주세요.");
        }

        if (joinService.duplicateCheckEmail(email)) {
            log.info("2.email : {}", email);
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");

        }


//        return new ResponseEntity<>(result, HttpStatus.OK);
        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }
}
