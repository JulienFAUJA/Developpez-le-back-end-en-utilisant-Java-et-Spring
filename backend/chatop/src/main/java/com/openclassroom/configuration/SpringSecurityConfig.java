package com.openclassroom.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.openclassroom.repositories.UserRepository;
import com.openclassroom.services.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Value("${jwt.secret}")
	private String jwtKey; 
	
	 public final JwtAuthenticationFilter jwtAuthenticationFilter;

	    public SpringSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session
			    		   
			    		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS))//.maximumSessions(1))
       .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register/").permitAll()
                .requestMatchers("/api/auth/login/").permitAll()
                .anyRequest().authenticated())//.userDetailsService(userService)
       
           
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//	 @Bean
//	 public JwtDecoder jwtDecoder() {
//		 SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length,"RSA");
//			return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
//	 }
//
//	 @Bean
//		public JwtEncoder jwtEncoder() {
//			return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
//		}
//	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	

	

	
}

//.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

