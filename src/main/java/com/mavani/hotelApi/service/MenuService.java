package com.mavani.hotelApi.service;

import com.mavani.hotelApi.dto.MenuRequestDTO;
import com.mavani.hotelApi.dto.MenuResponseDTO;

import java.util.List;

public interface MenuService {
    public List<MenuResponseDTO> findAll(MenuRequestDTO requestDTO);

    MenuResponseDTO save(MenuRequestDTO menuRequestDTO);

    String deleteById(Long menuId);

    MenuResponseDTO findById(Long menuId);

    MenuResponseDTO update(MenuRequestDTO menuRequestDTO, Long menuId);
}
