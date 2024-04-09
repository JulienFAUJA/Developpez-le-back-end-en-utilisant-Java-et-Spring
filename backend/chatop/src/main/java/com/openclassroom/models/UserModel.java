package com.openclassroom.models;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class UserModel implements UserDetails, Principal{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="email", unique=true)
	private String username;
	private String name;
	private String password;
	@CreationTimestamp
	private Timestamp  created_at;
	@UpdateTimestamp
	private Timestamp  updated_at;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
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
	@Override
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
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}

	public UserModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public UserModel(String username, String name, String password) {
		this.username = username;
		this.name = name;
		this.password = password;
	}
	public UserModel(Integer id, String username, String name, String password, Timestamp created_at, Timestamp updated_at) {
		this.id=id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public UserModel(String username, String name, String password, Timestamp created_at, Timestamp updated_at) {
		this.username = username;
		this.name = name;
		this.password = password;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	

}
