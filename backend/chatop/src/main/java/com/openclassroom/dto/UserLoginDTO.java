package com.openclassroom.dto;


import com.openclassroom.models.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {

	@NotBlank(message="L'adresse email est obligatoire...")
    @Email(message = "Le format de l'adresse mail n'est pas valide...")
	private String email;
	
	@NotBlank(message="Le mot de passe est obligatoire...")
	private String password;

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	

	public UserLoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	
	
	@Override
	public String toString() {
		return "UserLoginDTO [email=" + email + ", password=" + password+ "]";
	}

	
	public UserLoginDTO(UserModel user) {
		this.email = user.getUsername();
		this.password = user.getPassword();
    }
}