package com.openclassroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.UserDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;
import com.openclassroom.services.Interfaces.IUserService;

@Service
public class UserService implements UserDetailsService, IUserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	


    private UserModel convertToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, UserModel.class);
    }
    
    private UserDTO convertToDTO(UserModel user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    private UserModel convertRegisterToEntity(UserRegisterDTO userRegisterDTO) {
        return modelMapper.map(userRegisterDTO, UserModel.class);
    }
    
    private UserRegisterDTO convertToRegisterDTO(UserModel user) {
        return modelMapper.map(user, UserRegisterDTO.class);
    }
    
    
    private UserModel convertLoginToEntity(UserLoginDTO userLoginDTO) {
        return modelMapper.map(userLoginDTO, UserModel.class);
    }
    
    private UserLoginDTO convertToLoginDTO(UserModel user) {
        return modelMapper.map(user, UserLoginDTO.class);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    
    
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
    
//    
//	public Iterable<UserDTO> getUsers(){
//		List<UserDTO> users = new ArrayList<>();
//		this.userRepository.findAll().forEach(u -> users.add(this.convertToDTO(u)));
//		return users;
//	}
	
	
	public UserDTO getUserById(Integer id){
		Optional<UserModel> user = this.userRepository.findById(id);
		System.out.println("UserService -> user.orElseThrow():"+user.orElseThrow());
		System.out.println("UserService -> convertToDTO(user.orElseThrow()):"+convertToDTO(user.orElseThrow()));
		return convertToDTO(user.orElseThrow());
	}
	
//	
//	
//	public UserModel registerUser(UserRegisterDTO userRegisterDTO) {
//		System.out.println("userRegisterDTO:"+userRegisterDTO.toString());
//		String password_hash = BCrypt.hashpw(userRegisterDTO.getPassword(), BCrypt.gensalt());
//		userRegisterDTO.setPassword(password_hash);
//		userRegisterDTO.setCreated_atNow();
//		UserModel userCreated = convertRegisterToEntity(userRegisterDTO);
//    	userRepository.save(userCreated);
//    	// Logique pour vérifier s'il existe déjà...
//    	return userCreated;
//	}

	

}
