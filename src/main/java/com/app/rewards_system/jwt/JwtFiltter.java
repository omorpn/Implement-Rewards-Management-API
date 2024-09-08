package com.app.rewards_system.jwt;

import com.app.rewards_system.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFiltter extends OncePerRequestFilter {
    
    private final JwtToken jwtToken;
    private final UserDetailService userDetailService;

    public JwtFiltter(JwtToken jwtToken, UserDetailService userDetailService) {
        this.jwtToken = jwtToken;

        this.userDetailService = userDetailService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        
        //Check to Bearer Token
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);

            boolean isValid = jwtToken.validateToken(token);


            if (isValid) {
                String customerId = jwtToken.getSubject(token);

                UserDetails user = userDetailService.loadUserByUsername(customerId);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
            
                    

        }
        filterChain.doFilter(request, response);
    }
}
