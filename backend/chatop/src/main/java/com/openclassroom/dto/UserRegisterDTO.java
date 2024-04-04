package com.openclassroom.dto;

import java.sql.Timestamp;
import java.time.Instant;

import com.openclassroom.models.UserModel;

public class UserRegisterDTO {
	
	private Integer id;
	private String email;
	private String name;
	private String password;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public void setCreated_atNow() {
		Timestamp now = Timestamp.from(Instant.now());
		this.setCreated_at(now);
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public void setUpdated_atNow() {
		Timestamp now = Timestamp.from(Instant.now());
		this.setUpdated_at(now);
	}
	
	
	public UserRegisterDTO() {
	}
	

	public UserRegisterDTO(String email, String name) {
		this.email = email;
		this.name = name;
	}
	
	public UserRegisterDTO(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		Timestamp now = Timestamp.from(Instant.now());
		this.created_at = now;
		this.updated_at = now;
	}
	
	
	@Override
	public String toString() {
		return "UserRegisterDTO [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}

	public UserRegisterDTO(Integer id, String email, String name, String password, Timestamp created_at, Timestamp updated_at) {
		this.id=id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public UserRegisterDTO(UserModel user) {
		this.id=user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
		this.password = user.getPassword();
		this.created_at = user.getCreated_at();
		this.updated_at = user.getUpdated_at();
    }
}