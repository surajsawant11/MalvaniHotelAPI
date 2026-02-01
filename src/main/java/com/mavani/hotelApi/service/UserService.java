package com.mavani.hotelApi.service;

import com.mavani.hotelApi.dto.UserRequestDTO;
import com.mavani.hotelApi.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    public List<UserResponseDTO> findAll(UserRequestDTO requestDTO);

    UserResponseDTO save(UserRequestDTO requestDTO);

    UserResponseDTO update(Long userId, UserRequestDTO requestDTO);

    UserResponseDTO findById(Long userId);

    String deleteById(Long menuId);
}
