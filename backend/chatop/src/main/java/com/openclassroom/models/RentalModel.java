package com.openclassroom.models;

import java.time.Instant;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "rentals")
public class RentalModel{

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String name;
     private Double  surface;
     private Double  price;
     private String picture;
     private String description;
     private Integer owner_id;
     private Instant created_at;
     private Instant updated_at;
     
     
     
     
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

 	public String getPicture() {
 		return picture;
 	}

 	public void setPicture(String picture) {
 		this.picture = picture;
 	}

 	public String getDescription() {
 		return description;
 	}

 	public void setDescription(String description) {
 		this.description = description;
 	}

 	public Integer getOwner_id() {
 		return owner_id;
 	}

 	public void setOwner_id(Integer owner_id) {
 		this.owner_id = owner_id;
 	}

 	public Instant getCreated_at() {
 		return created_at;
 	}

 	public void setCreated_at(Instant now) {
 		this.created_at = now;
 	}

 	public Instant getUpdated_at() {
 		return updated_at;
 	}

 	public void setUpdated_at(Instant updated_at) {
 		this.updated_at = updated_at;
 	}
 	
 	public void CreateNow() {
 		Instant now = Instant.now();
 		this.setCreated_at(now);
 		this.setUpdated_at(now);
 	}
 	
 	public void UpdateNow() {
 		Instant now = Instant.now();
 		this.setUpdated_at(now);
 	}
     
     
	@Override
	public String toString() {
		return "RentalModel [id=" + id + ", name=" + name + ", surface=" + surface + ", price=" + price + ", picture="
				+ picture + ", description=" + description + ", owner_id=" + owner_id + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}
	
	public RentalModel() {
		
	}

	public RentalModel(Integer id, String name, Double surface, Double price, String picture, String description,
			Integer owner_id, Instant created_at, Instant updated_at) {

		this.id = id;
		this.name = name;
		this.surface = surface;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.owner_id = owner_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	

	
     
}



