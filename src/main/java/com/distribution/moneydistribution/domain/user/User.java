package com.distribution.moneydistribution.domain.user;

import com.distribution.moneydistribution.domain.BaseTimeEntity;
import com.distribution.moneydistribution.domain.transaction.FinancialTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role; // 권한 -> USER, ADMIN

    @OneToMany
    private List<FinancialTransaction> financialTransactionList = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "users")
//    private List<sharedContainer> sharedContainers = new ArrayList<>();


    public static User createUser(String email, String password, String name, String phoneNum, String nickname, int age) {
//        return new User(null, email, password, name, phoneNum, nickname, age, Role.USER);
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNum(phoneNum)
                .nickname(nickname)
                .age(age)
                .role(Role.USER)
                .build();

    }


}
