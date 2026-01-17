package com.mavani.hotelApi.dto;

//package com.malvanihotel.dto;

public class LoginResponseDTO {

    private String message;
    private String email;
    private String role;
    private String token;
    private String name;

    public LoginResponseDTO(String message, String email, String role,String name, String token) {
        this.message = message;
        this.email = email;
        this.role = role;
        this.name = name;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }
}

