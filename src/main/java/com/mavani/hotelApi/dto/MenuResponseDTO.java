package com.mavani.hotelApi.dto;

public class MenuResponseDTO {
    private Long menuId;
    private String name;
    private String description;
    private String category;
    private Double price;
    private String message;
    private String status;

    public MenuResponseDTO(Long menuId, String name, String description, String category, Double price,String status,String message) {
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.message = message;
        this.status = status;
    }

    public MenuResponseDTO(String message){
        this.message = message;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
