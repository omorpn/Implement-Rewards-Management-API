package com.app.rewards_system.config;


import com.app.rewards_system.jwt.JwtFiltter;
import com.app.rewards_system.jwt.JwtToken;
import com.app.rewards_system.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class GeneralConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtToken jwtToken, UserDetailService userDetailService) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers("/api/token").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFiltter(jwtToken,userDetailService), AuthorizationFilter.class)
                .build();

    }


}
