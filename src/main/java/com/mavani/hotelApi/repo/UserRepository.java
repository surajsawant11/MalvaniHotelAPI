package com.mavani.hotelApi.repo;

import com.mavani.hotelApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
