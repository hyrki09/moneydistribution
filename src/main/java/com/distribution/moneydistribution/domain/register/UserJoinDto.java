package com.distribution.moneydistribution.domain.register;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserJoinDto {
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private String nickName;
    private int age;
}
