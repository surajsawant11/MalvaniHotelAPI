package com.mavani.hotelApi.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mavani.hotelApi.model.UserModel;
//import com.mavani.hotelApi.user.UserModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDt;

    @LastModifiedDate
    private LocalDateTime updatedDt;

    // ✅ FK -> t_user.id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private UserModel createdBy;

    // ✅ FK -> t_user.id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private UserModel updatedBy;

    @PrePersist
    public void onCreate() {
        this.createdDt = LocalDateTime.now();
        this.updatedDt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedDt = LocalDateTime.now();
    }

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public LocalDateTime getUpdatedDt() {
        return updatedDt;
    }

    public UserModel getCreatedBy() {
        return createdBy;
    }

    public UserModel getUpdatedBy() {
        return updatedBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public void setUpdatedDt(LocalDateTime updatedDt) {
        this.updatedDt = updatedDt;
    }

    public void setCreatedBy(UserModel createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(UserModel updatedBy) {
        this.updatedBy = updatedBy;
    }
}
