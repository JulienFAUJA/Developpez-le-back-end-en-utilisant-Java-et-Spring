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
    private UsernamePasswordAuthenticationToken authToken=null;


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
        System.out.println("\n\nauthHeader token:"+authHeader);
        System.out.println("Response:"+response.getStatus());
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
        	System.out.println("ERREUR TOKEN:"+authHeader);
        	System.out.println("Response:"+response.getStatus());
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);
        System.out.println("Extract email from header token:"+email);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userService.loadUserByUsername(email);
            System.out.println("userDetails:"+userDetails.toString());

            if(jwtService.isValid(token, email) && (authToken==null || authToken.isAuthenticated()==false)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), userDetails.getPassword()
                );
            }
            if(authToken!=null) {
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
