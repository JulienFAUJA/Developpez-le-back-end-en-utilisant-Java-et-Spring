package com.openclassroom.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserLoggedDTO {
	
	private Integer id;
    private String username;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    
	@Override
	public String toString() {
		return "UserLoggedDTO [id=" + id + ", username=" + username + ", name=" + name + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}

}
