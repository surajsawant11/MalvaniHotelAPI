package com.mavani.hotelApi.service;

import com.mavani.hotelApi.dto.UserRequestDTO;
import com.mavani.hotelApi.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    public List<UserResponseDTO> find(UserRequestDTO requestDTO);

    UserResponseDTO save(UserRequestDTO requestDTO);
}
