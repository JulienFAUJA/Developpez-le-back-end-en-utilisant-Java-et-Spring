package com.openclassroom.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.openclassroom.services.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{
	
	@Autowired
	//@Lazy
	private UserService userService;

	

	
	 public final JwtAuthenticationFilter jwtAuthenticationFilter;

	    public SpringSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    }
	    
	    

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				
				.sessionManagement(session -> session
			    		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS))//.maximumSessions(1))
       .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register").anonymous()
                .requestMatchers("/auth/login/**").anonymous()
                
                .requestMatchers("/auth/me").authenticated()
                .requestMatchers("/api/rentals").authenticated()
                .requestMatchers("/api/rentals/**").authenticated()
                .requestMatchers("/api/messages/**").authenticated()
                .requestMatchers("/api/users/**").authenticated()
                ).userDetailsService(userService).cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
       
       // .anyRequest().authenticated()
       //.authenticationManager(authenticationManager)
       
        // Add JWT token filter
        .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        
        
        .build();                
	}
	
//	@Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }
	
	@Bean
	@Lazy
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	

	

	
}

//.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

