package com.openclassroom.dto;

import java.sql.Timestamp;

import com.openclassroom.models.MessageModel;

import lombok.Data;

public class MessageDTO {

	private Integer id;
    private Integer rental_id;
    private Integer user_id;
    private String message;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    
	public MessageDTO() {
	}
	

	public MessageDTO(String message, Integer user_id, Integer rental_id) {
		this.message = message;
		this.user_id = user_id;
		this.rental_id = rental_id;
	}
	
	public MessageDTO(Integer id, Integer rental_id, Integer user_id, String message, Timestamp created_at, Timestamp updated_at) {
		this.id=id;
		this.rental_id = rental_id;
		this.user_id = user_id;
		this.message = message;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public MessageDTO(Integer rental_id, Integer user_id, String message, Timestamp created_at, Timestamp updated_at) {
		this.rental_id = rental_id;
		this.user_id = user_id;
		this.message = message;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public MessageDTO(MessageModel message) {
		this.id=message.getId();
		this.rental_id = message.getRental_id();
		this.user_id = message.getUser_id();
		this.message = message.getMessage();
		this.created_at = message.getCreated_at();
		this.updated_at = message.getUpdated_at();
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

    
}

