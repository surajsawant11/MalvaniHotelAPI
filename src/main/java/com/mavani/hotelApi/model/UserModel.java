package com.mavani.hotelApi.model;

import com.mavani.hotelApi.common.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "t_user")
public class UserModel extends BaseModel {


    private String name;
    private String phone;
    private String email;
    private String password;
    private String role;



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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
