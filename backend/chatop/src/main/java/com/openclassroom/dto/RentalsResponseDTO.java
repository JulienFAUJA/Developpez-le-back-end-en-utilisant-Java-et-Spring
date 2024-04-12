package com.openclassroom.dto;

import java.util.List;

public class RentalsResponseDTO {
	
	private List<RentalFormDTO> rentals;
	
	public RentalsResponseDTO() {
    }

    public RentalsResponseDTO(List<RentalFormDTO> rentals) {
        this.rentals = rentals;
    }

    public List<RentalFormDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalFormDTO> rentals) {
        this.rentals = rentals;
    }

}
