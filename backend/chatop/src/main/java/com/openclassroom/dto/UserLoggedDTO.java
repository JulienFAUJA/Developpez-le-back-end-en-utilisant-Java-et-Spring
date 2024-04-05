package com.openclassroom.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserLoggedDTO {
	
	private Integer id;
    private String email;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;

}
