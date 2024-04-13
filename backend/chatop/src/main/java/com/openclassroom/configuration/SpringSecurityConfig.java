package com.openclassroom.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	// Le filtre qui va permettre de récupérer le Token
	 public final JwtAuthenticationFilter jwtAuthenticationFilter;
	 public final AuthenticationProvider authenticationProvider;

	    public SpringSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
	    		AuthenticationProvider authenticationProvider) {
	    	
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	        this.authenticationProvider = authenticationProvider;
	    }
	    
	    

	@Bean
	/**
	 * La FilterChain qui est un des trois axes de sécurité de Spring Security
	 * @param http L'objet http courant
	 * @return Ce même objet http mais ayant reçu les paramètres de filtres.
	 * @throws Exception
	 */
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		String root_dir = System.getProperty("user.dir");
		String static_folder_path = root_dir+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static";
		String static_folder_path2 = LocationHelpers.STATIC_DIR;
		System.out.println("\nroot_dir:"+root_dir+"\nstatic_folder_path:"+LocationHelpers.STATIC_DIR+"\nstatic_folder_path2:"+static_folder_path2);
		return http
				.csrf(csrf -> csrf.disable())
				// Une API Rest est sans état (STATELESS) d'un point de vue de la session. c'est défini ici.
				.sessionManagement(session -> session
			    		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Les URLs / endpoints et dossiers authorisés pour les utilisateurs non-authentifiés. 
       .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                		LocationHelpers.REGISTER_URI, 
                		LocationHelpers.LOGIN_URI, 
                		
                		"images/**",
                		LocationHelpers.STATIC_URI,
                		LocationHelpers.STATIC_URI_RECURSIVE,
                		LocationHelpers.SWAGGER_UI_URI,
                		LocationHelpers.SWAGGER_API_URI
                		).permitAll()
                // Pour tout le reste il faut être authentifié
                .anyRequest().authenticated())
       .authenticationProvider(authenticationProvider)
       
        // Add JWT token filter
        .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();                	
	}	
}
