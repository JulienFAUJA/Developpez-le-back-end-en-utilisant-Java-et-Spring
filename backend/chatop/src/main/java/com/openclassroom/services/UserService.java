package com.openclassroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.UserDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;

@Service
public class UserService {

	
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
    
    
	public Iterable<UserDTO> getUsers(){
		List<UserDTO> users = new ArrayList<>();
		this.userRepository.findAll().forEach(u -> users.add(this.convertToDTO(u)));
		return users;
	}
//	public Optional<UserModel> getUserById(Integer id){
//		return userRepository.findById(id);
//	}
//	
	public UserDTO getUserById(Integer id){
		Optional<UserModel> user = this.userRepository.findById(id);
		System.out.println("UserService -> user.orElseThrow():"+user.orElseThrow());
		System.out.println("UserService -> convertToDTO(user.orElseThrow()):"+convertToDTO(user.orElseThrow()));
		return convertToDTO(user.orElseThrow());
	}
	

}
