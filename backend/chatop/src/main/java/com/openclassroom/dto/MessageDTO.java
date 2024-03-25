package com.openclassroom.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private String message;
    private Long user_id;
    private Long rental_id;
}