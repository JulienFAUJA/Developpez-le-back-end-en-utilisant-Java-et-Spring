package com.openclassroom.models;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "rentals")
public class RentalModel {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String name;
     private String surface;
     private String price;
     private String picture;
     private String description;
     private Integer owner_id;
     private Timestamp created_at;
     private Timestamp updated_at;
}
