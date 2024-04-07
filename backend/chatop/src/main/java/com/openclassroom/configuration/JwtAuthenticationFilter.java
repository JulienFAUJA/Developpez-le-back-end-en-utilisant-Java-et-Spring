package com.openclassroom.configuration;



import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openclassroom.services.JWTokenService;
import com.openclassroom.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
@Lazy
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTokenService jwtService;
    private final UserService userService;


    public JwtAuthenticationFilter(JWTokenService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
             @NonNull HttpServletResponse response,
             @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("authHeader token:"+authHeader);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
        	System.out.println("ERREUR TOKEN:"+authHeader);
        	System.out.println("Response:"+response.getClass().toString());
            //filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);
        System.out.println("Extract email from header token:"+email);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userService.loadUserByUsername(email);
            System.out.println("userDetails:"+userDetails.toString());

            if(jwtService.isValid(token, email)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("authToken:"+authToken.toString());
            }
        }
        filterChain.doFilter(request, response);


    }
}

// eyJhbGciOiJIUzM4NCJ9.e30.OMZdVPkjchYPPNNJ7ZOA9rTTZP48jfUnshFeXmKk5rH_hsQmHfhXsU_bSxsWW83n
// eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0MjhAdGVzdC5jb20iLCJpYXQiOjE3MTI0ODYzNDQsImlzcyI6InNlbGYiLCJleHAiOjE3MTI1NzI3NDR9.pEI8oIK_Yfvo5sUYlBv8YkaTF8_n_VBO6zGPs4ag0mlnG49fbT7ega3lSZ1dVOF6

// {"alg":"HS384"}{}1—U>HÜ…ƒÏ4Ò{dà=­4Ù?#}Iì„W—˜©9¬á±	‡~ìSöÒÆÅ–óy
// {"alg":"HS384"}{"sub":"test28@test.com","iat":1712486344,"iss":"self","exp":1712572744}ñB<áé┐a¹Þµ┼ö³bFô¤þ²PNÙ1Å│åáÊigÅ_m>Ìü¡ÕIØ]Tßz

// eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0MzBAdGVzdC5jb20iLCJpYXQiOjE3MTI0ODc0NzUsImlzcyI6InNlbGYiLCJleHAiOjE3MTI1NzM4NzV9.GuLC6DTwkb9cy50bN-1syy8J0Suvo8l97TAPkf_1_h3eI12MF0u6BBQihfUAYspy
