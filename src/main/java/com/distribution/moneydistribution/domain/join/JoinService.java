package com.distribution.moneydistribution.domain.join;

import com.distribution.moneydistribution.domain.user.Users;
import com.distribution.moneydistribution.domain.user.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    @Autowired
    public JoinService(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<String> joinUser(UserJoinDto joinDto) {
        String email = joinDto.getEmail();
        String password = joinDto.getPassword();
        String name = joinDto.getName();
        String phoneNum = joinDto.getPhoneNum();
        String nickName = joinDto.getNickName();
        int age = joinDto.getAge();

        boolean isExistEmail = usersRepository.existsByEmail(email);
        boolean isExistNickName = usersRepository.existsByNickname(nickName);


        // 오류 발생 시켜야함
        if (isExistEmail) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디가 이미 존재합니다.");
        }
        if (isExistNickName) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 닉네임 입니다.");
        }

        Users user = Users.createUser(email, passwordEncoder.encode(password), name, phoneNum, nickName, age);

        usersRepository.save(user);
        return ResponseEntity.ok("회원가입 성공!");
    }


}
