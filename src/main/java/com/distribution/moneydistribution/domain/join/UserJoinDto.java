package com.distribution.moneydistribution.domain.join;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserJoinDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String phoneNum;
    @NotNull
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
