package com.mavani.hotelApi.repo;

import com.mavani.hotelApi.model.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuModel, Long> {
}
