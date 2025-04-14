package rlatnfla.securitytest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rlatnfla.securitytest.filter.LoginFilter;
import rlatnfla.securitytest.service.LoginUserDetailsService;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginUserDetailsService loginUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(loginUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic(auth -> auth.disable());

        http.csrf(auth -> auth.disable());

        http.formLogin(auth -> auth.disable());
        
        // public 요청 경로 설정
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/sign-up", "/auth/login").permitAll()
            .anyRequest().authenticated());

        // 무상태 세션 사용
        http.sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 커스텀 로그인 필터 등록
        http.addFilterBefore(new LoginFilter(authenticationManager()),
            UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}