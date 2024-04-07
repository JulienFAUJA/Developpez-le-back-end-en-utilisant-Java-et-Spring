package com.openclassroom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    
    public UserLoggedDTO me(Authentication principal){
    	System.out.println("principal:"+principal);
    	UserModel userAuthorized = (UserModel) principal.getPrincipal();
        Integer id = userAuthorized.getId();
        UserModel user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user, UserLoggedDTO.class);
    }
    
    
    public String register(UserRegisterDTO request) {

        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByUsername(request.getEmail()).isPresent()) {
            return "User already exist";
        }

        UserModel user = new UserModel(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
        
        user = userRepository.save(user);
        String jwt = jwtService.generateToken(request.getEmail());
        return jwt;

    }

    public String authenticating(UserLoginDTO request) {
    	    	System.out.println("request:"+request.toString());
    	    
    	    	Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        passwordEncoder.encode(request.getPassword())
                )
        );
    	    	System.out.println("authentication:"+authentication.toString());
        UserModel user = (UserModel)authentication.getPrincipal();
        System.out.println("principal:"+user.toString());
        //userRepository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user.getUsername());
       


        return jwt;

    }
  
   

}
