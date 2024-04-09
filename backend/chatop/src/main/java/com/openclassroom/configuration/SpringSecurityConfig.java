package com.openclassroom.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.openclassroom.services.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{
	
	@Autowired
	private UserService userService;

	

	
	 public final JwtAuthenticationFilter jwtAuthenticationFilter;
	 public final AuthenticationProvider authenticationProvider;

	    public SpringSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
	    		AuthenticationProvider authenticationProvider) {
	    	
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	        this.authenticationProvider = authenticationProvider;
	    }
	    
	    

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session
			    		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
//                .requestMatchers("/auth/me").authenticated()
//                .requestMatchers("/api/rentals").authenticated()
//                .requestMatchers("/api/users/**").authenticated()
//                .requestMatchers("/api/messages/**").authenticated()
                .anyRequest().authenticated())
       .authenticationProvider(authenticationProvider)
       
        // Add JWT token filter
        .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        
        
        .build();                
		
	}
	
	
	
	


	
	

	

	
}

//.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//.userDetailsService(userService).cors(Customizer.withDefaults())

// .anyRequest().authenticated()
