package com.mavani.hotelApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MenuResponseDTO {
    private Long menuId;
    private String name;
    private String description;
    private String category;
    private Double price;
    private String message;
    private String status;
    private String imageUrl;



    public MenuResponseDTO(String message){
        this.message = message;
    }

}
