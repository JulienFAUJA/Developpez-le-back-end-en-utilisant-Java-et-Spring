package com.openclassroom.models;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "messages")
public class MessageModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rental_id;
    private Integer user_id;
    private String message;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public MessageModel() {
    }
    
	public MessageModel(Integer id, Integer rental_id, Integer user_id, String message, Timestamp created_at, Timestamp updated_at) {
		this.id=id;
		this.rental_id = rental_id;
		this.user_id = user_id;
		this.message = message;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public MessageModel(Integer rental_id, Integer user_id, String message, Timestamp created_at, Timestamp updated_at) {
		this.rental_id = rental_id;
		this.user_id = user_id;
		this.message = message;
		this.created_at = created_at;
		this.updated_at = updated_at;
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
	
	public void setCreated_atNow() {
		Timestamp now = Timestamp.from(Instant.now());
		this.setCreated_at(now);
	}

	
	public void setUpdated_atNow() {
		Timestamp now = Timestamp.from(Instant.now());
		this.setUpdated_at(now);
	}

    
}
