package com.openclassroom.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;
import com.openclassroom.dto.RentalResponseDTO;
import com.openclassroom.dto.RentalsResponseDTO;
import com.openclassroom.services.Interfaces.IRentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	@Autowired
    private IRentalService rentalService;
	
	
	@Operation(summary = "Récupération de toutes les annonces", description = "Permet d'afficher les annonces.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Annonces récupérées avec succès...",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RentalsResponseDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Non authorisé...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> getAllRentals() {
		return ResponseEntity.status(HttpStatus.OK).body(rentalService.getRentals());
		
	}
	
  
	@Operation(summary = "Récupération d'une annonce", description = "Récupération d'une annonce en fonction de son id...")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Annonce récupérée avec succès...",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RentalFormDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Non authorisé...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@GetMapping("{id}")
	public ResponseEntity<?> getRentalById(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(rentalService.getRentalById(id));
	}
	
	@Operation(summary = "Publication d'une annonce", description = "Publication d'une annonce et sauvegarde en base de données...")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Annonce publiée avec succès...",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RentalResponseDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Non authorisé...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@PostMapping
	public ResponseEntity<?> postRental(RentalDTO rentalDTO) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(rentalService.postRental(rentalDTO.getPicture(), rentalDTO));
	}
	
	@Operation(summary = "Modification d'une annonce", description = "Modification d'une annonce en fonction de son id et sauvegarde en base de données...")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Annonce modifiée avec succès...",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RentalResponseDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Non authorisé...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@PutMapping(value="{id}")
	public ResponseEntity<?> setRentalForId(@PathVariable("id") Integer id, RentalFormDTO rentalFormDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(rentalService.updateRental(id, rentalFormDTO));
	}

}




