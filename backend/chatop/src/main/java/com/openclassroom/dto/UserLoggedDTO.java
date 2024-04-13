package com.openclassroom.dto;

import java.sql.Timestamp;

public class UserLoggedDTO {
	
	private Integer id;
    private String email;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
	@Override
	public String toString() {
		return "UserLoggedDTO [id=" + id + ", email=" + email + ", name=" + name + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}

}
