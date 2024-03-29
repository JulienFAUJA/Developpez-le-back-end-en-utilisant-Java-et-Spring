package com.openclassroom.dto;

import java.sql.Timestamp;

import com.openclassroom.models.UserModel;


public class UserDTO {
	
	private Integer id;
	private String email;
	private String name;
	private Timestamp  created_at;
	private Timestamp  updated_at;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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


	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	public UserDTO() {
	}
	

	public UserDTO(String email, String name) {
		this.email = email;
		this.name = name;
	}
	
	
	public UserDTO(Integer id, String email, String name, Timestamp created_at, Timestamp updated_at) {
		this.id=id;
		this.email = email;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public UserDTO(UserModel user) {
		this.id=user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
		this.created_at = user.getCreated_at();
		this.updated_at = user.getUpdated_at();
    }
}