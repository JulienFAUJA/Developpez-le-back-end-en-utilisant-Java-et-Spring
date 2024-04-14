package com.openclassroom.services.Interfaces;

import java.security.Principal;

import com.openclassroom.dto.TokenDTO;
import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;

public interface IAuthService {
		
	UserLoggedDTO me(Principal user);
	
	TokenDTO register(UserRegisterDTO request);
	
	TokenDTO authenticating(UserLoginDTO request);

}
