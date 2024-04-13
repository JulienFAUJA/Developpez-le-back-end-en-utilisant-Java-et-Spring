package com.openclassroom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.MessageResponseDTO;
import com.openclassroom.dto.TokenDTO;
import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;
import com.openclassroom.services.Interfaces.IAuthService;

@Service
public class AuthService implements IAuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	 private final PasswordEncoder passwordEncoder;
	 private final JWTokenService jwtService;


    private final AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, JWTokenService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    
    public UserModel getCurrentUser(String label){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        return userModel;
    }
    
    public Authentication setCurrentUser(String email, String password){
    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    			email,
    			password
        );
    	SecurityContext contextHolder = SecurityContextHolder.getContext();
    	Authentication authentication = contextHolder.getAuthentication();
    	SecurityContextHolder.getContext().setAuthentication(authToken);
    	
        System.out.println("contextHolder:"+contextHolder.toString());
        System.out.println("authentication - Before:"+authentication.toString());
        authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication - After:"+authentication.toString());
        System.out.println("authToken:"+authToken.toString());
        Authentication userModel = authentication;
        return userModel;
    }
    
    public ResponseEntity<?> me(){
    	UserModel UserCurrent = getCurrentUser("/Me");
        String email = UserCurrent.getUsername();
        UserModel user = this.userRepository.findByEmail(email).orElse(null);
        if (user==null) {
        	MessageResponseDTO errorResponse = new MessageResponseDTO("Unauthorized: ");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
        UserLoggedDTO userLoggedDto = modelMapper.map(user, UserLoggedDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userLoggedDto);
    }
    
    
    public String register(UserRegisterDTO request) {
    	UserModel user = new UserModel(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
    	String jwt = jwtService.generateToken(request.getEmail());
        // vérifie si existe déjà
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
        	System.out.println("User already exist:"+request.toString());
        }
        else {
        	user = userRepository.save(user);
        }
        System.out.println("jwt:"+jwt);
        return jwt;

    }

    public ResponseEntity<?> authenticating(UserLoginDTO request) {
    	    	Authentication authentication;
    	    try {
    	    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                );
    	    	authentication = authenticationManager.authenticate(authToken);
    	    }
    	    catch (AuthenticationException e) {
    	        // Si une exception d'authentification se produit (par exemple, erreur 401), renvoie un ResponseEntity avec un code de statut 401 Unauthorized
    	    	MessageResponseDTO errorResponse = new MessageResponseDTO("Unauthorized: " + e.getMessage());
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    	    }
    	    SecurityContextHolder.getContext().setAuthentication(authentication);
    	    UserModel user = (UserModel)authentication.getPrincipal();
    	    String email = user.getUsername();
    	    String jwt = jwtService.generateToken(email);
    	    System.out.println("jwt:"+jwt);
    	    TokenDTO token = new TokenDTO(jwt);
    	    return ResponseEntity.ok(token);
    	    	
    	   
    	    
       


        

    }
  
   

}
