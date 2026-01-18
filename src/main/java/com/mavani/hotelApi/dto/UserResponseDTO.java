package com.mavani.hotelApi.dto;

import java.time.LocalDateTime;

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

    public UserResponseDTO(Long userId, String name, String phone, String email, String role, LocalDateTime updatedDt, LocalDateTime createdDt, String createdBy, String updatedBy) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.updatedDt = updatedDt;
        this.createdDt = createdDt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(LocalDateTime updatedDt) {
        this.updatedDt = updatedDt;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
