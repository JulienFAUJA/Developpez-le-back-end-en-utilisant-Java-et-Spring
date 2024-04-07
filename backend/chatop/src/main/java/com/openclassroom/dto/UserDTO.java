package com.openclassroom.dto;

import java.sql.Timestamp;

import com.openclassroom.models.UserModel;


public class UserDTO {
	
	private Integer id;
	private String username;
	private String name;
	private Timestamp  created_at;
	private Timestamp  updated_at;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
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
	

	public UserDTO(String username, String name) {
		this.username = username;
		this.name = name;
	}
	
	
	public UserDTO(Integer id, String username, String name, Timestamp created_at, Timestamp updated_at) {
		this.id=id;
		this.username = username;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public UserDTO(UserModel user) {
		this.id=user.getId();
		this.username = user.getUsername();
		this.name = user.getName();
		this.created_at = user.getCreated_at();
		this.updated_at = user.getUpdated_at();
    }
}