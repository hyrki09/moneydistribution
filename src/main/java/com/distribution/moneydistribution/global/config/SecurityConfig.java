package com.distribution.moneydistribution.global.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                    .authorizeHttpRequests(request -> request
                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                            .requestMatchers("/status", "/css/**","/js/**","/register","/auth/register").permitAll()
                            .anyRequest() // 어떤 요청이든 인증필요
                            .authenticated() // 어떤 요청이라도 인증 필요
                    )
                    .formLogin(login -> login   // form 방식 로그인 사용
                            .loginPage("/login") // 커스텀 로그인 페이지 지정
//                            .loginProcessingUrl("/login/") // 지정하지 않으면 자동으로 post /login으로 진행
                            .usernameParameter("email") // 아이디
                            .passwordParameter("password") // 비밀번호
                            .defaultSuccessUrl("/",true) // 성공 시 index 페이지로 이동
                            .permitAll()
                    )
                    .logout(withDefaults());  // 로그아웃은 기본 설정으로 (/logout으로 인증해제
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
