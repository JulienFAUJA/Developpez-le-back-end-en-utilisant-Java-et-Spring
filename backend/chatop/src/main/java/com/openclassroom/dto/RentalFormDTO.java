package com.openclassroom.dto;


import java.time.Instant;





public class RentalFormDTO{
	
	private Integer id;
    private String name;
    private Double surface;
    private Double price;
    private String description;
    private String picture;
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

 	public Instant getCreated_at() {
 		return created_at;
 	}

 	public void setCreated_at(Instant created_at) {
 		this.created_at = created_at;
 	}

 	public Instant getUpdated_at() {
 		return updated_at;
 	}

 	public void setUpdated_at(Instant updated_at) {
 		this.updated_at = updated_at;
 	}
     
 
    
    public RentalFormDTO() {
    	
    }

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