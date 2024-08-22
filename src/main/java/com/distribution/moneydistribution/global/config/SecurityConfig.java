package com.distribution.moneydistribution.global.config;

import com.distribution.moneydistribution.domain.login.LoginFilter;
import com.distribution.moneydistribution.global.jwt.JWTUtil;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // AuthenticationManager가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입함
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // form 로그인 형식
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
////                .formLogin(formLogin -> formLogin.disable())
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.disable())
//                    .authorizeHttpRequests(request -> request
//                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                            .requestMatchers("/status", "/css/**","/js/**","/register","/auth/register").permitAll()
//                            .anyRequest() // 어떤 요청이든 인증필요
//                            .authenticated() // 어떤 요청이라도 인증 필요
//                    )
//                    .formLogin(login -> login   // form 방식 로그인 사용
//                            .loginPage("/login") // 커스텀 로그인 페이지 지정
////                            .loginProcessingUrl("/login/") // 지정하지 않으면 자동으로 post /login으로 진행
//                            .usernameParameter("email") // 아이디
//                            .passwordParameter("password") // 비밀번호
//                            .defaultSuccessUrl("/",true) // 성공 시 index 페이지로 이동
//                            .permitAll()
//                    )
//                    .logout(withDefaults());  // 로그아웃은 기본 설정으로 (/logout으로 인증해제
//        return http.build();
//    }

    // JWT 로그인 방식
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((auth) -> auth.disable())
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())
                // 경로별 인가 작업
                .authorizeHttpRequests((auth) -> auth
                                                .requestMatchers("/login", "/","/join").permitAll()
                                                .requestMatchers("/test/admin").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                // 필터 추가  LoginFilter()를 인자로 받음 (AuthenticationManager() 메서드에  authenticationConfiguration 객체를 넣어야 함) 따라서 등록필요
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class)
                // 세션 설정
                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
