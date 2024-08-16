package com.distribution.moneydistribution.domain.register;

import com.distribution.moneydistribution.domain.user.Users;
import com.distribution.moneydistribution.domain.user.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void joinUser(UserJoinDto joinDto) {
        String email = joinDto.getEmail();
        String password = joinDto.getPassword();
        String name = joinDto.getName();
        String phoneNum = joinDto.getPhoneNum();
        String nickName = joinDto.getNickName();
        int age = joinDto.getAge();

        boolean isExist = usersRepository.existsByEmail(email);

        if (isExist) {
            return;
        }

        Users user = Users.createUser(email, passwordEncoder.encode(password), name, phoneNum, nickName, age);

        usersRepository.save(user);
    }




//    public Long joinUser(String email, String password, String name, String phoneNum, String nickname, int age){
//        String encode = passwordEncoder.encode(password);
//        log.info("encode : {} ", encode);
//        Users users = Users.createUser(email,encode, name, phoneNum, nickname, age);
//        validateDuplicateUser(users);
//        log.info("여기가 문제임1");
//        usersRepository.save(users);
//        log.info("여기가 문제임2");
//        return users.getId();
//    }


//    public Long join(String email, String password){
//
//        Users users = Users.createUser(email, passwordEncoder.encode(password));
//        validateDuplicateUser(users);
//        usersRepository.save(users);
//        return users.getId();
//    }

//    private void validateDuplicateUser(Users users) {
//        usersRepository.findByEmail(users.getEmail()).ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });
//    }
}
