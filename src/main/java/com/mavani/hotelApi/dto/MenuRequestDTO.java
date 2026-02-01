package com.mavani.hotelApi.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuRequestDTO {
    private Long menuId;
    private String name;
    private String category;
    private String description;
    private Double price;
    private String status;
    private MultipartFile image;


}
