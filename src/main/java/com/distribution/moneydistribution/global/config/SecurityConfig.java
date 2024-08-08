package com.distribution.moneydistribution.global.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                    .authorizeHttpRequests(request -> request
                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                            .anyRequest().authenticated() // 어떤 요청이라도 인증 필요
                    )
                    .formLogin(login -> login   // form 방식 로그인 사용
                            .loginPage("/login") // 커스텀 로그인 페이지 지정
//                            .loginProcessingUrl("/login/")
                            .defaultSuccessUrl("/",true) // 성공 시 index 페이지로 이동
                            .permitAll()
                    )
                    .logout(withDefaults());  // 로그아웃은 기본 설정으로 (/logout으로 인증해제
        return http.build();
    }

}
