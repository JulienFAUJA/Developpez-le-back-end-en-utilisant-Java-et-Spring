package com.openclassroom.dto;

import java.sql.Timestamp;
import java.time.Instant;

import com.openclassroom.models.MessageModel;

import lombok.Data;

public class MessageRequestDTO {

	private Integer id;
    private Integer rental_id;
    private Integer user_id;
    private String message;
    
    
	public MessageRequestDTO() {
	}
	

	public MessageRequestDTO(Integer rental_id, Integer user_id, String message) {
		this.rental_id = rental_id;
		this.user_id = user_id;
		this.message = message;
	}
	
	
	
	public MessageRequestDTO(MessageModel message) {
		this.id=message.getId();
		this.rental_id = message.getRental_id();
		this.user_id = message.getUser_id();
		this.message = message.getMessage();
		
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRental_id() {
		return rental_id;
	}

	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	


    
}

