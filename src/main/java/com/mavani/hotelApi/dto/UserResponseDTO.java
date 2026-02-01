package com.mavani.hotelApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long userId;
    private String name;
    private String phone;
    private String email;
    private String role;
    private LocalDateTime updatedDt;
    private LocalDateTime createdDt;
    private String createdBy;
    private String updatedBy;
    private String imageUrl;
    private String message;

}
