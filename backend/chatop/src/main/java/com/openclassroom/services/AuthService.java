package com.openclassroom.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;

@Service
public class AuthService {
	
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
        System.out.println("authentication:"+label+authentication.toString());
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
    
    public UserLoggedDTO me(){
    	UserModel UserCurrent = getCurrentUser("/Me");
    	System.out.println("UserCurrent:"+UserCurrent.toString());
        String email = UserCurrent.getUsername();
        UserModel user = this.userRepository.findByUsername(email).orElse(null);
        UserLoggedDTO userLoggedDto = modelMapper.map(user, UserLoggedDTO.class);
        System.out.println("UserModel byEmail:"+user.toString()+"UserCurrent:"+UserCurrent.toString()+"\nuserLoggedDto:"+userLoggedDto.toString());
        return userLoggedDto;
    }
    
    
    public String register(UserRegisterDTO request) {
    	UserModel user = new UserModel(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
    	String jwt = jwtService.generateToken(request.getEmail());
        // vérifie si existe déjà
        if(userRepository.findByUsername(request.getEmail()).isPresent()) {
        	System.out.println("User already exist:"+request.toString());
        }
        else {
        	user = userRepository.save(user);
        }
        System.out.println("jwt:"+jwt);
        return jwt;

    }

    public String authenticating(UserLoginDTO request) {
    	    	System.out.println("request:"+request.toString());
    	    	Authentication authentication;
    	    try {
    	    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                );
    	    	authentication = authenticationManager.authenticate(authToken);
    	    	SecurityContextHolder.getContext().setAuthentication(authToken);
    	    	
    	    	System.out.println("authToken:"+authToken.toString());
    	    	
    	    	System.out.println("authentication:"+authentication.toString());
    	    	System.out.println("getContext:"+SecurityContextHolder.getContext());
    	    }
    	    catch(Exception ex) {
    	    	System.out.println("[Exception][AuthService][authenticating]:"+ex.getMessage());
    	    	return ex.getMessage();
    	    }
    	    SecurityContextHolder.getContext().setAuthentication(authentication);
    	    UserModel user = (UserModel)authentication.getPrincipal();
    	    String email = user.getUsername();
    	    String jwt = jwtService.generateToken(email);
    	    System.out.println("jwt:"+jwt);
    	    System.out.println("userModel:"+user.toString());
    	    return jwt;
    	    	
        
       


        

    }
  
   

}
