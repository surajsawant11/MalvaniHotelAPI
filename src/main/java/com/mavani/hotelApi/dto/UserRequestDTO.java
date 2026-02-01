package com.mavani.hotelApi.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRequestDTO {
  private Long userId;
  private String name;
  private String phone;
  private String email;
  private String role;
  private String password;
  private Long updatedBy;
  private MultipartFile image;


}
