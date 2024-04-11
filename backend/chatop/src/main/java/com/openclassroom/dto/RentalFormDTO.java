package com.openclassroom.dto;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.models.RentalModel;

import lombok.Data;


public class RentalFormDTO{
	
	private Integer id;
    private String name;
    private Double surface;
    private Double price;
    private String description;
    private String picture;
	private Integer owner_id;
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

 	public Double getSurface() {
 		return surface;
 	}

 	public void setSurface(Double surface) {
 		this.surface = surface;
 	}

 	public Double getPrice() {
 		return price;
 	}

 	public void setPrice(Double price) {
 		this.price = price;
 	}

 	public String getDescription() {
 		return description;
 	}

 	public void setDescription(String description) {
 		this.description = description;
 	}
 	
 	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

 	public Integer getOwner_id() {
 		return owner_id;
 	}

 	public void setOwner_id(Integer owner_id) {
 		this.owner_id = owner_id;
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
     
 
    
    public RentalFormDTO() {
    	
    }
    
//    public RentalDTO(RentalModel rental) {
//    	this.id = rental.getId();
//		this.name = rental.getName();
//		this.surface = rental.getSurface();
//		this.price = rental.getPrice();
//		this.picture = rental.getPicture();
//		this.description = rental.getDescription();
//		this.owner_id = rental.getOwner_id();
//		this.created_at = rental.getCreated_at();
//		this.updated_at = rental.getUpdated_at();
//    }
    
//    public RentalDTO(Integer id, String name, Double surface, Double price, String picture, String description,
//			Integer owner_id, Timestamp created_at, Timestamp updated_at) {
//    	this.id = id;
//		this.name = name;
//		this.surface = surface;
//		this.price = price;
//		this.picture = picture;
//		this.description = description;
//		this.owner_id = owner_id;
//		this.created_at = created_at;
//		this.updated_at = updated_at;
//    }

	@Override
	public String toString() {
		return "RentalFormDTO [\n"+
				"id=" + id + ",\n"+
				"name=" + name + ",\n"+
				"surface=" + surface + ",\n"+
				"price=" + price + ",\n"+
				"picture=" + picture + ",\n"+
				"owner_id=" + owner_id + ",\n"+
				"description=" + description + ",\n"+
				"created_at=" + created_at + ",\n"+
				"updated_at=" + updated_at + "\n]";
	}
	
    
    
}