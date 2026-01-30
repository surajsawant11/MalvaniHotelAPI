package com.mavani.hotelApi.model;

import com.mavani.hotelApi.common.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_menu")
public class MenuModel extends BaseModel {

    @Column(unique = true)
    private String name;
    private String category;
    private String description;
    private Double price;
    private String status;
    @Column(columnDefinition = "boolean default false")
    private Boolean isImageAvailable;




}
