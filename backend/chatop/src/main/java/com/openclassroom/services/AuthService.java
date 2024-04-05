package com.openclassroom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
    
    
    public UserLoggedDTO me(Authentication principal){
    	UserModel userAuthorized = (UserModel) principal.getPrincipal();
        Integer id = userAuthorized.getId();
        UserModel user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user, UserLoggedDTO.class);
    }

}
