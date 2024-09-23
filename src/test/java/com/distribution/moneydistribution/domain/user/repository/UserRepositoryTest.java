package com.distribution.moneydistribution.domain.user.repository;

import com.distribution.moneydistribution.domain.user.Role;
import com.distribution.moneydistribution.domain.user.User;
import com.distribution.moneydistribution.domain.user.UserRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void save() {
        //given
        User user = User.builder().name("테스트김").password("1234").email("test@naver.com").age(27).phoneNum("01012345678").nickname("nick").role(String.valueOf(Role.USER)).build();

        // when
        User saveUser = userRepository.save(user);

        //then
        User findUser = userRepository.findByEmail(saveUser.getEmail()).orElseThrow(() -> new RuntimeException("저장된 회원 없음"));

        Assertions.assertThat(findUser).isSameAs(saveUser);
        Assertions.assertThat(findUser).isSameAs(user);
    }

    @Test
    public void non_register() throws Exception {
        //given
        User user = User.builder()
                            .name("이름")
                            .password("1234")
                            .age(27)
                            .email("test@naver.com")
                            .phoneNum("01012345678")
//                            .nickname("nick")
                            .role(String.valueOf(Role.USER))
                            .build();

        // when, then
        assertThrows(Exception.class, () -> userRepository.save(user));
//        Users save = userRepository.save(users);
    }

    @Test
    public void duplicateId() throws Exception {
        //given
        User user1 = User.builder()
                .name("이름1")
                .password("12342")
                .age(27)
                .email("test1@naver.com")
                .phoneNum("01012345671")
                .nickname("nick")
                .role(String.valueOf(Role.USER))
                .build();
        User user2 = User.builder()
                .name("이름2")
                .password("1234")
                .age(27)
                .email("test2@naver.com")
                .phoneNum("01012345672")
                .nickname("nick")
                .role(String.valueOf(Role.USER))
                .build();

        userRepository.save(user1);
        em.clear();

        //when, then
        assertThrows(Exception.class, () -> userRepository.save(user2));
    }

    @Test
    public void modify() throws Exception {
        //given
        User user1 = User.builder()
                .name("이름1")
                .password("12342")
                .age(27)
                .email("test1@naver.com")
                .phoneNum("01012345671")
                .nickname("nick")
                .role(String.valueOf(Role.USER))
                .build();
        userRepository.save(user1);
        em.clear();


        String updatePassword = "updatePW";
        String updateName = "updateName";
        int updateAge = 31;
        String updateNickName = "updateNickName";


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //when
        User findByIdusers = userRepository.findById(user1.getId()).orElseThrow(() -> new Exception());
//        findByIdusers.updateAge(updateAge);
//        findByIdusers.updateName(updateName);
//        findByIdusers.updatePassword(passwordEncoder, updatePassword);
//        findByIdusers.updateNickName(updateNickName);

        //then
        User updateUser = userRepository.findById(findByIdusers.getId()).orElseThrow(() -> new Exception());

        Assertions.assertThat(updateUser).isSameAs(findByIdusers);
        Assertions.assertThat(passwordEncoder.matches(updatePassword, updateUser.getPassword())).isTrue();
        Assertions.assertThat(updateUser.getName()).isEqualTo(updateName);
        Assertions.assertThat(updateUser.getName()).isNotEqualTo(user1.getName());

    }

    @Test
    public void delete() throws Exception {
        //given
        User user1 = User.builder()
                .name("이름1")
                .password("12342")
                .age(27)
                .email("test1@naver.com")
                .phoneNum("01012345671")
                .nickname("nick")
                .role(String.valueOf(Role.USER))
                .build();

        userRepository.save(user1);
        em.clear();




        userRepository.delete(user1);
        em.flush();

        //then
        assertThrows(Exception.class, () -> userRepository.findById(user1.getId()).orElseThrow(Exception::new));
    }

    @Test
    public void existByEmail_SUCCESS() throws Exception {
        //given
        String email = "wns@naver.com";
        User user1 = User.builder()
                .name("이름1")
                .password("12342")
                .age(27)
                .email(email)
                .phoneNum("01012345671")
                .nickname("nick")
                .role(String.valueOf(Role.USER))
                .build();
        userRepository.save(user1);
        em.clear();

        Assertions.assertThat(userRepository.existsByEmail(email)).isTrue();
        Assertions.assertThat(userRepository.existsByEmail(email+"qwer")).isFalse();



    }

    @Test
    public void findByEmail() throws Exception {
        //given
        String email = "wns@naver.com";
        User user1 = User.builder()
                .name("이름1")
                .password("12342")
                .age(27)
                .email(email)
                .phoneNum("01012345671")
                .nickname("nick")
                .role(String.valueOf(Role.USER))
                .build();
        userRepository.save(user1);
        em.clear();

        Assertions.assertThat(userRepository.findByEmail(email).get().getName()).isEqualTo(user1.getName());
        Assertions.assertThat(userRepository.findByEmail(email).get().getNickname()).isEqualTo(user1.getNickname());
        Assertions.assertThat(userRepository.findByEmail(email).get().getPhoneNum()).isEqualTo(user1.getPhoneNum());
        assertThrows(Exception.class, () -> userRepository.findByEmail(email+"123123").orElseThrow(() -> new Exception()));
    }



    @AfterEach
    public void after() {
        em.clear();
    }

}