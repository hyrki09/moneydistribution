package com.distribution.moneydistribution.domain.join;

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

    @Override
    public String toString() {
        return "UserJoinDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                '}';
    }
}
