package com.mavani.hotelApi.model;

import com.mavani.hotelApi.common.model.BaseModel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
})
public class UserModel extends BaseModel {


    private String name;
    private String phone;
    private String email;
    private String password;
    private String role;



}
