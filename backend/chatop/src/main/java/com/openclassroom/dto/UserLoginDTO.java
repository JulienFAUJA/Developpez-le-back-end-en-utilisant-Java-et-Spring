package com.openclassroom.dto;

import java.sql.Timestamp;
import java.time.Instant;

import com.openclassroom.models.UserModel;

public class UserLoginDTO {

	private String email;
	private String name;
	private String password;

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public UserLoginDTO() {
	}
	

	public UserLoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserLoginDTO(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		
	}
	
	
	@Override
	public String toString() {
		return "UserLoginDTO [email=" + email + ", name=" + name + ", password=" + password+ "]";
	}

	
	public UserLoginDTO(UserModel user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.password = user.getPassword();
    }
}