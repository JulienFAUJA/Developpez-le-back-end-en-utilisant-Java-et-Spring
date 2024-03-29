package com.openclassroom.models;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "users")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	public UserModel() {
	}

	public UserModel(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public UserModel(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
	public UserModel(String email, String name, String password, Timestamp created_at, Timestamp updated_at) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
	
	

}
